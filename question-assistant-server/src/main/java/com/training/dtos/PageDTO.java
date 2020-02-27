package com.training.dtos;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@ApiModel("分页对象")
public class PageDTO {
    @ApiModelProperty("页号")
    @NotNull
    @Min(1)
    private Integer pageNum;

    @ApiModelProperty("每页记录数")
    @Min(1)
    @Max(100)
    private Integer pageSize;

	public Page getPageInfo(){
		return new Page(this.pageNum, this.pageSize);
	}

}
