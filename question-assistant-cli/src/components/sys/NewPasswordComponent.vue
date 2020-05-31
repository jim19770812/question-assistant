<template>
  <div>
    <div class="new-container">
      <div>
        <router-link :to='{name:"login"}' replace>&lt;</router-link> {{title}}
      </div>
      <div class="form-container">
        <input type="hidden" :value="this.$store.state.resetInfo.ticket">
        <input class="password-icon" type="text" placeholder="请输入邮箱中的验证码" ref="code" />
        <input class="password-icon" type="text" placeholder="请输入新密码" ref="password" />
        <button class="next" @click="done">完成</button>
      </div>
    </div>
  </div>
</template>

<script>
import { resetPasswordValidateCode } from '@/requests/modules/user_requests'
import { NoticeUtils } from '@/common/utils'

export default {
  //密码找回第二步
  data:function(){
    return {
      title:'设置新密码'
    }
  },
  methods:{
    done(){
      const ticket=this.$store.state.resetInfo.ticket
      const code=this.$refs.code.value.trim()
      const password=this.$refs.password.value.trim()
      resetPasswordValidateCode(ticket, code, password).then(data=>{
        NoticeUtils.info("密码重置完成，新密码请记牢")
      }).catch(e=>{
        NoticeUtils.error(e.message)
      })
    }
  }
}
</script>

<style scoped lang="less">
  .new-container{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: left;
    align-items: top;
    margin:auto;
    list-style: none;
    width:720px;
  }
  .new-container>*{
    background-color: #fff;
    outline: none;
  }
  .new-container a{
    text-decoration: none;
  }
  .form-container{
    padding-top: 20px;
    display: flex;
    flex-direction: column;
    text-align: left;
  }
  .form-container>*{
    margin: 20px 0 0 0;
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
