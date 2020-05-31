<template>
  <div class="qad-item-text-box">
    <div v-bind:class="{'qad-item-text':true, selected:selected}"  @click="click">
      <div class="title">{{item?item.title:""}} <span class="red" v-show="item.notEmpty">*</span></div>
      <textarea class="input" :cols="cols" :rows="rows" readonly disabled></textarea>
      <div class="input"></div>
    </div>
    <designer-tool-box></designer-tool-box>
  </div>
</template>

<script>
import QADesignerToolBox from '@/components/qa/QADesignerToolBox'
export default {
  name: 'QATextLineFormItemDesigner',
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
    container:function(){
      return this.$store.state.qa.container
    },
    item:function(){
      const ret=this.$store.state.qa.container.getItem(this.designKey)
      return ret
    },
    selected:function(){
      return this.$store.state.qa.container.key===this.designKey
    },
    cols:function(){
      return this.item.cols
    },
    rows:function(){
      return this.item.rows
    }
  },
  components:{
    "designer-tool-box":QADesignerToolBox
  },
  watch:{
    key(newVal, oldVal){
      console.log("多行文本-key发生变化", newVal, oldVal)
    }
  }
}
</script>

<style lang="less" scoped>
  .qad-item-text-box{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items:flex-start;
  }
  .qad-item-text{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qad-item-text>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qad-item-text>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qad-item-text>.title>.red{
    color:red
  }
  .qad-item-text>.input{
    /*height:108px;*/
    background:rgba(246,246,246,1);
    border:1px solid rgba(222,226,230,1);
    opacity:1;
    border-radius:4px;
    resize: none;
  }
  .selected{
    border: solid 1px #00a57b;
  }
</style>
