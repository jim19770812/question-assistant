package com.training.vos;

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
 * @date 2020-05-24 21:18:04
 * @description 
 */

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@ApiModel("问卷表VO")
public class QuestionSheetVO {

	/**
	 * 问卷ID
	 */
	@ApiModelProperty("问卷ID")
	private Integer qs_id;

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
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date create_time;

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
