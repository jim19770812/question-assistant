package com.training.controllers;

import com.common.objects.Result;
import com.google.common.collect.Lists;
import com.training.vos.MUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 投票控制器
 */
@Api(tags = "投票助手控制器")
@Controller
@RequestMapping("/v1/qa")
public class TrainingController {
    @ApiOperation(value = "测试api", notes = "一个测试的api")
    @GetMapping("/test")
    @ResponseBody
    public Result<MUserVO> test(@ApiParam(required = true, value = "名字") @RequestParam(required = true) final String name) throws Exception{
        var result=MUserVO.builder()
                .usr_name("张三丰")
                .usr_email("sanfengzhang@qa.com")
                .build();
        return Result.succes(result);
    }

    @GetMapping("/list")
    @ApiOperation(value="列表", notes = "列表")
    public List list() throws Exception{
        return Lists.newArrayList();
    }
}
