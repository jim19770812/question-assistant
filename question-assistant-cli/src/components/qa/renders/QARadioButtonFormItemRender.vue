<template>
  <div class="qar-item-radio-box">
    <div v-bind:class="{'qar-item-radio':true}">
      <div class="title">{{item?item.title:""}}
        <span class="red" v-if="!errVisible && item.notEmpty">*</span>
        <span v-if="errVisible" class="red">{{errMessage}}</span>
      </div>
<!--      <input class="input" type="text" v-model="val" @input="inputChange($event)" @focus="inputFocus($event)">-->
      <div v-for="(op, index) in item.options" :value="op" :key="index" >
        <input :id="namedId(index)" :name="radioName" type="radio" v-model="val" :value="op" @input="changeOptionValue(index, $event)"/>
        <label :for="namedId(index)">{{op}}</label>
      </div>
    </div>
  </div>
</template>

<script>
import { JsonPathUtils, ObjectUtlls } from '@/common/utils'
import {v1 as uuid1} from 'uuid'
export default {
  name: 'QARadioButtonFormItemRender',
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
    namedId:function(idx){
      return this.radioName+"_"+idx
    },
    verify:function(){
      const index=this.$store.state.qa.container.indexByKey(this.renderKey)
      const notEmpty=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].notEmpty`)
      const val=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].val`)
      if (notEmpty && Array.isArray(val)){
        this.errVisible=true
        this.errMessage=`${this.item.title}不能是空！`
        return
      }
    }
  }
}
</script>

<style lang="less" scoped>
  .qar-item-radio-box{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items:flex-start;
  }
  .qar-item-radio{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qar-item-radio>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qar-item-radio>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qar-item-radio>.title>.red{
    color:red
  }
  .qar-item-radio>.input{
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
