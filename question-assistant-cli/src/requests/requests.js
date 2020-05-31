/**
 * 认证头请求拦截器插件
 */
import { NoticeUtils, TokenUtils } from '@/common/utils'
import { ErrorConsts } from '@/exceptions/exceptions'
import axios from 'axios'
import * as qs from 'qs'
axios.defaults.timeout=5000;
const request=axios.create()
request.config={withoutNotice:true}

request.interceptors.request.use((config) => {
  //在发送请求之前设置token
  config.headers.Authorization=TokenUtils.getToken()
  if (config.method.toLowerCase()=== "get"){
    config.paramsSerializer = function(params) {
      return qs.stringify(params, {arrayFormat: 'repeat'})
    }
  }
  return config;
},(error) =>{
  console.log('bindRequestAuthorization有错误发生，',error)
  return Promise.reject(error);
})

/**
 * 处理错误信息
 * @param {number} code
 * @param {string} message
 * @returns {{code, message}} 处理后的错误编码/信息
 */
function errorHandler(code, message){
  const result={
    code:code,
    message:message
  }
  // ERR_1001:1001,//业务异常,通常用于弹窗提示直接输出错误,如存在关联无法删除之类的业务异常
  // ERR_1002:1002,//登录令牌错误,需要刷新令牌后重新尝试登录
  // ERR_1003:1003,//参数错误,用于参数校验失败后定位到错误字段,返回json数组如:[{\"email\":\"邮箱格式错误\"},...]
  // ERR_1004:1004,//没有访问权限
  // ERR_1005:1005, //服务器内部异常,必须处理,出现后请反馈给后端,带上错误编号即可,例: [错误编号]:null point Exception
  // CLI_1006:1006  //客户端异常
  if ([ErrorConsts.ERR_1001, ErrorConsts.ERR_1002, ErrorConsts.ERR_1003, ErrorConsts.ERR_1004, ErrorConsts.CLI_1006].indexOf(code)>=0) {
    result.message = message
  } else if (code===ErrorConsts.SUCC){
    result.message="成功"
  }else{
    result.message="有错误发生"
  }
  return result
}

request.interceptors.response.use(response => {
  const {code, message, ret}=response.data
  console.log("=>", JSON.stringify(response.data))
  if (code === ErrorConsts.SUCC){
    return response.data
  }else{
    //自定义错误处理
    const err=errorHandler(code, message)
    if (request.config.withoutNotice){
      NoticeUtils.error(err.message)
    }
  }
  return response.data;
},error => {
  //请求返回异常
  if (error.response) {
    const { response, message } = error;
    // 请求异常
    //store.commit("index/loadingOff"); //loading-1
    // 网络异常
    if (message.indexOf("Network Error")>=0) {
      //msg.showError("网络断开，请重新连接");
      return Promise.reject(error.response.data);
    }
    // 是否取消了请求
    if (axios.isCancel(error)) {
      return Promise.reject(error.response.data);
    }
    //自定义错误处理
    const err=errorHandler(error.response.data.code, error.response.data.message);
    if (error.response.data.message!==err.message){
      error.response.data.message=err.message
    }
  }
  return Promise.reject(error.response.data)
})

export {request}
