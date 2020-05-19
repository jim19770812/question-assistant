<template>
  <div>
    <div class="qae-item-phone">
      <div>手机号</div>
      <input type="text" v-model="val"/>
      <div>校验</div>
      <div>
        <input type="checkbox" v-model="notEmpty"/><span>是否必填</span>
      </div>
    </div>
  </div>

</template>

<script>
import { ObjectUtlls } from '@/common/utils'

export default {
  name: 'QAPhoneFormItemEditor',
  data:function(){
    return {

    }
  },
  computed:{
    index:function(){
      return this.$store.state.qa.container.index
    },
    val:{
      get(){
        return this.$store.state.qa.container.getItem(this.index).val
      },
      set(newVal){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())){
          const path=`$.items[${this.index}].val`
          this.$store.commit("qa/updateValue", {path: path,
            newVal: newVal
          })
        }
      }
    },
    notEmpty:{
      get(){
        return this.$store.state.qa.container.getItem(this.index).notEmpty
      },
      set(newVal){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())){
          const path=`$.items[${this.index}].notEmpty`
          this.$store.commit("qa/updateValue", {path: path,
            newVal: newVal
          })
        }
      }
    }
  }
}
</script>

<style lang="less" scoped>
  .qae-item-phone{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qae-item-phone>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qae-item-phone>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qae-item-phone>.title>.red{
    color:red
  }
  .qae-item-phone>.input{
    height:36px;
    background:rgba(246,246,246,1);
    border:1px solid rgba(222,226,230,1);
    opacity:1;
    border-radius:4px;
  }

</style>
