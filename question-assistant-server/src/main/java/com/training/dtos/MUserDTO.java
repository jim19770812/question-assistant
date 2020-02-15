package com.training.dtos;

import lombok.*;

import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author hanxiaofeng
 * @date 2019-12-31 22:48:55
 * @description 
 */

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("m_用户表DTO")
public class MUserDTO {

	/**
	 *  用户邮箱 
	 */
	@ApiModelProperty(" 用户邮箱 ")
	@NotEmpty
	@NotNull
	private String usr_email;

	/**
	 *  用户密码 
	 */
	@NotEmpty
	@NotNull
	@ApiModelProperty(" 用户密码 ")
	private String usr_pwd;

}
