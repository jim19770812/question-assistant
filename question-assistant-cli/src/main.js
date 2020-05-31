import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import Test from '@/views/Test'
import { globalErrorHandler } from '@/exceptions/exceptions'
import "reset.css"
import "@/assets/styles/common.less"
import 'element-ui/lib/theme-chalk/index.css'
import ElementUI, { MessageBox, Loading, Message } from 'element-ui'
import { TokenUtils } from '@/common/utils'
Vue.use(ElementUI)
Vue.use(Loading.directive);

Vue.config.productionTip = true //在非生产环境使用的警告信息，会帮助程序除错但也会带来运行开销

//初始化Vue.prototype，这样所有vue组件都有下面定义的特性
Vue.prototype.$loading = Loading.service
Vue.prototype.$msgbox = MessageBox
Vue.prototype.$alert = MessageBox.alert
Vue.prototype.$confirm = MessageBox.confirm
Vue.prototype.$prompt = MessageBox.prompt
Vue.prototype.$notify = Notification
Vue.prototype.$message = Message
Vue.prototype.$axios=axios
//挂载全局异常处理
Vue.prototype.errorHandler=globalErrorHandler

const vue=new Vue({
  router,
  store,
  render: h => h(App),
  mounted:function(){
    window.app=this
    console.log(this)
    const token=TokenUtils.getToken()
    if (token===""){
      console.log("token是空，转向登录页")
      this.$router.replace({name:"login"})
    }
  }
}).$mount('#app')

export default vue
