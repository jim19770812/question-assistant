<template>
  <div class="home">
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import QAList from '@/components/qa/QAList.vue'
import Login from '@/components/sys/Login.vue'

export default {
  name: 'Home',
  components: {
    QAList,
    Login
  },
  created () {
    //判断是否登录,如果没有登录过就转向登录页,否则转向问题列表页
  }
}
</script>
