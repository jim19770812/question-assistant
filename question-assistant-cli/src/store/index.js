import Vue from 'vue'
import Vuex from 'vuex'
import auth from '../modules/auth'
import reset from '../modules/reset'
import qa from '../modules/qa'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    auth,
    reset,
    qa
  }
})
