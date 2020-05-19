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
      <input type="checkbox" v-model="minLenEnabled"/><span>最少<input type="text" v-model.number="minLenVal" :disabled="!minLenEnabled">个字</span>
    </div>
    <div>
      <input type="checkbox" v-model="maxLenEnabled"/><span>最多<input type="text" v-model.number="maxLenVal" :disabled="!maxLenEnabled">个字</span>
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
    val:{
      get(){
        const item=this.$store.state.qa.container.getItem(this.key)
        return ObjectUtlls.isNull(item)?"":item.val
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.key))){
          const index=this.$store.state.qa.container.indexByKey(this.key)
          this.$store.commit("qa/updateValue", {path: `$.items[${index}].val`,
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
