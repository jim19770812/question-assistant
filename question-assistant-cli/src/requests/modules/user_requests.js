import {request as request2} from "../no_auth_requests"
import {request} from "../requests"

/**
 * 用户登录
 * @param {string} account
 * @param {string} password
 * @returns {Promise}
 */
export function login(account, password){
  request2.config.withoutNotice=true
  return request2({
    method:"get",
    url:"/xhr/v1/user/login",
    params:{
      usr_email:account.trim(),
      usr_pwd:password.trim()
    }
  })
}

/**
 * 用户注册
 * @param {string} account
 * @param {string} password
 * @returns {Promise}
 */
export function register(account, password){
  request2.config.withoutNotice=true
  return request2({
    method:"post",
    url:"/xhr/v1/user/register",
    params:{
      usr_email:account.trim(),
      usr_pwd:password.trim()
    }
  })
}

/**
 * 根据用户ID查找用户
 * @param {int}usrId
 * @returns {Promise}
 */
export function getUser(usrId){
  return request({
    method:"get",
    url:"/xhr/v1/user/info",
    params:{
      usr_id:usrId
    }
  })
}

/**
 * 重置密码并发送邮件
 * @param {string} mail
 * @returns {AxiosPromise}
 */
export function resetPasswordSendMail(mail){
  return request({
    method:"post",
    url:"/xhr/v1/user/resetPasswordSendMail",
    params:{
      mail:mail
    }
  })
}

/**
 * 重置密码时验证并重置验证码
 * @param {string} ticket
 * @param {string} code
 * @param {string} password
 * @returns {AxiosPromise}
 */
export function resetPasswordValidateCode(ticket, code, password){
  return request({
    method:"post",
    url:"/xhr/v1/user/resetPasswordValidateCode",
    params:{
      ticket:ticket,
      code:code,
      password:password
    }
  })
}
