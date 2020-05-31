<template>
  <div class="qad-item-area-box">
    <div v-bind:class="{'qad-item-area':true, selected:selected}"  @click="click">
      <div class="title">{{item?item.title:""}} <span class="red" v-show="item.notEmpty">*</span>
      </div>
      <div class="input"><div class="input-offset">省份</div></div>
      <div class="input"><div class="input-offset">城市</div></div>
      <div class="input"><div class="input-offset">区县</div></div>
    </div>
    <designer-tool-box></designer-tool-box>
  </div>
</template>

<script>
import QADesignerToolBox from '@/components/qa/QADesignerToolBox'

export default {
  name: 'QAAreaFormItemDesigner',
  props:{
    designKey:{
      required:true,
      type:String
    }
  },
  data:function(){
    return {
      key:""
    }
  },
  created:function(){
    if (this.key===""){
      this.key=this.$store.state.qa.container.getLastestItem().key
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
      console.log("地区-key发生变化", newVal, oldVal)
    }
  }
}
</script>

<style lang="less" scoped>
@import "../../../assets/styles/common.less";
.qad-item-area-box{
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: flex-start;
  align-items:flex-start;
}
.qad-item-area{
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  justify-content: flex-start;
  align-items: center;
}
.qad-item-area>*{
  margin: 12px 20px;
  width:280px;
  text-align: left;
}
.qad-item-area>.title{
  height:18px;
  font-size:13px;
  font-family:PingFang SC;
  font-weight:500;
  line-height:16px;
  color:rgba(51,51,51,1);
  opacity:1;
}
.qad-item-area>.title>.red{
  color:red
}
.qad-item-area>.input{
  height:36px;
  background:rgba(246,246,246,1);
  border:1px solid rgba(222,226,230,1);
  opacity:1;
  border-radius:4px;
}
.qad-item-area>.input> *{
  margin: 8px auto auto 4px;
}
.selected{
  border: solid 1px #00a57b;
}
</style>

