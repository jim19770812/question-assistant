package com.training.dtos;

import lombok.*;

import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author hanxiaofeng
 * @date 2020-05-24 21:17:50
 * @description 
 */

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("回答表DTO")
public class AnswerDTO {

	/**
	 * 回答ID
	 */
	@ApiModelProperty("回答ID")
	private Integer aw_id;

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
}
