package cn.com.leadu.fms.business.common.util.activiti;

import cn.com.leadu.fms.business.common.constant.enums.activiti.ActBasSalesEnums;
import cn.com.leadu.fms.business.common.constant.enums.activiti.ActBasSalesStatusEnums;
import cn.com.leadu.fms.business.common.constant.enums.activiti.ActProcessInstanceKeyEnums;
import cn.com.leadu.fms.common.exception.FmsServiceException;
import cn.com.leadu.fms.common.util.ArrayUtils;
import cn.com.leadu.fms.common.util.LogUtils;
import cn.com.leadu.fms.common.util.StringUtils;
import cn.com.leadu.fms.extend.common.util.UserInfoUtils;
import cn.com.leadu.fms.pojo.activiti.vo.actrutask.ActRuTaskVo;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.FormService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengbo
 * @ClassName: ActBasSalesUtils
 * @Description: 实际销售方工作流工具类
 * @date
 */
@Component
@Slf4j
public class ActBasSalesUtils {

    /**
     * 单例
     */
    private static ActBasSalesUtils actBasSalesUtils = null;

    /**
     * @Fields  : 每个流程节点的审批状态名称
     * @author yanfengbo
     */
    private static Map<String,String> approvalStatusNames = new HashMap<>();

    /**
     * @Fields  : 需要设置用户的节点
     * @author yanfengbo
     */
    private static String [] userDefKeys = null;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    public ActBasSalesUtils(){
        actBasSalesUtils = this;
        approvalStatusNames.put(ActBasSalesEnums.BAS_SALES_OPERATION.getFlag(),ActBasSalesEnums.BAS_SALES_OPERATION_STATUS.getFlag());
        approvalStatusNames.put(ActBasSalesEnums.BAS_SALES_APPROVE.getFlag(),ActBasSalesEnums.BAS_SALES_APPROVAL_STATUS.getFlag());

        //需要设置用户的节点
        userDefKeys = new String[] {ActBasSalesEnums.BAS_SALES_OPERATION.getFlag()};
    }

    /**
     * @Title:
     * @Description: 启动实际销售方添加修改
     * @param
     * @return
     * @throws
     * @author yanfengbo
     * @date
     */
    public static ActRuTaskVo startBasSales(String serviceId, String serviceType, String serviceName){
        //当前登录用户就是申请人员
        String operationBasSalesUser = UserInfoUtils.getUserName();
        //任务发起人账号
        String startUser = UserInfoUtils.getUserName();
        //任务发起人姓名
        String startUserName = UserInfoUtils.getUser().getUserName();
        //构建参数
        Map<String,Object> variables = new HashMap<>();
        //设置实际销售方添加或修改人
        variables.put(ActBasSalesEnums.OPERATION_BAS_SALES_USER.getFlag(),operationBasSalesUser);
        ActUtils.putVariables(variables, ActProcessInstanceKeyEnums.BAS_SALES.getKey(),
                ActProcessInstanceKeyEnums.BAS_SALES.getDesc(),serviceType,serviceName,serviceId,startUser,startUserName);
        //启动流程
        ProcessInstance processInstance = actBasSalesUtils.runtimeService.startProcessInstanceByKey(ActProcessInstanceKeyEnums.BAS_SALES.getKey(),serviceId,variables);
        //第一条任务是申请录入人的
        Task task  = actBasSalesUtils.taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        //提前获取到审批人的信息,并设置审批人
        Map<String,Object> taskVariables = setApprovalUser(task);
        //自动设置为完成
        actBasSalesUtils.taskService.complete(task.getId(),taskVariables);
        //返回任务详情信息
        return ActUtils.getActRuTaskVoAndNextInfo(task);
    }

    /**
     * @Title:
     * @Description: 设置审批人
     * @param
     * @return
     * @throws
     * @author yanfengbo
     * @date
     */
    private static Map<String,Object> setApprovalUser(Task task){
        //为了防止是退回后,再次进行提交,所以要进行查询流程参数,判断每个流程的具体审批人是否已经被设置过
        Map<String,Object> taskVariables = getTaskVariables(task.getExecutionId());
        //拿出表单每个节点审批人的信息
        Map<String,String> formPropertyMap = ActUtils.getTaskFormData(task.getId());
        //最终返回的审批人信息
        Map<String,Object> results = new HashMap<>();
        //如果申请审批人为空，则进行放入
        if(StringUtils.isTrimBlank(taskVariables.get(ActBasSalesEnums.BAS_SALES_APPROVAL_USER.getFlag()))){
            String approvalUserId = formPropertyMap.get(ActBasSalesEnums.BAS_SALES_APPROVAL_USER_ID.getFlag());
            String approvalUserUnit = formPropertyMap.get(ActBasSalesEnums.BAS_SALES_APPROVAL_USER_UNIT.getFlag());
            Object approvalUser = ActUtils.getNextUser(approvalUserId,approvalUserUnit);
            LogUtils.infoLine(log,"流程实例: " + task.getProcessInstanceId() + "的申请审批人是: " + JSON.toJSONString(approvalUser));
            results.put(ActBasSalesEnums.BAS_SALES_APPROVAL_USER.getFlag(),approvalUser);
        }
        return results;
    }

