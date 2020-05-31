<template>
  <div class="qar-item-photo-box">
    <div v-bind:class="{'qad-item-photo':true}">
      <div class="title">{{item?item.title:""}}
        <span class="red" v-if="!errVisible && item.notEmpty">*</span>
        <span v-if="errVisible" class="red">{{errMessage}}</span>
      </div>
      <input class="input" type="text" v-model="val" @input="inputChange($event)" @focus="inputFocus($event)">
    </div>
  </div>
</template>

<script>
import { JsonPathUtils, ObjectUtlls } from '@/common/utils'
export default {
  name: 'QAPhoneFormItemRender',
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
    item(){
      return this.$store.state.qa.container.getItem(this.renderKey)
    },
    container(){
      return this.$store.state.qa.container
    }
  },
  methods:{
    /**
     * 校验表单元素
     * @returns {boolean}
     */
    verify:function(){
      const index=this.$store.state.qa.container.indexByKey(this.renderKey)
      const notEmpty=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].notEmpty`)
      const val=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].val`)
      if (notEmpty && val===""){
        this.errVisible=true
        this.errMessage=`${this.item.title}不能是空！`
        return false
      }
      return true
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
  .qar-item-photo-box{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items:flex-start;
  }
  .qad-item-photo{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qad-item-photo>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qad-item-photo>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qad-item-photo>.title>.red{
    color:red
  }
  .qad-item-photo>.input{
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
