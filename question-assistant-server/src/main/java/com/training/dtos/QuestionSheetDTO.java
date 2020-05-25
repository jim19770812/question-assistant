package com.training.dtos;

import com.google.gson.JsonObject;
import lombok.*;

import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author hanxiaofeng
 * @date 2020-05-24 21:17:33
 * @description 
 */

@Builder
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("问卷表DTO")
public class QuestionSheetDTO {

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
	 * 问卷内容模板
	 */
	@ApiModelProperty("问卷内容模板")
	private String qs_template;


}
