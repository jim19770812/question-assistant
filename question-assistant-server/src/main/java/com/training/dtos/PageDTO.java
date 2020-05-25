package com.training.dtos;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页对象")
public class PageDTO {
    @ApiModelProperty("页号")
    @NotNull
    @ApiParam(required = false, defaultValue = "1")
    @Min(1)
    private Integer pageNum;

    @ApiModelProperty("每页记录数")
    @Min(1)
    @Max(100)
    @ApiParam(required = false, defaultValue = "20")
    private Integer pageSize;
    public static Page toPage(PageDTO pageDTO){
        return new Page(pageDTO.getPageNum(), pageDTO.getPageSize());
    }
}