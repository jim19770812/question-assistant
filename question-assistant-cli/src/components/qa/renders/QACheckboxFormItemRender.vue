<template>
  <div class="qar-item-checkbox-box">
    <div v-bind:class="{'qar-item-checkbox':true}">
      <div class="title">{{item?item.title:""}}
        <span class="red" v-if="!errVisible && item.notEmpty">*</span>
        <span v-if="errVisible" class="red">{{errMessage}}</span>
      </div>
      <div v-for="(op, index) in item.options" :key="op">
        <input :id="namedId(index)" type="checkbox" :value="op" :checked="checked(op)" :key="index" v-model="val" @change="checkedThis"/>
        <label :for="namedId(index)">{{op}}</label>
      </div>
    </div>
  </div>
</template>

<script>
import { JsonPathUtils, ObjectUtlls } from '@/common/utils'
import {v1 as uuid1} from 'uuid'
export default {
  name: 'QACheckboxFormItemRender',
  props:{
    renderKey:{
      required:true,
      type:String
    }
  },
  created:function(){
    this.radioName=uuid1()
  },
  data:function(){
    return {
      errVisible:false,
      errMessage:"",
      radioName:""
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
    checked(op){
      const ret=op in this.item.options
      return ret
    },
    namedId:function(idx){
      return this.radioName+"_"+idx
    },
    /**
     * 校验表单元素
     * @returns {boolean}
     */
    verify:function(){
      const index=this.$store.state.qa.container.indexByKey(this.renderKey)
      const notEmpty=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].notEmpty`)
      const val=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].val`)
      if (notEmpty && Array.isArray(val) && val.length===0){
        this.errVisible=true
        this.errMessage=`${this.item.title}不能是空！`
        return false
      }
      const minSelection=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].minSelection`)
      if (minSelection.enabled && val.length>0 && minSelection.val>0 && val.length<minSelection.val){
        this.errVisible=true
        this.errMessage=`${this.item.title}最少可选${minSelection.val}项！`
        return false
      }
      const maxSelection=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].maxSelection`)
      if (maxSelection.enabled && maxSelection.val>0 && val.length>0 && val.length>maxSelection.val){
        this.errVisible=true
        this.errMessage=`${this.item.title}最多可选${maxSelection.val}项！`
        return false
      }
      return true
    },
    checkedThis:function(){
      if (this.errVisible){
        this.errVisible=false
        this.errMessage=""
      }
    }
  }
}
</script>

<style lang="less" scoped>
  .qar-item-checkbox-box{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items:flex-start;
  }
  .qar-item-checkbox{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qar-item-checkbox>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qar-item-checkbox>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qar-item-checkbox>.title>.red{
    color:red
  }
  .qar-item-checkbox>.input{
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
