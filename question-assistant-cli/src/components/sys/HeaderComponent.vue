<template>
  <div class="qa-list-header">
    <div>
      报名助手
    </div>
    <div class="qa-list-header-user-container">
      <div class="qa-list-header-user-flex">
        <a href="#" class="qa-return-home">返回首页</a>
        <span class="qa-default-user-icon"></span>
        <span class="qa-default-user-name" @click="logoutDropdownClick">{{this.$store.state.usrInfo}}</span>
        <span class="arrow-down" @click="logoutDropdownClick"></span>
      </div>
      <div v-if="logoutInfo.visible" class="qa-list-header-user-opt-list-container">
        <div class="qa-list-header-user-opt-list">
          <a href="#" class="logout-txt" @click="logout">退出登录</a>
          <a href="#" class="logout-button"></a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HeaderComponent',
  data:function(){
    return {
      logoutInfo:{ //登出信息
        visible:false
      }
    }
  },
  methods:{
    logoutDropdownClick(){ //登出下拉菜单点击事件
      this.logoutInfo.visible=!this.logoutInfo.visible
    },
    logout(){ //登出
      this.$store.commit("logout")
    },
  },
  computed:{

  }
}
</script>

<style lang="less" scoped>
  .qa-list-header{
    display: flex;
    flex-direction: row;
    height: 68px;
    flex-wrap: nowrap;
    justify-content: space-between;
    justify-items: center;
    box-sizing: border-box;
    border:1px solid red;
  }
  .qa-list-header>*{
    padding: 20px;
  }
  .qa-list-header-user-container{
    display: flex;
    flex-direction: column;
    z-index: 1;
  }
  .qa-list-header-user-container>.qa-list-header-user-flex{
    display: flex;
    flex-wrap: nowrap;
    flex-direction: row;
    justify-content: flex-end; /*右侧对齐*/
  }
  .qa-list-header-user-container .qa-default-user-name{
    margin: 4px;
  }
  .qa-list-header-user-flex .qa-return-home{
    font-size:14px;
    font-family:PingFang SC;
    font-weight:400;
    line-height:20px;
    color:rgba(27,31,38,1);
    opacity:1;
    margin-right: 48px;
  }
  .qa-list-header-user-container .qa-default-user-icon{
    display: inline-block;
    width: 28px;
    height: 28px;
    background: url("../../assets/default-user-logo.png") no-repeat center center;
    background-size: 28px 28px;
    margin: 0 12px 0 0;
  }
  .qa-list-header-user-container .arrow-down {
    width: 0;
    height: 0;
    border-top: 8px solid rgba(26,33,71,1);
    opacity: 1;
    border-left: 6px solid transparent;
    border-right: 6px solid transparent;
    margin: 10px 0 0 4px;
  }
  .qa-list-header-user-container>.qa-list-header-user-opt-list-container{
    z-index: 2;
    display: block;
    width:240px;
    height:56px;
  }
  .qa-list-header-user-container>.qa-list-header-user-opt-list-container>.qa-list-header-user-opt-list{
    display: flex;
    flex-wrap: nowrap;
    flex-direction: row;
    justify-content: space-between;
    //justify-items: center;
    align-items: center;
    background:rgba(255,255,255,0.93);
    border:1px solid rgba(233,233,233,1);
    box-shadow:0 2px 6px rgba(188,188,188,0.5);
    opacity:1;
    border-radius:10px;
    margin-top: 4px;
    height: 36px;
    /*z-index: 3;*/
  }
  .qa-list-header-user-opt-list>*{
    margin: 20px 20px;
  }
  .qa-list-header-user-opt-list>.logout-txt{
    /*width:48px;*/
    /*height:17px;*/
    font-size:12px;
    font-family:PingFang SC;
    font-weight:400;
    line-height:17px;
    /*-webkit-text-stroke:1 rgba(0,0,0,0.00);*/
    text-stroke:1 rgba(0,0,0,0.00);
    color:#F45738;
    opacity:1;
    /*margin:20px 18px;*/
  }
  .qa-list-header-user-opt-list>.logout-button{
    background-image:url("../../assets/icon_quit.png");
    background-size: 10px 10px;
    background-repeat: no-repeat;
    width: 10px;
    height: 10px;
    outline: none;
    text-decoration: none;
  }
</style>
