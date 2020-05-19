<template>
  <div class="qae-item-name">
    <div>标题</div>
    <input type="text" v-model="title"/>
    <div>值</div>
    <input type="text" v-model="val"/>
    <div>校验</div>
    <div>
      <input type="checkbox" v-model="notEmpty"/><span>是否必填</span>
    </div>
    <div>
      <input type="checkbox" v-model="minLenEnabled"/><span>最少<input type="text" v-model="minLenVal" :disabled="!minLenEnabled">个字</span>
    </div>
    <div>
      <input type="checkbox" v-model="maxLenEnabled"/><span>最多<input type="text" v-model="maxLenVal" :disabled="!maxLenEnabled">个字</span>
    </div>
  </div>
</template>

<script>
import { ObjectUtlls } from '@/common/utils'

export default {
  name: 'item-name-editor',
  data:function(){
    return{
    }
  },
  computed:{
    index:function(){
      return this.$store.state.qa.container.index
    },
    title:{
      get(){
        return ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())?"":this.$store.state.qa.container.getSelectedItem().title
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())){
          const path=`$.items[${this.index}].title`
          this.$store.commit("qa/updateValue", {path: path,
            newVal: val
          })
        }
      }
    },
    val:{
      get(){
        return ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())?"":this.$store.state.qa.container.getSelectedItem().val
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())){
          const path=`$.items[${this.index}].val`
          this.$store.commit("qa/updateValue", {path: path,
            newVal: val
          })
        }
      }
    },
    notEmpty:{
      get(){
        return ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())?"":this.$store.state.qa.container.getSelectedItem().notEmpty
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())){
          const path=`$.items[${this.index}].notEmpty`
          this.$store.commit("qa/updateValue", {path: path,
            newVal: val
          })
        }
      }
    },
    minLenEnabled:{
      get(){
        return ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())?"":this.$store.state.qa.container.getSelectedItem().minLen.enabled
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())){
          const path=`$.items[${this.index}].minLen.enabled`
          this.$store.commit("qa/updateValue", {path: path,
            newVal: val
          })
        }
      }
    },
    minLenVal:{
      get(){
        return ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())?"":this.$store.state.qa.container.getSelectedItem().minLen.val
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())){
          const path=`$.items[${this.index}].minLen.val`
          this.$store.commit("qa/updateValue", {path: path,
            newVal: val
          })
        }
      }
    },
    maxLenEnabled:{
      get(){
        return ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())?"":this.$store.state.qa.container.getSelectedItem().maxLen.enabled
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())){
          this.$store.commit("qa/updateValue", {path: `$.items[${this.index}].maxLen.enabled`,
            newVal: val
          })
        }
      }
    },
    maxLenVal:{
      get(){
        return ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())?"":this.$store.state.qa.container.getSelectedItem().maxLen.val
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getSelectedItem())){
          const path=`$.items[${this.index}].maxLen.val`
          this.$store.commit("qa/updateValue", {path: path,
            newVal: val
          })
        }
      }
    }
  }
}
</script>

<style lang="less" scoped>
  .qae-item-name{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qae-item-name>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qae-item-name>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qae-item-name>.title>.red{
    color:red
  }
  .qae-item-name>.input{
    height:36px;
    background:rgba(246,246,246,1);
    border:1px solid rgba(222,226,230,1);
    opacity:1;
    border-radius:4px;
  }
</style>
