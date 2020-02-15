package com.training.beans;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.Builder;
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
 * @date 2020-01-28 11:42:46
 * @description 
 */

@Builder
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@ApiModel("m_用户表")
public class MUser  extends Model<MUser>{

	/**
	 * 用户ID
	 */
	@TableId(type = IdType.AUTO)
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

	/**
	 * 用户密码
	 */
	@ApiModelProperty("用户密码")
	private String usr_pwd;

	/**
	 * 用户密码盐
	 */
	@ApiModelProperty("用户密码盐")
	private String usr_slat;

	/**
	 * 用户加入时间
	 */
	@ApiModelProperty("用户加入时间")
	private Date usr_join_time;

	/**
	 * 用户app-key
	 */
	@ApiModelProperty("用户app-key")
	private String usr_app_key;

	/**
	 * 用户app-secret
	 */
	@ApiModelProperty("用户app-secret")
	private String usr_app_secret;


}
