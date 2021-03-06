package cn.com.leadu.fms.data.postbiz.repository;

import cn.com.leadu.fms.pojo.postbiz.entity.DeferTask;
import cn.com.leadu.fms.common.vo.PageQuery;
import cn.com.leadu.fms.pojo.postbiz.vo.defertask.DeferTaskVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.entity.Example;
import cn.com.leadu.fms.data.base.vo.PageInfoExtend;
import java.util.List;

/**
 * @author ningyangyang
 * @ClassName: DeferTaskRepository
 * @Description: 合同展期Repository层
 */
public interface DeferTaskRepository {

	/**
	 * @Title:
	 * @Description: 新增合同展期
	 * @param deferTask
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int insertData(DeferTask deferTask);

	/**
	 * @Title:
	 * @Description: 批量保存合同展期
	 * @param deferTasks
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int insertDataList(List<DeferTask> deferTasks);

	/**
	 * @Title:
	 * @Description: 修改合同展期
	 * @param deferTask
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int updateByPrimaryKeyData(DeferTask deferTask);

	/**
	 * @Title:
	 * @Description: 批量修改合同展期
	 * @param deferTasks
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int updateByPrimaryKeyDataList(List<DeferTask> deferTasks);

	/**
	 * @Title:
	 * @Description: 动态修改合同展期
	 * @param deferTask
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int updateByPrimaryKeySelectiveData(DeferTask deferTask);

	/**
	 * @Title:
	 * @Description: 批量动态修改合同展期
	 * @param deferTasks
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int updateByPrimaryKeySelectiveDataList(List<DeferTask> deferTasks);

	/**
	 * @Title:
	 * @Description: 根据条件修改合同展期
	 * @param deferTask
	 * @param example
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int updateByExampleData(DeferTask deferTask, Example example);

	/**
	 * @Title:
	 * @Description: 根据条件动态修改合同展期
	 * @param deferTask
	 * @param example
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int updateByExampleSelectiveData(DeferTask deferTask, Example example);

	/**
	 * @Title:
	 * @Description: 根据deferTaskId删除合同展期
	 * @param deferTask
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int deleteData(DeferTask deferTask);

	/**
	 * @Title:
	 * @Description: 根据deferTaskId集合批量删除合同展期
	 * @param deferTaskIds
	 * @param deferTask
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int deleteDataList(List deferTaskIds, DeferTask deferTask);

	/**
	 * @Title:
	 * @Description: 根据自定义条件删除合同展期
	 * @param example
	 * @param deferTask
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int deleteExampleData(Example example, DeferTask deferTask);

	/**
	 * @Title:
	 * @Description: 查询全部合同展期
	 * @return List<DeferTask>
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	List<DeferTask> selectAll();

	/**
	 * @Title:
	 * @Description: 通过条件查询一个合同展期
	 * @param example
	 * @return DeferTask
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	DeferTask selectOneByExample(Example example);

	/**
	 * @Title:
	 * @Description: 通过条件批量查询合同展期
	 * @param example
	 * @return List<DeferTask>
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	List<DeferTask> selectListByExample(Example example);

	/**
	 * @Title:
	 * @Description: 通过deferTaskId查询合同展期
	 * @param deferTaskId
	 * @return DeferTask
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	DeferTask selectByPrimaryKey(Object deferTaskId);

	/**
	 * @Title:
	 * @Description: 分页查询合同展期
	 * @param example
	 * @param pageQuery
	 * @return PageInfoExtend<DeferTask>
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	PageInfoExtend<DeferTask> selectListByExamplePage(Example example, PageQuery pageQuery);

	/**
	 * @Title:
	 * @Description: 分页查询合同展期vo
	 * @param methodName
	 * @param param
	 * @param pageQuery
	 * @return PageInfoExtend
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	PageInfoExtend selectListVoByPage(String methodName, Object param, PageQuery pageQuery);

	/**
	 * @Title:
	 * @Description: 修改合同展期
	 * @param deferTask,并进行排他
	 * @param exclusive
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int updateByPrimaryKeyData(DeferTask deferTask, boolean exclusive);

	/**
	 * @Title:
	 * @Description: 批量修改合同展期,并进行排他
	 * @param deferTasks
	 * @param exclusive
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int updateByPrimaryKeyDataList(List<DeferTask> deferTasks, boolean exclusive);

	/**
	 * @Title:
	 * @Description: 根据主键动态修改合同展期,并进行排他
	 * @param deferTask
	 * @param exclusive
	 * @return int
	 * @throws
	 * @author qiaomengnan
	 * @date 2018-5-25 11:13:59
	 */
	int updateByPrimaryKeySelectiveData(DeferTask deferTask, boolean exclusive);

	/**
	 * @Title:
	 * @Description: 批量动态修改合同展期,并进行排他
	 * @param deferTasks
	 * @param exclusive
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int updateByPrimaryKeySelectiveDataList(List<DeferTask> deferTasks, boolean exclusive);

	/**
	 * @Title:
	 * @Description: 根据条件修改合同展期,并进行排他
	 * @param deferTask
	 * @param example
	 * @param exclusive
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int updateByExampleData(DeferTask deferTask, Example example, boolean exclusive);

	/**
	 * @Title:
	 * @Description: 根据条件动态修改合同展期,并进行排他
	 * @param deferTask
	 * @param example
	 * @param exclusive
	 * @return int
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-1 14:35:16
	 */
	int updateByExampleSelectiveData(DeferTask deferTask, Example example, boolean exclusive);

	/**
	 * @Title:
	 * @Description: 根据contNo获取展期合同的当前合同信息
	 * @param deferTaskVo
	 * @return DeferTaskVo
	 * @throws
	 * @author ningyangyang
	 * @date 2018-9-3 14:35:16
	 */
	DeferTaskVo selectDeferTaskVoByContNo( DeferTaskVo deferTaskVo);
}
