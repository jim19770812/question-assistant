const state={
  compList:[], /*当前组件列表*/
  currentIndex:-1 /*当前选中的组件*/
}

const mutations={
  push: (states, comp)=> {
    states.compList.push(comp)
  },
  remove:(states, index)=>{
    states.compList.splice(index)
  },
  /**
   * 选中组件
   * @param {this} states
   * @param {int} index
   */
  select:(states, index)=>{
    states.currentIndex=index
  }
}
const getters={
  getCompList: states=>{
    return states.compList
  },
  getCurrentIndex:states=>{
    return states.currentIndex
  },
  getCurrentComp:states=>{
    return states.compList[states.currentIndex]
  }
}

export default {
  state,
  mutations,
  getters,
  namespaced:true
}
