package cn.com.leadu.fms.pojo.prebiz.vo.contrepaysked;

import cn.com.leadu.fms.common.util.DateUtils;
import cn.com.leadu.fms.common.vo.PageQuery;
import cn.com.leadu.fms.pojo.prebiz.entity.ContRepaySked;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @author ningyangyang
 * @ClassName: ContRepaySkedVo
 * @Description: 融资合同还款计划载体
 * @date 2018-05-08
 */
@Data
public class ContRepaySkedVo extends PageQuery<ContRepaySked> {

	private static final long serialVersionUID = 1L;

	/**
	 * @Fields  : 还款计划表ID
	 * @author ningyangyang
	 */
	private String repaySkedId;

	/**
	 * @Fields  : 合同编号
	 * @author ningyangyang
	 */
	private String contNo;

	/**
	 * @Fields  : 期数
	 * @author ningyangyang
	 */
	private Integer period;

	/**
	 * @Fields  : 成交日期
	 * @author ningyangyang
	 */
	private Date dealDate;

	/**
	 * @Fields  : 收款日期
	 * @author ningyangyang
	 */
	private Date repayDate;

	/**
	 * @Fields  : 月利率
	 * @author ningyangyang
	 */
	private BigDecimal intrstMonthRate;

	/**
	 * @Fields  : 每期客户租金
	 * @author ningyangyang
	 */
	private BigDecimal rent;

	/**
	 * @Fields  : 当期本金
	 * @author ningyangyang
	 */
	private BigDecimal principal;

	/**
	 * @Fields  : 当期利息
	 * @author ningyangyang
	 */
	private BigDecimal interest;

	/**
	 * @Fields  : 手续费用
	 * @author ningyangyang
	 */
	private BigDecimal charge;

	/**
	 * @Fields  : 当期剩余本金
	 * @author ningyangyang
	 */
	private BigDecimal restPrincipal;

	/**
	 * @Fields  : 扣款状态
	 * @author ningyangyang
	 */
	private String repayStatus;

	/**
	 * @Fields  : 逾期状态
	 * @author ningyangyang
	 */
	private String overdueStatus;

	/**
	 * @Fields  : 暂不扣款标志
	 * @author ningyangyang
	 */
	private String repayFlag;

	/**
	 * @Fields  : 实际收款银行
	 * @author ningyangyang
	 */
	private String recAccBank;

	/**
	 * @Fields  : 实际收款账号
	 * @author ningyangyang
	 */
	private String recAccountNo;

	/**
	 * @Fields  : 实际收款户名
	 * @author ningyangyang
	 */
	private String recAccountName;

	/**
	 * @Fields  : 保证金
	 * @author ningyangyang
	 */
	private BigDecimal deposit;

	/**
	 * @Fields  : 每期客户实际租金
	 * @author ningyangyang
	 */
	private BigDecimal rentActual;

	/**
	 * @Fields  : 到账日期
	 * @author ningyangyang
	 */
	private Date receiptDate;

	/**
	 * @Fields  : 还款计划表ID
	 * @author ningyangyang
	 */
	private List<String> repaySkedIds;

	/**
	 * @Fields  : 到账金额
	 * @author ningyangyang
	 */
	private BigDecimal  receiptAmount;

	/**
	 * @Fields  : 开票日期
	 * @author yangyiquan
	 */
	@JsonFormat(pattern = DateUtils.formatStr_yyyyMMdd)
	private Date invoiceDate;

	/**
	 * @Fields  : 备注
	 * @author yangyiquan
	 */
	private String memo;

}