const state={
  internalActiveIndex:0 /*当前活动页签*/
}

const mutations={
  /*
    切换到登录页
   */
  loginActived: states=> {
    states.internalActiveIndex=0
  },
  /*
    切换到注册页面
   */
  registerActived: states=>{
    states.internalActiveIndex=1
  }
}
const getters={
  activeIndex: states=>{
    return states.internalActiveIndex
  }
}

export default {
  state,
  mutations,
  getters
}
