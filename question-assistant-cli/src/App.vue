<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>
      <router-link to="/auth/login">认证页</router-link>
      <router-link to="/reset/1" @click.native="goLosePassword">重置密码</router-link>
      <router-link to="/reset/2" @click.native="goNewPassword">新设密码</router-link>
      <router-link to="/qalist">问题列表</router-link>
      <router-link to="/qadesigner">问题表单设计器</router-link>
      <router-link to="/qarender">问题表单渲染器</router-link>
    </div>
    <keep-alive>
      <router-view/>
    </keep-alive>
  </div>
</template>

<script>
import {mapMutations} from 'vuex'
export default{
  methods:{
    ...mapMutations(['/reset']),
    goLosePassword(){
      console.log("goLosePassword",this.$store.reset)
      this.$store.commit('setSelectedComponent',"lose-password-component")
      this.$router.push({name:"reset"})
    },
    goNewPassword(){
      console.log("goNewPassword",this.$store.reset)
      this.$store.commit('setSelectedComponent',"new-password-component")
      this.$router.push({name:"reset"})
    }
  }
}
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;
    margin:0 10px 0 10px;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
