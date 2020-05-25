package com.training.vos;

import com.google.gson.JsonObject;
import lombok.Data;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author hanxiaofeng
 * @date 2020-05-24 21:18:12
 * @description 
 */

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@ApiModel("回答表VO")
public class AnswerVO {

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
	 * 回答内容json数组
	 */
	@ApiModelProperty("回答内容json数组")
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
