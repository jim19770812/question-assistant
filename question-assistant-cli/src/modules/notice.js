/**
 * 通知模块，type可选值：info,warn,error
 * @type {{message: string, type: string, notice: boolean}}
 */
import { StringUtils } from '@/common/utils'

const state={
  notice:false,
  message:"",
  type:"info" //info/warn/error
}

const mutations={
  /**
   * 显示提醒
   * @param {object} states
   * @param {string}message
   */
  info:(states, message)=>{
    if (!StringUtils.isBlank(message)){
      states.notice=true
      states.type='info'
      states.message=message
    }
  },
  /**
   * 显示警告
   * @param {object} states
   * @param {string}message
   */
  warn:(states, message)=>{
    if (!StringUtils.isBlank(message)){
      states.notice=true
      states.type='warn'
      states.message=message
    }
  },
  /**
   * 显示错误
   * @param {object} states
   * @param {string}message
   */
  error:(states, message)=>{
    if (!StringUtils.isBlank(message)){
      states.notice=true
      states.type='error'
      states.message=message
    }
  },
  /**
   * 重置通知，隐藏下拉提示框
   * @param {object}states
   */
  reset:(states)=>{
    states.notice=false
    states.message=''
    states.type=''
  }
}

export default {
  state,
  mutations,
  namespaced:true
}
