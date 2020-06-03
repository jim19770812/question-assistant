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
import VueClipboard from 'vue-clipboard2'

Vue.use(VueClipboard)
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
const conf=require("./config.json")
console.log(conf)
Vue.prototype.$home_url=conf.home_url
const vue=new Vue({
  router,
  store,
  render: h => h(Test),
  methods:{
    isAuth(url){
      const urlArray=["/share", "/e404"]
      const ret=urlArray.filter(o=>{
        return url.indexOf(o)>=0
      })
      return ret.length===0
    }
  },
  created:function(){
    if (this.isAuth(this.$route.path)){
      const token=TokenUtils.getToken()
      if (token===""){
        console.log("token是空，转向登录页")
        this.$router.replace({name:"login"})
      }
    }
  },
  mounted:function(){
    window.app=this
  }
}).$mount('#app')

export default vue
