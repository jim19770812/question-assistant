import { TokenUtils } from '@/common/utils'

const state={
  activeIndex:0, /*认证页的当前活动页签，0：认证，1：注册*/
  usrInfo:{
    usrId:0,     //用户ID
    usrName:"",  //用户名
    usrEmail:""  //用户邮箱
  },
  resetInfo:{
    ticket:""     //召回密码所用ticket，由服务器端返回
  }
}

const mutations={
  /**
   * 设置认证页的活动页索引
   * @param {state}states
   * @param {number}payload 索引
   */
  setAuthActiveIndex(states, payload){
    states.activeIndex=payload
  },
  /**
   * 设置用户信息
   * @param {state} states
   * @param {{usrId, usrName, usrEmail}} payload
   * @returns {void}
   */
  setUserInfo(states, payload){
    states.usrInfo ={
      usrId:payload.usrId,
      usrName:payload.usrName,
      usrEmail:payload.usrEmail
    }
  },
  logout(states){
    TokenUtils.removeToken()
    states.usrInfo ={
      usrId:0,
      usrName:"未登录",
      usrEmail:""
    }
  },
  /**
   * 保存找回密码的票据
   * @param {state} states
   * @param {string} payload
   * @returns {void}
   */
  saveTicket(states, payload){
    states.resetInfo.ticket=payload
  }
}
const getters={
  activeIndex: states=>{
    return states.activeIndex
  },
  userInfo:states=>{
    return states.usrInfo
  }
}

export default {
  state,
  mutations,
  getters
}
