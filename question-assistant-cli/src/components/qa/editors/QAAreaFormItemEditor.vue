<template>
  <div class="qae-item-area">
    <div>标题</div>
    <input type="text" v-model="title"/>
    <div>校验</div>
    <div>
      <input type="checkbox" v-model="notEmpty"/><span>是否必填</span>
    </div>
    <div>
      <select class="input" v-model="minLevel">
        <option :value="1" selected>最小选择到市</option>
        <option :value="2">最小选择到县</option>
      </select>
    </div>
  </div>
</template>

<script>
import { ObjectUtlls } from '@/common/utils'
export default {
  name: 'QAAreaFormItemEditor',
  data:function(){
    return{
    }
  },
  computed:{
    key(){
      return this.$store.state.qa.container.key
    },
    title:{
      get(){
        const item=this.$store.state.qa.container.getItem(this.key)
        return ObjectUtlls.isNull(item)?"":item.title
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.key))){
          const index=this.$store.state.qa.container.indexByKey(this.key)
          this.$store.commit("qa/updateValue", {path: `$.items[${index}].title`,
            newVal: val,
            key:this.key
          })
        }
      }
    },
    notEmpty:{
      get(){
        const item=this.$store.state.qa.container.getItem(this.key)
        return ObjectUtlls.isNull(item)?"":item.notEmpty
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.key))){
          const index=this.$store.state.qa.container.indexByKey(this.key)
          this.$store.commit("qa/updateValue", {path: `$.items[${index}].notEmpty`,
            newVal: val,
            key:this.key
          })
        }
      }
    },
    minLenEnabled:{
      get(){
        const item=this.$store.state.qa.container.getItem(this.key)
        return ObjectUtlls.isNull(item)?"":item.minLen.enabled
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.key))){
          const index=this.$store.state.qa.container.indexByKey(this.key)
          this.$store.commit("qa/updateValue", {path: `$.items[${index}].minLen.enabled`,
            newVal: val,
            key:this.key
          })
        }
      }
    },
    minLenVal:{
      get(){
        const item=this.$store.state.qa.container.getItem(this.key)
        return ObjectUtlls.isNull(item)?"":item.minLen.val
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.key))){
          const index=this.$store.state.qa.container.indexByKey(this.key)
          this.$store.commit("qa/updateValue", {path: `$.items[${index}].minLen.val`,
            newVal: val,
            key:this.key
          })
        }
      }
    },
    maxLenEnabled:{
      get(){
        const item=this.$store.state.qa.container.getItem(this.key)
        return ObjectUtlls.isNull(item)?"":item.maxLen.enabled
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.key))){
          this.$store.commit("qa/updateValue", {path: `$.items[${this.index}].maxLen.enabled`,
            newVal: val,
            key:this.key
          })
        }
      }
    },
    maxLenVal:{
      get(){
        const item=this.$store.state.qa.container.getItem(this.key)
        return ObjectUtlls.isNull(item)?"":item.maxLen.val
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.key))){
          const index=this.$store.state.qa.container.indexByKey(this.key)
          this.$store.commit("qa/updateValue", {path: `$.items[${index}].maxLen.val`,
            newVal: val,
            key:this.key
          })
        }
      }
    },
    minLevel:{
      get(){
        const item=this.$store.state.qa.container.getItem(this.key)
        return ObjectUtlls.isNull(item)?"":item.minLevel
      },
      set(newVal){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.key))){
          const index=this.$store.state.qa.container.indexByKey(this.key)
          this.$store.commit("qa/updateValue", {path: `$.items[${index}].minLevel`,
            newVal: newVal,
            key:this.key
          })
        }
      }
    }
  }
}
</script>

<style lang="less" scoped>
  .qae-item-area{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qae-item-area>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qae-item-area>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qae-item-area>.title>.red{
    color:red
  }
  .qae-item-area .input{
    height:36px;
    background:rgba(246,246,246,1);
    border:1px solid rgba(222,226,230,1);
    opacity:1;
    border-radius:4px;
  }
</style>
