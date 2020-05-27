<template>
  <div>
    <div class="form-container">
      <div v-if="invalid" class="red">{{errMessage}}</div>
      <input class="account-icon" type="text" placeholder="请输入帐号登录" ref="account" />
      <input class="password-icon" type="text" placeholder="请输入密码" ref="password" />
      <router-link class="lostpassword" :to='{name:"losepassword"}' @click.native="goLosePassword">忘记密码,点击找回</router-link>
      <button class="next" @click="login">登录</button>
    </div>
  </div>
</template>

<script>
import { StringUtils } from '@/common/utils'

export default {/*登录组件*/
  name: 'LoginComponent',
  data:function(){
    return {
      invalid:false,
      errMessage:""
    }
  },
  methods:{
    goLosePassword(){
      // console.log("goLosePassword",this.$store.reset)
      this.$store.commit('setSelectedComponent',"lose-password-component")
      this.$router.push({name:"reset"})
    },
    login(){
      if (StringUtils.isBlank(this.$refs.account.trim())){
        this.showError("帐号不能为空")
        return
      }
      if (StringUtils.isBlank(this.$refs.password.trim())){
        this.showError("密码不能为空")
        return
      }
    },
    showError(message){
      this.invalid=true
      this.errMessage=message
    },
    reset(){
      this.invalid=false
      this.errMessage=""
    }
  }
}
</script>

<style scoped lang="less">
  .form-container{
    padding-top: 20px;
    /*background-color: #efc;*/
    display: flex;
    flex-direction: column;
    text-align: left;
    margin-left: 20px;
  }
  .form-container>*{
    margin: 20px 0 0 0;
  }
  .account-icon{
    padding-left: 32px;
    background:url('../../assets/icon_account.png') no-repeat scroll;
    background-position: 12px 13px;
  }
  .password-icon{
    padding-left: 32px;
    background:url('../../assets/icon_password.png') no-repeat scroll;
    background-position: 12px 13px;
  }
  .form-container>input[type="text"]{
    border-radius:30px;
    border:1px solid rgba(213,223,232,1);
    height:40px;
    width:300px;
    color:rgba(187,187,187,1);
    outline: none;
  }
  .form-container>.lostpassword{
    font-size:12px;
    font-family:PingFang SC;
    font-weight:400;
    line-height:16px;
    color:rgba(187,187,187,1);
    text-decoration:underline;
    opacity:1;
  }
  .form-container>.next{
    color:white;
    width:168px;
    height:40px;
    background:rgba(0,166,122,1);
    opacity:1;
    border-radius:25px;
    outline: none;
  }

</style>
