<template>
  <div>
    <div class="lose-container">
      <div>
        <router-link :to='{name:"login"}' replace>&lt;</router-link> {{title}}
      </div>
      <div class="form-container">
        <input class="account-icon" type="text" placeholder="请输入您的注册邮箱" ref="mail" />
        <button class="next" @click="next">下一步</button>
      </div>
    </div>
  </div>
</template>

<script>
// import LoginComponent from '@/components/sys/LoginComponent'
// import RegisterComponent from '@/components/sys/RegisterComponent'
// import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
import { resetPasswordSendMail } from '@/requests/modules/user_requests'
import { NoticeUtils } from '@/common/utils'

export default {
  //密码找回第一步
  name:'losepassword',
  data:function(){
    return {
      title:'忘记密码'
    }
  },
  methods:{
    next(){ //下一步
      const mail=this.$refs.mail.value.trim()
      resetPasswordSendMail(mail).then(data=>{
        console.log("resetPasswordSendMail->", data)
        this.$router.replace({path:"/reset", query:{compName:"new-password-component"}})
      }).catch(e=>{
        NoticeUtils.error(e.message)
      })
    }
  }
}
</script>

<style scoped lang="less">
  .lose-container{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: left;
    align-items: top;
    margin:auto;
    list-style: none;
    width:720px;
  }
  .lose-container>*{
    background-color: #fff;
    outline: none;
  }
  .lose-container a{
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
  .account-icon{
    padding-left: 32px;
    background:url('../../assets/icon_account.png') no-repeat scroll;
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
