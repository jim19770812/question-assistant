<template>
  <div class="qad-item-name-box">
    <div v-bind:class="{'qad-item-name':true, selected:selected}"  @click="click">
      <div class="title">{{item?item.title:""}} <span class="red" v-show="item.notEmpty">*</span></div>
      <div class="input"></div>
    </div>
    <designer-tool-box :componentKey="item.key"></designer-tool-box>
  </div>
</template>

<script>
import QADesignerToolBox from '@/components/qa/QADesignerToolBox'
export default {
  name: 'QANameFormItemDesigner',
  data:function(){
    return {
      key:""
    }
  },
  created:function(){
    console.log("重设名字组件的key-before", this.key)
    if (this.key===""){
      this.key=this.$store.state.qa.container.getLastestItem().key
      console.log("重设名字组件的key", this.key)
    }
  },
  methods:{
    click:function(){
      this.$store.commit('qa/select', this.key)
    }
  },
  computed:{
    container:function(){
      return this.$store.state.qa.container
    },
    item:function(){
      const ret=this.$store.state.qa.container.getItem(this.key)
      return ret
    },
    selected:function(){
      return this.$store.state.qa.container.key===this.key
    }
  },
  components:{
    "designer-tool-box":QADesignerToolBox
  },
  watch:{
    key(newVal, oldVal){
      console.log("名字-key发生变化", newVal, oldVal)
    }
  }
}
</script>

<style lang="less" scoped>
  .qad-item-name-box{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items:flex-start;
  }
  .qad-item-name{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qad-item-name>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qad-item-name>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qad-item-name>.title>.red{
    color:red
  }
  .qad-item-name>.input{
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
