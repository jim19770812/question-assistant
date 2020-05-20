<template>
  <div class="qae-item-weichat">
    <div>微信</div>
    <div></div>
    <div>校验</div>
    <div>
      <input type="checkbox" v-model="notEmpty"/><span>是否必填</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'QAWeixinFormItemEditor',
  data:function(){
    return {
    }
  },
  computed:{
    key(){
      return this.$store.state.qa.container.key
    },
    notEmpty:{
      get(){
        return this.$store.state.qa.container.getItem(this.key).notEmpty
      },
      set(newVal){
        const index=this.$store.state.qa.container.indexByKey(this.key)
        this.$store.commit("qa/updateValue", {path: `$.items[${index}].notEmpty`,
          newVal: newVal,
          key:this.key
        })
      }
    }
  }
}
</script>

<style lang="less" scoped>
  .qae-item-weichat{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qae-item-weichat>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qae-item-weichat>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qae-item-weichat>.title>.red{
    color:red
  }
  .qae-item-weichat>.input{
    height:36px;
    background:rgba(246,246,246,1);
    border:1px solid rgba(222,226,230,1);
    opacity:1;
    border-radius:4px;
  }

</style>

