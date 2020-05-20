<template>
  <div class="qae-item-textline">
    <div>标题</div>
    <input type="text" v-model="title"/>
    <div>校验</div>
    <div>
      <input type="checkbox" v-model="notEmpty"/><span>是否必填</span>
    </div>
    <div>
      行数
    </div>
    <input type="text" v-model.number="rows">
    <div>
      列数
    </div>
    <input type="text" v-model.number="cols">
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
    rows:{
      get(){
        const item=this.$store.state.qa.container.getItem(this.key)
        return ObjectUtlls.isNull(item)?"":item.rows
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.key))){
          const index=this.$store.state.qa.container.indexByKey(this.key)
          this.$store.commit("qa/updateValue", {path: `$.items[${index}].rows`,
            newVal: val,
            key:this.key
          })
        }
      }
    },
    cols:{
      get(){
        const item=this.$store.state.qa.container.getItem(this.key)
        return ObjectUtlls.isNull(item)?"":item.cols
      },
      set(val){
        if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.key))){
          const index=this.$store.state.qa.container.indexByKey(this.key)
          this.$store.commit("qa/updateValue", {path: `$.items[${index}].cols`,
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
  .qae-item-textline{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qae-item-textline>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qae-item-textline>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qae-item-textline>.title>.red{
    color:red
  }
  .qae-item-textline>.input{
    height:36px;
    background:rgba(246,246,246,1);
    border:1px solid rgba(222,226,230,1);
    opacity:1;
    border-radius:4px;
  }
</style>
