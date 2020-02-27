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
@ApiModel("反馈明细")
public class Feedbacks  extends Model<Feedbacks>{
	public static final String COL_FBS_ID="fbs_id";
	public static final String COL_FB_ID="fb_id";
	public static final String COL_QS_ID="qs_id";
	public static final String COL_QSS_ID="qss_id";
	public static final String COL_CREATE_TIME="create_time";
	public static final String COL_CREATE_USER="create_user";
	public static final String COL_UPDATE_TIME="update_time";
	public static final String COL_UPDATE_USER="update_user";
	public static final String COL_USR_ID="usr_id";
	public static final String COL_FBS_CONTENT="fbs_content";
	public static final String COL_VERSION="version";

	/**
	 * 反馈明细ID
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty("反馈明细ID")
	private Integer fbs_id;

	/**
	 * 回答ID
	 */
	@ApiModelProperty("回答ID")
	private Integer fb_id;

	/**
	 * 问卷ID
	 */
	@ApiModelProperty("问卷ID")
	private Integer qs_id;

	/**
	 * 问卷明细ID
	 */
	@ApiModelProperty("问卷明细ID")
	private Integer qss_id;

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
	 * 用户ID（冗余）
	 */
	@ApiModelProperty("用户ID（冗余）")
	private Integer usr_id;

	/**
	 * 反馈明细内容json数组
	 */
	@ApiModelProperty("反馈明细内容json数组")
	private String fbs_content;

	/**
	 * 版本（乐观锁字段）
	 */
	@ApiModelProperty("版本（乐观锁字段）")
	private Integer version;


}
