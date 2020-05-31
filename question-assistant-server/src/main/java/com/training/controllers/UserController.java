package com.training.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.objects.Result;
import com.common.utils.MD5Util;
import com.common.utils.StringUtil;
import com.common.utils.UUIDUtils;
import com.google.gson.JsonObject;
import com.training.beans.MUser;
import com.training.domains.UserDomain;
import com.training.dtos.MUserDTO;
import com.training.exceptions.APIException;
import com.training.mapper.MUserMapper;
import com.training.vos.MUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.Duration;
import java.util.Map;

@Api(tags = "用户模块")
@RestController
@RequestMapping("/v1/user")
@Slf4j
public class UserController extends AbstractController {
    @Resource
    private UserDomain userDomain;
    @Resource
    private MUserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Value("${mail.smtp.host}")
    private String mailStmpHost;
    @Value("${mail.smtp.account")
    private String mailSmtpAccount;
    @Value("${mail.smtp.password}")
    private String mailSmtpPassword;
    @Value("${mail.encoding}")
    private String mailEncoding;

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

    @ApiOperation(value = "重置密码第一步：发送邮件")
    @PostMapping("/resetPasswordSendMail")
    public Result<String> resetPasswordSendMail(@ApiParam(value="邮箱", required = true) @NotNull @RequestParam(required = true) final String mail){
        var qw=new QueryWrapper<MUser>();
        qw.eq(MUser.COL_USR_EMAIL, mail);
        MUser user=this.userMapper.selectOne(qw);
        if (user==null){
            throw new APIException(APIException.ERR_1003, "用户不存在");
        }


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("hanxiaofeng77@163.com");//收信人
        message.setSubject("投票助手验证码");//主题
        var ticket= UUIDUtils.generate32Key();
        var code= UUIDUtils.generate8Key();
        var data= new JsonObject();
        data.addProperty("code", code);
        data.addProperty("email", "hanxiaofeng77@163.com");
        message.setText("您的验证码是："+code);//内容
        message.setFrom("shunzhao_bot@126.com");//发信人
        this.redisTemplate.boundValueOps(ticket).set(data, Duration.ofMinutes(2));//缓存2分钟

        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost("smtp.126.com");
        mailSender.setUsername("shunzhao_bot@126.com");
        mailSender.setPassword("FOQQFTXSKNTTPHKH");
        mailSender.setDefaultEncoding("utf-8");
        mailSender.send(message);
        return Result.succes(ticket);
    }

    @ApiOperation(value = "重置密码第二步：验证并重置验证码")
    @PostMapping("/resetPasswordValidateCode")
    public Result<Boolean> resetPasswordValidateCode(@ApiParam(value="发送邮件的票据，是个32位长度的字符串", required = true) @NotNull @RequestParam(required = true) final String ticket,
                                                     @ApiParam(value="邮件中的验证码", required = true) @NotNull @RequestParam(required = true) final String code,
                                                     @ApiParam(value="新的密码", required = true) @NotNull @RequestParam(required = true) final String password){
        if (StringUtil.isNull(ticket) || StringUtil.isNull(code)){
            throw new APIException(APIException.ERR_1003, "没有传入有效的ticket或code");
        }
        var data=(Map)this.redisTemplate.boundValueOps(ticket).get();
        if (!code.equals(data.get("code"))){
            throw new APIException(APIException.ERR_1003, "验证码不一致");
        }
        //重置密码
        var qw=new QueryWrapper<MUser>();
        qw.eq(MUser.COL_USR_EMAIL, data.get("email"));
        MUser user=this.userMapper.selectOne(qw);
        if (user==null){
            throw new APIException(APIException.ERR_1003, "用户不存在");
        }
//        usr_pwd=md5(md5(concat(#{password}, usr_slat))
        try {
            user.setUsr_pwd(MD5Util.encode(MD5Util.encode(password+user.getUsr_slat())));
            var ret=this.userMapper.updateById(user);
            log.debug("更新用户密码，影响记录数="+ret);
            this.redisTemplate.delete(ticket);//完成后移除已经验证过的key
        } catch (Exception e) {
            throw new APIException(APIException.ERR_1001, e.getMessage());
        }
        return Result.succes(true);
    }
}
