package com.training.beans;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

/**
 * @author hanxiaofeng
 * @date 2020-02-16 20:58:46
 * @description 
 */

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@ApiModel("问卷明细")
public class QuestionSheets  extends Model<QuestionSheets>{
	public static final String COL_QSS_ID="qss_id";
	public static final String COL_QS_ID="qs_id";
	public static final String COL_CREATE_TIME="create_time";
	public static final String COL_CREATE_USER="create_user";
	public static final String COL_UPDATE_TIME="update_time";
	public static final String COL_UPDATE_USER="update_user";
	public static final String COL_QSS_TYPE="qss_type";
	public static final String COL_QSS_CONTENT="qss_content";
	public static final String COL_QSS_ORDER="qss_order";
	public static final String COL_VERSION="version";

	/**
	 * 问卷明细ID
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty("问卷明细ID")
	private Integer qss_id;

	/**
	 * 问卷ID
	 */
	@ApiModelProperty("问卷ID")
	private Integer qs_id;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date create_time;

	/**
	 * 创建人
	 */
	@ApiModelProperty("创建人")
	private Long create_user;

	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private Date update_time;

	/**
	 * 更新人
	 */
	@ApiModelProperty("更新人")
	private Long update_user;

	/**
	 * 问卷明细类型（N:姓名，P：电话，W：微信，A：地区，T：文本，R：单选，C：多选）
	 */
	@ApiModelProperty("问卷明细类型（N:姓名，P：电话，W：微信，A：地区，T：文本，R：单选，C：多选）")
	private String qss_type;

	/**
	 * 问卷内容json
	 */
	@ApiModelProperty("问卷内容json")
	private String qss_content;

	/**
	 * 问卷顺序
	 */
	@ApiModelProperty("问卷顺序")
	private Integer qss_order;

	/**
	 * 版本（乐观锁字段）
	 */
	@ApiModelProperty("版本（乐观锁字段）")
	private Integer version;


}
