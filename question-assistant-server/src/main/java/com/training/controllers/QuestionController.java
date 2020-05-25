package com.training.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.objects.Result;
import com.common.utils.DateUtil;
import com.common.utils.M;
import com.common.utils.StringUtil;
import com.training.beans.Answer;
import com.training.beans.QuestionSheet;
import com.training.domains.UserDomain;
import com.training.dtos.AnswerDTO;
import com.training.dtos.PageDTO;
import com.training.dtos.QuestionSheetDTO;
import com.training.enums.QuestionSheetStatus;
import com.training.exceptions.APIException;
import com.training.mapper.AnswerMapper;
import com.training.mapper.QuestionSheetMapper;
import com.training.vos.AnswerVO;
import com.training.vos.QuestionSheetVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 投票控制器
 */
@Slf4j
@Api(tags = "投票助手控制器")
@RestController
@RequestMapping("/v1/qa")
public class QuestionController extends AbstractController {
    @Resource
    private QuestionSheetMapper questionSheetMapper;
    @Resource
    private AnswerMapper answerMapper;

    @ApiOperation(value = "发布/修改问题", notes = "发布问题")
    @PostMapping(value="/questionSheet", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<QuestionSheetVO> saveQuestion(@ApiParam(required = true, value = "调查问卷对象") @RequestBody @Valid QuestionSheetDTO qsDTO) throws Exception{
        QuestionSheet qs=null;
        if (!this.paramIsNull(qsDTO.getQs_id())){
            qs=this.questionSheetMapper.selectById(qsDTO.getQs_id());
            if (qs == null) {
                throw new APIException(APIException.ERR_1001, M.format("未能根据问卷ID[{}]找到对应的问卷对象", qsDTO.getQs_id()));
            }
        }else{
            qs=new QuestionSheet();
            qs.setCreate_time(new Date());
            qs.setCreate_user(0L);
        }

        BeanUtils.copyProperties(qsDTO, qs, QuestionSheet.COL_QS_ID);
        qs.setUpdate_time(new Date());
        qs.setUpdate_user(0L);
        qs.setQs_status(0);
        qs.setQs_feedback_count(0);
        qs.setQs_pub_time(new Date());
        qs.setQs_template(qsDTO.getQs_template());
        qs.setVersion(0);
        qs.setUsr_id(UserDomain.getLoginedUserInfo().getUsrId());
        qs.setQs_stop_time(DateUtil.ZERO_DATE);

        var ret=qs.getQs_id()==null?questionSheetMapper.insert(qs):questionSheetMapper.updateById(qs);
        log.debug("保存问卷记录,影响记录数={}", ret);
        QuestionSheetVO vo=new QuestionSheetVO();
        BeanUtils.copyProperties(qs, vo);
        return Result.succes(vo);
    }

    @ApiOperation(value = "问题状态待发布->已发布")
    @PostMapping(value="/questionSheetStatus01", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<QuestionSheetVO> questionStatus01(@ApiParam(required = true, value = "调查问卷ID") @Valid Integer qsId) throws Exception{
        QueryWrapper qw=new QueryWrapper<>();
        qw.eq(QuestionSheet.COL_QS_ID, qsId);
        qw.eq(QuestionSheet.COL_QS_STATUS, 0);
        var qs=this.questionSheetMapper.selectOne(qw);
        if (qs==null){
            throw new APIException(500, "未能根据传入的问卷ID找到对应的问卷");
        }
        qs.setQs_status(QuestionSheetStatus.PUBLISHED.getStatus());
        var ret=this.questionSheetMapper.updateById(qs);
        log.debug("更新问卷状态，影响记录数:"+ret);
        var vo=new QuestionSheetVO();
        BeanUtils.copyProperties(qs, vo);
        return Result.succes(vo);
    }

    @ApiOperation(value = "问题状态已发布->已停止")
    @PostMapping(value="/questionSheetStatus12", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<QuestionSheetVO> questionStatus12(@ApiParam(required = true, value = "调查问卷的ID") @Valid Integer qsId) throws Exception{
        QueryWrapper qw=new QueryWrapper<>();
        qw.eq(QuestionSheet.COL_QS_ID, qsId);
        qw.eq(QuestionSheet.COL_QS_STATUS, 1);
        var qs=this.questionSheetMapper.selectOne(qw);
        if (qs==null){
            throw new APIException(500, "未能根据传入的问卷ID找到对应的问卷");
        }
        qs.setQs_status(QuestionSheetStatus.STOPED.getStatus());
        var ret=this.questionSheetMapper.updateById(qs);
        log.debug("更新问卷状态，影响记录数:"+ret);
        var vo=new QuestionSheetVO();
        BeanUtils.copyProperties(qs, vo);
        return Result.succes(vo);
    }

    @GetMapping(value = "/questionSheet", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="查询问题", notes = "根据ID查询问题")
    public Result<QuestionSheet> queryQuestionSheet(@ApiParam(required = false, value="问卷名称或ID") @Valid @NotNull @RequestParam(required = false) final Integer id){
        var qw=new QueryWrapper();
        qw.eq(QuestionSheet.COL_QS_ID, id);
        QuestionSheet qs=this.questionSheetMapper.selectOne(qw);
        Result<QuestionSheet> result=Result.succes(qs);
        return result;
    }

    @GetMapping(value = "/questionSheet/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="查询问题列表", notes = "问题列表")
    @ResponseBody
    public Result<Page<QuestionSheetVO>> queryQuestionSheetList(@ApiParam(required = false, value="问卷名称或ID") @Valid @RequestParam(required = false) final String idOrName,
                                                                 @ApiParam(required = true, value="问卷状态（-1:全部,0：待发布，1：已发布，2：已停止）") @Valid @RequestParam(required = true) @NotNull final List<Integer> qsStatusList,
                                                                 @ApiParam(required = true, value="分页对象") @Valid @ModelAttribute @NotNull final PageDTO pageDTO) throws Exception{
        if (qsStatusList.stream().filter(o->o<0&&o>2).count()>0){
            throw new APIException(HttpStatus.BAD_REQUEST, "未传入有效的问卷状态，必须是0/1/2之一");
        }
        var qw=new QueryWrapper();
        if (!StringUtil.isNull(idOrName)){
            if (StringUtil.isNumber(idOrName)){
                qw.eq(QuestionSheet.COL_QS_ID, idOrName);
            }else{
                qw.eq(QuestionSheet.COL_QS_NAME, idOrName);
            }
        }
        qw.in(QuestionSheet.COL_QS_STATUS,  qsStatusList);
        qw.eq(QuestionSheet.COL_USR_ID, UserDomain.getLoginedUserInfo().getUsrId());
        qw.orderByAsc(QuestionSheet.COL_QS_PUB_TIME);
        var pages=this.questionSheetMapper.selectPage(PageDTO.toPage(pageDTO), qw);
        var t=pages.getRecords().stream().map(o->{
            QuestionSheetVO vo=new QuestionSheetVO();
            BeanUtils.copyProperties(o, vo);
            return vo;
        }).collect(Collectors.toList());
        pages.setRecords((List)t);
        return Result.succes(pages);
    }

    @ApiOperation(value="保存回答")
    @PostMapping(value="/answer")
    public Result<AnswerVO> saveAnswer(@ApiParam(required = true, value="") @Valid @RequestBody AnswerDTO answerDTO){
        var answer=new Answer();
        BeanUtils.copyProperties(answerDTO, answer, Answer.COL_AW_ID);
        answer.setUsr_id(UserDomain.getLoginedUserInfo().getUsrId());
        var ret=0;
        if (this.paramIsNull(answer.getAw_id())){
            answer.setCreate_time(new Date());
            answer.setCreate_user((long)answer.getUsr_id());
            answer.setUpdate_time(new Date());
            answer.setUpdate_user((long)answer.getUsr_id());
            answer.setVersion(1);
            answer.setAw_time(new Date());
            var qs=this.questionSheetMapper.selectById(answerDTO.getQs_id());
            answer.setQs_usr_id(qs!=null?qs.getUsr_id():0);
            ret=this.answerMapper.insert(answer);
        }else{
            answer.setUpdate_time(new Date());
            answer.setUpdate_user((long)answer.getUsr_id());
            ret=this.answerMapper.updateById(answer);
        }
        if (ret==0){
            throw new APIException(HttpStatus.BAD_REQUEST, "保存失败");
        }
        log.debug("保存回答完成，影响记录数="+ret);
        AnswerVO vo=new AnswerVO();
        BeanUtils.copyProperties(answer, vo);
        return Result.succes(vo);
    }

    @ApiOperation(value="查询回答列表")
    @GetMapping(value="/answer/list")
    public Result<Page<AnswerVO>> answerList(@ApiParam(required = true, value="问卷ID") @RequestParam(required = true) @Valid @NotNull Integer qsId,
            @ApiParam(required = true, value="分页对象") @Valid @ModelAttribute @NotNull final PageDTO pageDTO) {
        var qw = new QueryWrapper<Answer>();
        qw.eq(Answer.COL_QS_ID, qsId)
                .orderByAsc(Answer.COL_AW_TIME)
                .eq(Answer.COL_QS_USR_ID, UserDomain.getLoginedUserInfo().getUsrId());
        var ret = this.answerMapper.selectPage(PageDTO.toPage(pageDTO), qw);
        var list=ret.getRecords().stream().map(o->{
            var vo=new AnswerVO();
            BeanUtils.copyProperties(o, vo);
            return vo;
        }).collect(Collectors.toList());
        ret.setRecords((List)list);
        return Result.succes(ret);
    }
}
