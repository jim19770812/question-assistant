<template>
  <div>
    <div class="auth-container">
      <div class="login-left-pane">
        <div class="auth-form-container">
            <div class="title">报名助手</div>
            <div class="tab-container">
              <router-link :class="{deactive: this.$store.getters.activeIndex!==0}" :to='{name:"login"}' @click.native="loginClick">登录</router-link>
              <router-link :class="{deactive: activeIndex!==1}" :to='{name:"register"}' @click.native="registerClick">注册</router-link>
              <router-view></router-view>
            </div>
        </div>
      </div>
      <p class="right-picture">
      </p>
    </div>

  </div>

</template>

<script>
export default {
  name: 'Auth',
  computed:{
    activeIndex:function(){
      return this.$store.getters.activeIndex
    }
  },
  methods:{
    loginClick:function(){
      this.$store.commit("loginActived")
    },
    registerClick:function(){
      this.$store.commit("registerActived")
    }
  }
}
</script>

<style scoped lang="less">
  .auth-container{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: center;
    align-items: center;
    margin:auto;
    list-style: none;
    width:1920px;
    height: 1080px;
  }
  .auth-container>*{
    border: 1px solid grey;
    background-color: #fff;
    outline:none;
  }
  .auth-container a{
    text-decoration: none;
  }
  .right-picture{
    background-color: gray;
    background-image: url('../../assets/helloquence-OQMZwNd3ThU-unsplash@2x.png');
    height:1080px;
    width:1180px;
    overflow:hidden;
    background-repeat: no-repeat;
  }
  .login-left-pane{/*登录容器*/
    height:100%;
    width:740px;
  }
  .auth-form-container {
    margin-left: 220px;
    height: 100%;
    letter-spacing:2px;
    opacity:1;
    font-family:PingFang SC;
    font-weight:600;
    line-height:28px;
    text-align: left;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: left;
    align-items: baseline;
  }
  .auth-form-container>.title{
    font-size:24px;
    margin: 180px auto 0 0;
    color:rgba(0,167,121,1);
  }
  .auth-form-container .router-link-active{
    opacity:1;
  }
  .auth-form-container .deactive{
    opacity:0.5;
    color:rgb(26,33,71);
  }
  .auth-form-container> .tab-container{
    margin:124px auto 0 0;
    line-height: 28px;
    font-size: 20px;
    font-weight: 500;
    color:rgba(26,33,71,1);
    text-align: left;
  }
  .auth-form-container> .tab-container>a:first-child{
    margin-right: 24px;
  }
</style>
