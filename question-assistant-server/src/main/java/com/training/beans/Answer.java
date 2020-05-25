package com.training.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;
import com.google.gson.JsonObject;
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
 * @date 2020-05-24 21:16:31
 * @description 
 */

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@ApiModel("回答表")
public class Answer  extends Model<Answer>{
	public static final String COL_AW_ID="aw_id";
	public static final String COL_USR_ID="usr_id";
	public static final String COL_QS_ID="qs_id";
	public static final String COL_CREATE_TIME="create_time";
	public static final String COL_CREATE_USER="create_user";
	public static final String COL_UPDATE_TIME="update_time";
	public static final String COL_UPDATE_USER="update_user";
	public static final String COL_VERSION="version";
	public static final String COL_AW_CONTENT="aw_content";
	public static final String COL_AW_TIME="aw_time";
	public static final String COL_QS_USR_ID="qs_usr_id";

	/**
	 * 回答ID
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty("回答ID")
	private Integer aw_id;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	private Integer usr_id;

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
	 * 版本（乐观锁字段）
	 */
	@ApiModelProperty("版本（乐观锁字段）")
	private Integer version;

	/**
	 * 回答内容json数组
	 */
	@ApiModelProperty("回答内容json数组")
//	@TableField(typeHandler = GsonTypeHandler.class)
	private String aw_content;

	/**
	 * 回答时间
	 */
	@ApiModelProperty("回答时间")
	private Date aw_time;

	/**
	 * 提问用户ID（冗余）
	 */
	@ApiModelProperty("提问用户ID（冗余）")
	private Integer qs_usr_id;


}
