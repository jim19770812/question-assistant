package com.training.controllers;

import com.common.objects.Result;
import com.common.utils.StringUtil;
import com.training.beans.MUser;
import com.training.domains.UserDomain;
import com.training.dtos.MUserDTO;
import com.training.exceptions.APIException;
import com.training.mapper.MUserMapper;
import com.training.vos.MUserVO;
import com.training.vos.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Null;

@Api(tags = "用户模块")
@RestController
@RequestMapping("/v1/user")
public class UserController extends AbstractController {
    @Resource
    private UserDomain userDomain;
    @Resource
    private MUserMapper userMapper;

    @ApiOperation(value = "注册", notes = "注册")
    @PostMapping("/register")
    public Result<MUserVO> register(@ApiParam("用户参数对象") @ModelAttribute @Valid final MUserDTO userDTO) throws Exception {
        var usr=this.userDomain.register(userDTO.getUsr_email(), userDTO.getUsr_email(), userDTO.getUsr_pwd());
        var ret=new MUserVO();
        BeanUtils.copyProperties(usr, ret);
        return Result.succes(ret);
    }

    @ApiOperation(value = "登录", notes = "登录")
    @GetMapping("/login")
    public Result<MUserVO> login(@ApiParam("登录") @ModelAttribute @Valid final MUserDTO userDTO) throws Exception {
        var auth=this.userDomain.login(userDTO.getUsr_email(), userDTO.getUsr_pwd());
        var vo=new MUserVO();
        vo.setUsr_id(Integer.valueOf(auth.get("usr_id", "")));
        vo.setUsr_name(auth.get("usr_name",""));
        vo.setUsr_email(auth.get("usr_email",""));
        vo.setToken(auth.getToken());
        return Result.succes(vo);
    }

    @ApiOperation(value = "根据用户ID查询用户")
    @GetMapping("/info")
    public Result<MUserVO> info(@ApiParam(value = "用户Id，不传表示获取当前登录用户信息", required = false) @RequestParam(required = false) @Valid Integer usr_id, @ApiParam(hidden = true) @RequestHeader("Authorization") @Null String  token){
        var vo=new MUserVO();
        if (usr_id==null){
            var userInfo=UserDomain.getLoginedUserInfo();
            vo.setUsr_id(userInfo.getUsrId());
            vo.setUsr_name(userInfo.getUsrName());
            vo.setUsr_email(userInfo.getUsrEmail());
            vo.setToken(StringUtil.isNull(token)?"":token);
        }else{
            var usr=this.userMapper.selectById(usr_id);
            if (usr==null){
                throw new APIException(APIException.ERR_1003, "用户不存在");
            }
            BeanUtils.copyProperties(usr, vo);
            vo.setToken("");
            if (!StringUtil.isNull(token) &&  UserDomain.getLoginedUserInfo().getUsrId()!=usr_id){
                vo.setUsr_email(StringUtil.desensitizeMiddle(vo.getUsr_email(), 1,1));
                vo.setUsr_name(StringUtil.desensitizeMiddle(vo.getUsr_name(), 1,1));
            }
        }
        return Result.succes(vo);

    }
}
