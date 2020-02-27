package com.training.beans;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @date 2020-02-16 20:58:46
 * @description 
 */

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@ApiModel("选项字典类型说明：值类型是DYN表示动态资源，比如注册协议需要传入用户ID，值内容定义为 /docs/register.")
public class MOption  extends Model<MOption>{
	public static final String COL_O_ID="o_id";
	public static final String COL_O_CATEGORY="o_category";
	public static final String COL_O_NAME="o_name";
	public static final String COL_O_VALUE="o_value";
	public static final String COL_O_VALUE_TYPE="o_value_type";
	public static final String COL_O_SCOPE="o_scope";
	public static final String COL_O_REMARKS="o_remarks";
	public static final String COL_O_ORDER="o_order";

	/**
	 * 选项ID
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty("选项ID")
	private Integer o_id;

	/**
	 * 选项分类
	 */
	@ApiModelProperty("选项分类")
	private String o_category;

	/**
	 * 选项名
	 */
	@ApiModelProperty("选项名")
	private String o_name;

	/**
	 * 选项值
	 */
	@ApiModelProperty("选项值")
	private String o_value;

	/**
	 * 值类型（TXT：原始值，STC：静态资源相对路径，DYN：动态页面需要，可以替换变量）
	 */
	@ApiModelProperty("值类型（TXT：原始值，STC：静态资源相对路径，DYN：动态页面需要，可以替换变量）")
	private String o_value_type;

	/**
	 * 配置范围（FON：仅限前端使用，SON：仅限后端使用，BON：前后端都可以用）
	 */
	@ApiModelProperty("配置范围（FON：仅限前端使用，SON：仅限后端使用，BON：前后端都可以用）")
	private String o_scope;

	/**
	 * 选项说明
	 */
	@ApiModelProperty("选项说明")
	private String o_remarks;

	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
	private Integer o_order;


}
