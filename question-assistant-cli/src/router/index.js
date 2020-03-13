import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [{path: '/',
  name: 'Home',
  component: Home
},{
  path:'/login',
  name:'login',
  component: resolve=>require(['@/compoments/sys/Login.vue'], resolve)
},{
  path:'/register',
  name:'register',
  component: resolve=>require(['@/compoments/sys/Register.vue'], resolve)
},{
  path:'/losepassword',
  name:'losepassword',
  component: resolve=>require(['@/compoments/sys/LosePassword.vue'], resolve)
},{
  path:'/newpassword',
  name:'newpassword',
  component: resolve=>require(['@/compoments/sys/NewPassword.vue'], resolve)
},{
  path:'/qalist',
  name:'qalist',
  component: resolve=>require(['@/compoments/qa/QAList.vue'], resolve)
},{
  path:'/qaform',
  name:'qaform',
  component: resolve=>require(['@/compoments/qa/QAForm.vue'], resolve)
}]

const router = new VueRouter({
  routes
})

export default router
