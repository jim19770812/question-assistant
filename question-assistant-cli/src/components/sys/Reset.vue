<template>
  <div>
    <div class="reset-container">
      <div class="reset-left-pane">
        <div class="reset-form-container">
          <div class="title">报名助手</div>
          <div class="tab-container">
            <component :v-if="compName !=''" :is="compName"></component>
          </div>
        </div>
      </div>
      <p class="right-picture">
      </p>
    </div>
  </div>
</template>

<script>
import LosePasswordComponent from '@/components/sys/LosePasswordComponent'
import NewPasswordComponent from '@/components/sys/NewPasswordComponent'
// import {mapGetters} from 'vuex'
export default {
  name: 'Reset',
  data: function () {
    return {
    }
  },
  watch:{
    '$route'(to, from){
      console.log("watch ", to, from, this.$route)
    }
  },
  computed:{
    compName:function(){
      return this.$store.getters.compName
    }
  },
  methods: {
    // ...mapGetters('reset')
  },
  components: {
    'lose-password-component':LosePasswordComponent,
    'new-password-component':NewPasswordComponent
  }
}
</script>

<style scoped lang="less">
  .reset-container{
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
  .reset-container>*{
    border: 1px solid grey;
    background-color: #fff;
    outline:none;
  }

  .right-picture{
    background-color: gray;
    background-image: url('../../assets/helloquence-OQMZwNd3ThU-unsplash@2x.png');
    height:1080px;
    width:1180px;
    overflow:hidden;
    background-repeat: no-repeat;
  }
  .reset-left-pane{/*登录容器*/
    height:100%;
    width:740px;
  }
  .reset-form-container {
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
  .reset-form-container>.title{
    font-size:24px;
    margin: 180px auto 0 0;
    color:rgba(0,167,121,1);
  }
  .reset-form-container .router-link-active{
    opacity:1;
  }
  .reset-form-container .deactive{
    opacity:0.5;
    color:rgb(26,33,71);
  }
  .reset-form-container> .tab-container{
    margin:124px auto 0 0;
    line-height: 28px;
    font-size: 20px;
    font-weight: 500;
    color:rgba(26,33,71,1);
    text-align: left;
  }
</style>
