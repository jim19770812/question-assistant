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
import QASharePortal from '@/components/qa/QASharePortal'
import E404 from '@/components/sys/E404'

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
},{
  path:'/share',
  name:'share',
  component: QASharePortal
},{
  path:'/share/:id',
  name:'share',
  component: QASharePortal
},{
  path:'/e404',
  name:'e404',
  component: E404
}]

const router = new VueRouter({
  mode:'history',
  routes
})

export default router
