package com.training.controllers;

import com.common.objects.Result;
import com.training.beans.MUser;
import com.training.domains.UserDomain;
import com.training.dtos.MUserDTO;
import com.training.mapper.MUserMapper;
import com.training.vos.MUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.concurrent.BrokenBarrierException;

@Api(tags = "用户模块")
@Controller
@RequestMapping("/v1/user")
public class UserController {
    @Resource
    private UserDomain userDomain;
    @Resource
    private MUserMapper userMapper;

    @ApiOperation(value = "注册", notes = "注册")
    @GetMapping("/register")
    @ResponseBody
    public Result<MUserVO> register(@ApiParam("用户参数对象") @ModelAttribute @Valid final MUserDTO userDTO) throws Exception {
        var usr=this.userDomain.register(userDTO.getUsr_email(), userDTO.getUsr_email(), userDTO.getUsr_pwd());
        var ret=new MUserVO();
        BeanUtils.copyProperties(usr, ret);
        return Result.succes(ret);
    }

    @ApiOperation(value = "登录/注册", notes = "登录或者注册")
    @GetMapping("/login")
    @ResponseBody
    public Result<MUserVO> login(@ApiParam("登录") @ModelAttribute @Valid final MUserDTO userDTO) throws Exception {
        var auth=this.userDomain.login(userDTO.getUsr_email(), userDTO.getUsr_pwd());
        var vo=new MUserVO();
        vo.setUsr_email(auth.get("usr_email",""));
        vo.setToken(auth.getToken());
        vo.setUsr_id(Integer.valueOf(auth.get("usr_id", "")));
        vo.setUsr_name(auth.get("usr_email",""));
        vo.setToken(auth.getToken());
        return Result.succes(vo);
    }

    @ApiOperation(value = "根据用户ID查询用户")
    @GetMapping("/user")
    public Result<MUserVO> getUserById(@ApiParam("登录") @RequestParam(required = true) @Valid @NotNull Integer usrId){
        var usr=this.userMapper.selectById(usrId);
        var ret=new MUserVO();
        BeanUtils.copyProperties(usr, ret, MUser.COL_USR_ID);
        return Result.succes(ret);

    }
}
