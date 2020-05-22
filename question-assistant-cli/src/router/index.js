import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Auth from '@/components/sys/Auth.vue'

import QAFormDesigner from '@/components/qa/QAFormDesigner'
import QAList from '@/components/qa/QAList'
import LoginComponent from '@/components/sys/LoginComponent'
import RegisterComponent from '@/components/sys/RegisterComponent'
import Reset from '@/components/sys/Reset'
import QAFormRender from '@/components/qa/QAFormRender'

Vue.use(VueRouter)

const routes = [{path: '/',
  name: 'Home',
  component: Home
}, {
  path: '/auth',
  name: 'auth',
  component: Auth,
  children: [{
    path: 'login',
    name: 'login',
    component: LoginComponent
  }, {
    path: 'register',
    name: "register",
    component: RegisterComponent
  }]
},{
  path:'/reset',
  name:'reset',
  component: Reset
// },
// },{ path:'/reset',
//   name:'reset',
//   component: Reset,
//   children:[{
//     path:'losepassword',
//     name:'losepassword',
//     component:LosePasswordComponent
//   },{
//     path:'newpassword',
//     name:'newpassword',
//     component:NewPasswordComponent
//   }]
}, {
  path: '/qalist',
  name: 'qalist',
  component: QAList
},{
  path: '/qadesigner',
  name: 'qadesigner',
  component: QAFormDesigner
},{
  path:'/qarender',
  name:'qarender',
  component: QAFormRender,
}]

const router = new VueRouter({
  mode:'history',
  routes
})

export default router
