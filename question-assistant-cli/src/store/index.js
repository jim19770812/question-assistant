import Vue from 'vue'
import Vuex from 'vuex'
import auth from '@/modules/auth'
import reset from '@/modules/reset'
import qa from '@/modules/qa'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    auth,
    reset,
    qa
  },
  strict: true //生产环境要关掉严格模式
})
