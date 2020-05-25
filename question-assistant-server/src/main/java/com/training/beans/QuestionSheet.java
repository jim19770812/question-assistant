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
 * @date 2020-05-25 14:10:24
 * @description 
 */

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@ApiModel("问卷表")
public class QuestionSheet  extends Model<QuestionSheet>{
	public static final String COL_QS_ID="qs_id";
	public static final String COL_USR_ID="usr_id";
	public static final String COL_CREATE_TIME="create_time";
	public static final String COL_CREATE_USER="create_user";
	public static final String COL_UPDATE_TIME="update_time";
	public static final String COL_UPDATE_USER="update_user";
	public static final String COL_VERSION="version";
	public static final String COL_QS_NAME="qs_name";
	public static final String COL_QS_STATUS="qs_status";
	public static final String COL_QS_FEEDBACK_COUNT="qs_feedback_count";
	public static final String COL_QS_PUB_TIME="qs_pub_time";
	public static final String COL_QS_STOP_TIME="qs_stop_time";
	public static final String COL_QS_TEMPLATE="qs_template";

	/**
	 * 问卷ID
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty("问卷ID")
	private Integer qs_id;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	private Integer usr_id;

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
	 * 版本（乐观锁字段）
	 */
	@ApiModelProperty("版本（乐观锁字段）")
	private Integer version;

	/**
	 * 问卷名称
	 */
	@ApiModelProperty("问卷名称")
	private String qs_name;

	/**
	 * 问卷状态（0：待发布，1：已发布，2：已停止）
	 */
	@ApiModelProperty("问卷状态（0：待发布，1：已发布，2：已停止）")
	private Integer qs_status;

	/**
	 * 问卷反馈数量
	 */
	@ApiModelProperty("问卷反馈数量")
	private Integer qs_feedback_count;

	/**
	 * 问卷发布时间
	 */
	@ApiModelProperty("问卷发布时间")
	private Date qs_pub_time;

	/**
	 * 问卷停止时间
	 */
	@ApiModelProperty("问卷停止时间")
	private Date qs_stop_time;

	/**
	 * 问卷内容模板
	 */
	@ApiModelProperty("问卷内容模板")
	private String qs_template;


}
