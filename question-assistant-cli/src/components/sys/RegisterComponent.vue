<template>
  <div>
    <div class="form-container">
      <input class="account-icon" type="text" placeholder="请输入要注册的帐号" />
      <input class="password-icon" type="text" placeholder="请输入密码" />
      <button class="register">注册</button>
    </div>

  </div>
</template>

<script>
export default {
  name: 'RegisterComponent'
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
  .form-container>input[type="text"]{
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
