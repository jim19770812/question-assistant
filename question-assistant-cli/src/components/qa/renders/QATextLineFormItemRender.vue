<template>
  <div class="qar-item-name-box">
    <div v-bind:class="{'qar-item-name':true}">
      <div class="title">{{item?item.title:""}}
        <span class="red" v-if="!errVisible && item.notEmpty">*</span>
        <span v-if="errVisible" class="red">{{errMessage}}</span>
      </div>
      <textarea class="input" :cols="cols" :rows="rows" v-model="val" @input="inputChange($event)" @focus="inputFocus($event)"/>
    </div>
  </div>
</template>

<script>
import { JsonPathUtils, ObjectUtlls } from '@/common/utils'
export default {
  name: 'QATextLineFormItemRender',
  props:{
    renderKey:{
      required:true,
      type:String
    }
  },
  data:function(){
    return {
      errVisible:false,
      errMessage:""
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
    cols:{
      get(){
        return this.$store.state.qa.container.getItem(this.renderKey).cols
      },
      set(newVal){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.renderKey))){
          const index=this.$store.state.qa.container.indexByKey(this.renderKey)
          this.$store.commit("qa/updateValue", {path: `$.items[${index}].cols`,
            newVal: newVal,
            key:this.renderKey
          })
        }
      }
    },
    rows:{
      get(){
        return this.$store.state.qa.container.getItem(this.renderKey).rows
      },
      set(newVal){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.renderKey))){
          const index=this.$store.state.qa.container.indexByKey(this.renderKey)
          this.$store.commit("qa/updateValue", {path: `$.items[${index}].rows`,
            newVal: newVal,
            key:this.renderKey
          })
        }
      }
    },
    item(){
      return this.$store.state.qa.container.getItem(this.renderKey)
    },
    container(){
      return this.$store.state.qa.container
    }
  },
  methods:{
    verify:function(){
      const index=this.$store.state.qa.container.indexByKey(this.renderKey)
      const notEmpty=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].notEmpty`)
      const val=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].val`)
      if (notEmpty && val===""){
        this.errVisible=true
        this.errMessage=`${this.item.title}不能是空！`
        return
      }
      const minLen=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].minLen`)
      if (minLen.enabled && val.length>0 && minLen.val>0 && val.length<=minLen.val){
        this.errVisible=true
        this.errMessage=`${this.item.title}长度不足[${minLen.val}]！`
        return
      }
      const maxLen=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].maxLen`)
      if (maxLen.enabled && maxLen.val>0 && val.length>0 && val.length>maxLen.val){
        this.errVisible=true
        this.errMessage=`${this.item.title}长度超过限制[${maxLen.val}]！`
        return
      }
    },
    inputChange:function(event){
      if (this.errVisible) {
        this.errVisible=false;
        this.errMessage=''
      }
    },
    inputFocus:function(event){
      event.target.select()
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
  .qar-item-name{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qar-item-name>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qar-item-name>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qar-item-name>.title>.red{
    color:red
  }
  .qar-item-name>.input{
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
