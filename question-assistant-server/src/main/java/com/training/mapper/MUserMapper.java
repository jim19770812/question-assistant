package com.training.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import com.training.beans.MUser;

/**
 * @author hanxiaofeng
 * @date 2019-12-29 20:13:22
 * @description 
 */

@Component
public interface MUserMapper extends BaseMapper<MUser> {
    @Select("select * from m_user where usr_email=#{email} and usr_pwd=md5(md5(concat(#{password}, usr_slat)))")
    MUser findUserByEmailAndPassword(@Param("email")final String email, @Param("password") final String password);
    @Select("select * from m_user where usr_email=#{email}")
    MUser findUserByEmail(@Param("email")final String email);
    @Select("select count(1) from m_user where usr_email=#{email}")
    int countUserByEmail(@Param("email")final String email);
}
