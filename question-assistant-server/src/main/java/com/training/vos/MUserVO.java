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
 * @date 2020-01-18 15:41:20
 * @description 
 */

@Builder
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@ApiModel("m_用户表VO")
public class MUserVO {

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	private Integer usr_id;

	/**
	 * 用户名称
	 */
	@ApiModelProperty("用户名称")
	private String usr_name;

	/**
	 * 用户邮箱
	 */
	@ApiModelProperty("用户邮箱")
	private String usr_email;

	@ApiModelProperty("令牌")
	private String token;

}
