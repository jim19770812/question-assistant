<template>
  <div id="app">
    <qa-header></qa-header>
    <router-view/>
  </div>
</template>

<script>
import { mapMutations} from 'vuex'
import { StringUtils, TokenUtils } from '@/common/utils'
import HeaderComponent from '@/components/sys/HeaderComponent'
export default {
  created () {
    // 页面加载时判断token，没有就登录，有就转向问题列表页
    const token = TokenUtils.getToken()
    if (StringUtils.isBlank(token)){
      //未登录，转向登录页
      this.$router.replace({"name":"login"})
    }else{
      this.$router.replace({"name":"qalist"})
    }
  },
  components:{
    "qa-header":HeaderComponent
  },
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
@import "assets/styles/common.less";
@import '~reset.css';
//@import 'element-ui/lib/theme-chalk/index.css';
@import '~element-ui/lib/theme-chalk/index.css';

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width:1180px;
  height:1080px;
  background:rgba(247,247,247,1);
  opacity:1;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
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
