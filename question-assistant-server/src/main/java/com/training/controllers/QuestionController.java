package com.training.controllers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.objects.Result;
import com.common.utils.M;
import com.common.utils.StringUtil;
import com.training.beans.QuestionSheet;
import com.training.beans.QuestionSheets;
import com.training.dtos.PageDTO;
import com.training.dtos.QuestionSheetDTO;
import com.training.dtos.QuestionSheetsDTO;
import com.training.exceptions.APIException;
import com.training.mapper.QuestionSheetMapper;
import com.training.mapper.QuestionSheetsMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 投票控制器
 */
@Slf4j
@Api(tags = "投票助手控制器")
@Controller
@RequestMapping("/v1/qa")
public class QuestionController {
    @Resource
    private QuestionSheetMapper questionSheetMapper;
    @Resource
    private QuestionSheetsMapper questionSheetsMapper;

    @ApiOperation(value = "发布/修改问题", notes = "发布问题")
    @GetMapping(value="/qa/saveQuestion", produces = "applicaiton/json;charset=utf-8")
    @ResponseBody
    public Result<Boolean> saveQuestion(@ApiParam(required = true, value = "调查问卷的名字") @RequestParam(required = true) final QuestionSheetDTO qsDTO, final List<QuestionSheetsDTO> qssDTOs) throws Exception{
        QuestionSheet qs=null;
        if (qsDTO.getQs_id() !=null){
            qs=this.questionSheetMapper.selectById(qsDTO.getQs_id());
            if (qs == null) {
                throw new APIException(APIException.ERR_1001, M.format("未能根据问卷ID[{}]找到对应的问卷对象", qsDTO.getQs_id()));
            }
        }else{
            qs=new QuestionSheet();
            qs.setCreate_time(new Date());
            qs.setCreate_user(0L);
        }

        BeanUtils.copyProperties(qsDTO, qs);
        qs.setUpdate_time(new Date());
        qs.setUpdate_user(0L);
        qs.setQs_status(0);
        qs.setQs_feedback_count(0);
        qs.setQs_pub_time(new Date());
        qs.setVersion(0);

        var ret=qs.getQs_id()==null?questionSheetMapper.insert(qs):questionSheetMapper.updateById(qs);
        log.debug("保存问卷记录,影响记录数={}", ret);

        //处理修改问卷明细列表
        var qssIdList=qssDTOs.stream().filter(item->item.getQss_id()!=null).map(o->o.getQss_id()).collect(Collectors.toList());
        List<QuestionSheets> qssList=null;
        if (qssIdList!=null && !qssIdList.isEmpty()){
            var qw=new QueryWrapper();
            qw.in(QuestionSheets.COL_QSS_ID, qssIdList);
            qssList=this.questionSheetsMapper.selectList(qw);
            for (var qss : qssList){
                var dto=qssDTOs.stream().filter(o->o.getQss_id()!=null && o.getQss_id()==qss.getQss_id()).findFirst().get();
                BeanUtils.copyProperties(qss, dto);
                qss.setUpdate_time(qs.getUpdate_time());
                qss.setUpdate_user(qs.getUpdate_user());
                ret=questionSheetsMapper.updateById(qss);
                log.debug("更新问卷记录明细[qss_id={}],影响记录数={}", qss.getQss_id(), ret);
            }
        }

        //处理新增问卷明细列表
        var newItems=qssDTOs.stream().filter(item->item.getQss_id()==null).collect(Collectors.toList());
        for (var item : newItems){
            var qss=new QuestionSheets();
            BeanUtils.copyProperties(item, qss);
            qss.setQs_id(qs.getQs_id());
            qss.setCreate_time(qs.getCreate_time());
            qss.setCreate_user(qs.getCreate_user());
            qss.setUpdate_time(qs.getUpdate_time());
            qss.setUpdate_user(qs.getUpdate_user());
            qss.setQss_order(0);
            qss.setVersion(0);
            ret=questionSheetsMapper.insert(qss);
            log.debug("新增问卷记录明细,影响记录数={}", ret);
        }
        return Result.succes(true);
    }

    @GetMapping(value = "/queryQuestionSheet", produces = "applicaiton/json;charset=utf-8")
    @ApiOperation(value="查询问题列表", notes = "问题列表")
    public Result<Page<QuestionSheet>> queryQuestionSheetList(@ApiParam(required = true, value="问卷名称或ID") @Valid @RequestParam(required = true) final String idOrName,
                                  @ApiParam(required = true, value="问卷状态（-1:全部,0：待发布，1：已发布，2：已停止）") @Valid @RequestParam(required = true) @NotNull  @Pattern(regexp = "-1|0|1|2",  message = "必须传入-1/0/1/2之一") final Integer qsStatus,
                                  @ApiParam(required = true, value="分页对象") @Valid @RequestParam(required = true) @NotNull final PageDTO pageDTO) throws Exception{

        var qw=new QueryWrapper();
        if (!StringUtil.isNull(idOrName)){
            if (StringUtil.isNumber(idOrName)){
                qw.eq(QuestionSheet.COL_QS_ID, idOrName);
            }else{
                qw.eq(QuestionSheet.COL_QS_NAME, idOrName);
            }
        }
        if (qsStatus!=-1){
            qw.eq(QuestionSheet.COL_QS_STATUS,  qsStatus);
        }
        var pages=this.questionSheetsMapper.selectPage(pageDTO.getPageInfo(), qw);
        return Result.succes(pages);
    }

    @GetMapping(value="/queryQuestionSheets",produces = "applicaiton/json;charset=utf-8")
    @ApiOperation(value="查询问题明细", notes = "问题列表")
    public Result<Page<QuestionSheets>> queryQuestionSheetsList(@ApiParam(required = true, value="问卷ID") @Valid @RequestParam(required = true) @NotNull final Integer qsId,
                                  @ApiParam(required = true, value="分页对象") @Valid @RequestParam(required = true) @NotNull final PageDTO pageDTO) throws Exception{
        var qw=new QueryWrapper();
        qw.eq(QuestionSheets.COL_QS_ID, qsId);
        var pages=this.questionSheetsMapper.selectPage(pageDTO.getPageInfo(), qw);
        return Result.succes(pages);
    }
}
