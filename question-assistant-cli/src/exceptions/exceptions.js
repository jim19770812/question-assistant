import Vue from 'vue'
import ca from 'element-ui/src/locale/lang/ca'
/**
 * 异常定义常量
 * @type {{ERR_1005: number, ERR_1004: number, ERR_1003: number, ERR_1002: number, SUCC: number, ERR_1001: number}}
 */
const ErrorConsts={
  SUCC:1000,//成功
  ERR_1001:1001,//业务异常,通常用于弹窗提示直接输出错误,如存在关联无法删除之类的业务异常
  ERR_1002:1002,//登录令牌错误,需要刷新令牌后重新尝试登录
  ERR_1003:1003,//参数错误,用于参数校验失败后定位到错误字段,返回json数组如:[{\"email\":\"邮箱格式错误\"},...]
  ERR_1004:1004,//没有访问权限
  ERR_1005:1005, //服务器内部异常,必须处理,出现后请反馈给后端,带上错误编号即可,例: [错误编号]:null point Exception
  CLI_1006:1006  //客户端异常
}
Object.freeze(ErrorConsts)
export {ErrorConsts}

/**
 * 与服务器端异常保持一致，出现此异常是需要出现通知提醒的
 */
export class APIException extends Error{
  constructor (code, message) {
    super(message)
    this.code=code
  }
}

/**
 * 全局异常处理
 * @param {string} err
 * @param {Vue} vm
 * @param {object}info
 * @returns {void}
 */
export const globalErrorHandler=(err, vm, info)=>{
  console.log("全局异常处理：", err, info)
  // if (Vue.config.errorHandler){
  //   try{
  //     return Vue.config.errorHandler.call(null, err, vm, info)
  //   }catch (e) {
  //     console.error(e, null, 'config.errorHandler')
  //   }
  // }
}

/**
 * 捕获异常
 * @param {{code, ret, message}}response
 * @param {[{code:int, customMessage:string}]} codeAndMessages
 * @reutrns {boolean}
 */
export function catchBusinessError(response, codeAndMessages){
  codeAndMessages.forEach(cm=>{
    switch (response.code) {
      case ErrorConsts.ERR_1001:{ //业务异常,通常用于弹窗提示直接输出错误,如存在关联无法删除之类的业务异常
        console.log(1)
        return false
      }
      case ErrorConsts.ERR_1002:{ //登录令牌错误,需要刷新令牌后重新尝试登录
        Vue.$router.replace({name:"login"})
        return false
      }
      case ErrorConsts.ERR_1003:{ //参数错误,用于参数校验失败后定位到错误字段,返回json数组如:[{\"email\":\"邮箱格式错误\"},...]
        
        return false
      }
      case ErrorConsts.ERR_1004:{ //没有访问权限

        return false
      }
      case ErrorConsts.ERR_1005:{ //服务器内部异常,必须处理,出现后请反馈给后端,带上错误编号即可,例: [错误编号]:null point Exception

        return false
      }
      default: { //客户端异常
        return false
      }
    }
  })
}
