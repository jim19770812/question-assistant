<template>
  <div class="qad-item-weichat-box">
    <div v-bind:class="{'qad-item-weichat': true, selected: selected}"  @click="click">
      <div class="title">{{item.title}} <span class="red" v-show="item.notEmpty">*</span></div>
      <div class="input"></div>
    </div>
    <designer-tool-box></designer-tool-box>
  </div>
</template>

<script>
import QADesignerToolBox from '@/components/qa/QADesignerToolBox'
import { StringUtils } from '@/common/utils'

export default {
  name: 'QAWeixinFormItemDesigner',
  props:{
    designKey:{
      required:true,
      type:String,
      default:""
    }
  },
  data:function(){
    return {
    }
  },
  created:function(){
    if (this.designKey===""){
      this.designKey=this.$store.state.qa.container.getLastestItem().key
    }
  },
  methods:{
    click:function(){
      this.$store.commit('qa/select', this.designKey)
    }
  },
  computed:{
    item:function(){
      const ret=this.$store.state.qa.container.getItem(this.designKey)
      return ret
    },
    selected:function(){
      return this.$store.state.qa.container.key===this.designKey
    }
  },
  components:{
    "designer-tool-box":QADesignerToolBox
  },
  watch:{
    key(newVal, oldVal){
      if (!StringUtils.isBlank(oldVal)){
        console.log("微信-key发生变化", newVal, oldVal)
      }
    }
  }
}
</script>

<style lang="less" scoped>
  .qad-item-weichat-box{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items:flex-start;
  }
  .qad-item-weichat{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qad-item-weichat>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qad-item-weichat>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qad-item-weichat>.title>.red{
    color:red
  }
  .qad-item-weichat>.input{
    height:36px;
    background:rgba(246,246,246,1);
    border:1px solid rgba(222,226,230,1);
    opacity:1;
    border-radius:4px;
  }
  .selected{
    border: solid 1px #00a57b;
  }
</style>
