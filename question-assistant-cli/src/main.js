import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import "reset.css"
import Test from '@/views/Test'
import { globalErrorHandler } from '@/exceptions/exceptions'

Vue.config.productionTip = true //在非生产环境使用的警告信息，会帮助程序除错但也会带来运行开销
new Vue({
  router,
  store,
  render: h => h(Test),
  mounted:function(){
    this.$axios=axios
    window.app=this
    this.errorHandler=globalErrorHandler //挂载全局异常处理
  }
}).$mount('#app')
