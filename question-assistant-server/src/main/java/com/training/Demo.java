package com.training;

import com.common.utils.UUIDUtils;
import com.google.gson.JsonObject;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class Demo {
    public static void main(String[] args){
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

        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost("smtp.126.com");
        mailSender.setUsername("shunzhao_bot@126.com");
        mailSender.setPassword("FOQQFTXSKNTTPHKH");
        mailSender.setDefaultEncoding("utf-8");
        mailSender.send(message);
        System.out.println("完成");
    }
}
