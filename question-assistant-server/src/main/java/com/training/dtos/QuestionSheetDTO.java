package com.training.dtos;

import lombok.Data;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;

/**
 * @author hanxiaofeng
 * @date 2020-02-16 20:06:46
 * @description 
 */

@Builder
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@ApiModel("问卷表DTO")
public class QuestionSheetDTO {
	@ApiModelProperty("问卷ID")
	private Integer qs_id;

	/**
	 * 问卷名称
	 */
	@ApiModelProperty("问卷名称")
	@NotNull
	private String qs_name;

	/**
	 * 问卷停止时间
	 */
	@ApiModelProperty("问卷停止时间")
	private Date qs_stop_time;
}