    private static Map<String,Object> getTaskVariables(String executionId){
        //为了防止是退回后,再次进行提交,所以要进行查询流程参数,判断每个流程的具体审批人是否已经被设置过
        Map<String,Object> taskVariables = actBasSalesUtils.runtimeService.getVariables(executionId);
        return taskVariables;
    }

    /**
     * @Title:
     * @Description: 实际销售方维护审核通过
     * @param
     * @return
     * @throws
     * @author yanfengbo
     * @date
     */
    public static ActRuTaskVo approvalAgree(String taskId){
        if(StringUtils.isTrimBlank(taskId))
            throw new FmsServiceException("任务id不能为空");
        //审批通过时需要判断当前节点,需要设置下级审批人
        Task task = actBasSalesUtils.taskService.createTaskQuery().taskId(taskId).singleResult();
        if(task == null)
            throw new FmsServiceException("任务不存在");
        if(ArrayUtils.equalsContains(userDefKeys,task.getTaskDefinitionKey())){
            //如果是需要设置审批人的节点则进行设置审批人
            return approval(ActBasSalesStatusEnums.AGREE.getStatus(),taskId,setUser(task));
        }else{
            //否则正常走下一步
            return approval(ActBasSalesStatusEnums.AGREE.getStatus(),taskId);
        }
    }

    /**
     * @Title:
     * @Description: 退回上一级
     * @param
     * @return
     * @throws
     * @author yanfengbo
     * @date
     */
    public static ActRuTaskVo approvalReturnSuperior(String taskId){
        return approval(ActBasSalesStatusEnums.RETURN.getStatus(),taskId);
    }

    /**
     * @Title:
     * @Description: 设置相应节点的审批人
     * @param
     * @return
     * @throws
     * @author yanfengbo
     * @date
     */
    private static Map<String,Object> setUser(Task task){
        if(task.getTaskDefinitionKey().equals(ActBasSalesEnums.BAS_SALES_OPERATION.getFlag())){
            //提交节点设置审批人
            return setApprovalUser(task);
        }
        else{
            throw new FmsServiceException("此任务节点不需要设置审批人");
        }
    }

    /**
     * @Title:
     * @Description: 审核任务
     * @param
     * @return
     * @throws
     * @author yanfengbo
     * @date
     */
    private static ActRuTaskVo approval(Integer status,String taskId){
        if(StringUtils.isTrimBlank(taskId))
            throw new FmsServiceException("任务id不能为空 ");
        Map<String,Object> variables = new HashMap<>();
        Task task = ActUtils.getTask(taskId);

        variables.put(getApprovalStatusName(task.getTaskDefinitionKey()),status);
        actBasSalesUtils.taskService.complete(taskId,variables);
        return ActUtils.getActRuTaskVoAndNextInfo(task);
    }

    /**
     * @Title:
     * @Description: 审核任务
     * @param
     * @return
     * @throws
     * @author yanfengbo
     * @date
     */
    private static ActRuTaskVo approval(Integer status,String taskId,Map<String,Object> variables){
        Task task = ActUtils.getTask(taskId);
        variables.put(getApprovalStatusName(task.getTaskDefinitionKey()),status);
        actBasSalesUtils.taskService.complete(taskId,variables);
        return ActUtils.getActRuTaskVoAndNextInfo(task);
    }

    /**
     * @Title:
     * @Description: 获取当前节点对应的状态参数
     * @param
     * @return
     * @throws
     * @author yanfengbo
     * @date
     */
    public static String getApprovalStatusName(String taskDefinitionKey){
        if(StringUtils.isTrimBlank(taskDefinitionKey))
            throw new FmsServiceException("taskDefinitionKey不能为空");
        //流程节点状态名称
        String approvalStatusName = approvalStatusNames.get(taskDefinitionKey);
        if(StringUtils.isTrimBlank(approvalStatusName)){
            throw  new FmsServiceException("taskDefinitionKey对应的状态标识不存在");
        }
        return approvalStatusName;
    }
}
