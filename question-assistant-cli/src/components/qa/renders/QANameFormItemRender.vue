<template>
  <div class="qar-item-name-box">
    <div v-bind:class="{'qad-item-name':true, selected:selected}"  @click="click">
      <div class="title">{{item?item.title:""}} <span class="red" v-show="item.notEmpty">*</span></div>
      <input class="input" type="text" v-model="val">
    </div>
  </div>
</template>

<script>
import { ObjectUtlls } from '@/common/utils'

export default {
  name: 'QANameFormItemRender',
  props:{
    renderKey:{
      required:true,
      type:String
    }
  },
  mounted:function(){
    console.log("renderKey", this.renderKey)
  },
  data:function(){
    return{
      data:{}
    }
  },
  computed:{
    val:{
      get(){
        return this.$store.state.qa.container.getItem(this.renderKey).val
      },
      set(newVal){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.renderKey))){
          const index=this.$store.state.qa.container.indexByKey(this.renderKey)
          this.$store.commit("qa/updateValue", {path: `$.items[${index}].val`,
            newVal: newVal,
            key:this.renderKey
          })
        }
      }
    },
    item(){
      return this.$store.state.qa.container.getItem(this.renderKey)
    }
  }
}
</script>

<style lang="less" scoped>
  .qar-item-name-box{
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
