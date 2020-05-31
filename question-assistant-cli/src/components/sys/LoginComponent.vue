<template>
  <div>
    <div class="form-container">
      <div v-if="invalid" class="red">{{errMessage}}</div>
      <input class="account-icon" type="text" placeholder="请输入帐号登录" ref="account" value="hanxiaofeng77@163.com" />
      <input class="password-icon" type="password" placeholder="请输入密码" ref="password" value="jim" />
      <router-link class="lostpassword" :to='{path:"/reset", query:"lose-password-component"}' @click.native="goLosePassword">忘记密码,点击找回</router-link>
      <button class="next" @click="loginClick">登录</button>
    </div>
  </div>
</template>

<script>
import { NoticeUtils, StringUtils, TokenUtils } from '@/common/utils'
import { login, createRequestWithoutNotice } from '@/requests/modules/user_requests'

export default {/*登录组件*/
  name: 'LoginComponent',
  data:function(){
    return {
      invalid:false,
      errMessage:""
    }
  },
  methods:{
    goLosePassword(){ ////转向重置密码页面
      this.reset()
      this.$store.commit("saveTicket", "")//清空ticket
      this.$router.replace({path:"/reset", query:{compName:"lose-password-component"}})//跳转到忘记密码页面
    },
    loginClick(){
      this.reset()
      let t=StringUtils.isBlank(this.$refs.account.value.trim())
      if (t){
        this.showError("帐号不能为空")
        return
      }
      t=StringUtils.isBlank(this.$refs.password.value.trim())
      if (t){
        this.showError("密码不能为空")
        return
      }
      login(this.$refs.account.value, this.$refs.password.value).then(resp=>{
        TokenUtils.saveToken(resp.ret.token)

        //保存用户信息
        this.$store.commit("setUserInfo", {
          usrId: resp.ret.usr_id,
          usrEmail:resp.ret.usr_email,
          usrName:resp.ret.usr_name
        })

        this.$router.push({name:"qalist"})
      }).catch(e=>{
        this.showError(e.message)
      })
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
    width:300px;
    background:url('../../assets/icon_account.png') no-repeat scroll;
    background-position: 12px 13px;
  }
  .password-icon{
    padding-left: 32px;
    width:300px;
    background:url('../../assets/icon_password.png') no-repeat scroll;
    background-position: 12px 13px;
  }
  .form-container>input{
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
    border:none;
  }
</style>
