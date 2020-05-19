<template>
  <div>
    <div class="new-container">
      <div>
        <router-link :to='{name:"login"}' replace>&lt;</router-link> {{title}}
      </div>
      <div class="form-container">
        <input class="password-icon" type="text" placeholder="请输入新密码" />
        <button class="next">完成</button>
      </div>
    </div>
  </div>
</template>

<script>
// import LoginComponent from '@/components/sys/LoginComponent'
// import RegisterComponent from '@/components/sys/RegisterComponent'
// import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'
export default {
  data:function(){
    return {
      title:'设置新密码'
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
