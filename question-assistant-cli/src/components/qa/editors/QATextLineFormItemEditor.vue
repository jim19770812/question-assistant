<template>
  <div class="qae-item-name">
    <div>标题</div>
    <input type="text" v-model="title"/>
    <div>值</div>
    <input type="text"/>
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
  name: 'QATextLineFormItemEditor',
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
    }
  }
}
</script>

<style scoped>

</style>
