const state={
  selectedCompName:'' /*当前组件页,可选lose-password-component/new-password-component*/
}

const mutations={
  /*
    设置选中的组件名
   */
  setSelectedComponent: (states, name)=> {
    states.selectedCompName=name
  }
}
const getters={
  compName: states=>{
    return states.selectedCompName
  }
}

export default {
  state,
  mutations,
  getters
}
