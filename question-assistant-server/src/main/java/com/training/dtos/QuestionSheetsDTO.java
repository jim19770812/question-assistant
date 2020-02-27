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
@ApiModel("问卷明细DTO")
public class QuestionSheetsDTO {

	/**
	 * 问卷明细ID
	 */
	@ApiModelProperty("问卷明细ID")
	private Integer qss_id;

	/**
	 * 问卷明细类型（N:姓名，P：电话，W：微信，A：地区，T：文本，R：单选，C：多选）
	 */
	@ApiModelProperty("问卷明细类型（N:姓名，P：电话，W：微信，A：地区，T：文本，R：单选，C：多选）")
	@NotNull
	private String qss_type;

	/**
	 * 问卷内容json
	 */
	@ApiModelProperty("问卷内容json")
	private String qss_content;
}
