package cn.com.leadu.fms.prebiz.validator.bizfiles.vo;

import cn.com.leadu.fms.common.vo.BaseVo;
import cn.com.leadu.fms.pojo.prebiz.entity.BizFiles;
import lombok.Data;
import cn.com.leadu.fms.common.constant.ValidatorConstants;
import org.hibernate.validator.constraints.NotBlank;
import java.util.Date;

/**
 * @author huchenghao
 * @ClassName: BizFilesVo
 * @Description: 附件信息修改时载体及验证
 * @date 2018-04-09
 */
@Data
public class BizFilesModifyVo extends BaseVo<BizFiles> {

	private static final long serialVersionUID = 1L;

	/**
	 * @Fields  : 附件ID
	 */
	@NotBlank(message = ValidatorConstants.ID_NOT_NULL)
	private String fileId;

	/**
	 * @Fields  : 所属业务代码
	 */
	private String bizCode;

	/**
	 * @Fields  : 代码类型
	 */
	private String bizCodeType;

	/**
	 * @Fields  : 附件类型代码
	 */
	private String fileType;

	/**
	 * @Fields  : 文件名称
	 */
	private String fileName;

	/**
	 * @Fields  : 附件
	 */
	private String filePath;

}