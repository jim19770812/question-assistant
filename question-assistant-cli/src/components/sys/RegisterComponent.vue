<template>
  <div>
    <div class="form-container">
      <div v-if="invalid" class="red">{{errMessage}}</div>
      <input class="account-icon" type="text" placeholder="请输入要注册的帐号" ref="account" />
      <input class="password-icon" type="password" placeholder="请输入密码" ref="password" />
      <button class="register" @click="register">注册</button>
    </div>
  </div>
</template>

<script>
import { StringUtils } from '@/common/utils'
import { register } from "@/requests/modules/user_requests"
export default {
  name: 'RegisterComponent',
  data:function(){
    return {
      invalid:false,
      errMessage:""
    }
  },
  computed:function(){
  },
  methods:{
    register(){
      this.reset()
      const account=this.$refs.account.value.trim()
      let t=StringUtils.isBlank(account)
      if (t){
        this.showError("帐号不能为空")
        return
      }
      const password=this.$refs.password.value.trim()
      t=StringUtils.isBlank(password)
      if (t){
        this.showError("密码不能为空")
        return
      }
      register(account, password).then(resp=>{
        this.$store.commit("setAuthActiveIndex", 0)
        this.$router.replace({name:"login"})
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

<style scoped>
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
  .form-container>input{
    border-radius:30px;
    border:1px solid rgba(213,223,232,1);
    height:40px;
    width:300px;
    color:rgba(187,187,187,1);
  }
  .form-container>.register{
    color:white;
    width:168px;
    height:40px;
    background:rgba(0,166,122,1);
    opacity:1;
    border-radius:25px;
  }

</style>
