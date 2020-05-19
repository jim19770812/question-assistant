<template>
  <div v-bind:class="{'qad-item-name':true, selected:selected}"  @click="click">
    <div class="title">{{item?item.title:""}} <span class="red" v-show="item">*</span></div>
    <div class="input"></div>
  </div>
</template>

<script>
import {ObjectUtlls} from '@/common/utils'

export default {
  name: 'QANameFormItemDesigner',
  data:function(){
    return {
      key:""
    }
  },
  created:function(){
    this.key=this.$store.state.qa.container.getLastestItem().key
  },
  methods:{
    click:function(){
      this.$store.commit('qa/select', this.key)
    }
  },
  computed:{
    container:function(){
      return this.$store.state.qa.container
    },
    item:function(){
      const ret=this.$store.state.qa.container.getItem(this.key)
      return ret
    },
    selected:function(){
      return this.$store.state.qa.container.key===this.key
    }
  }
}
</script>

<style scoped>
  .qad-item-name{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qad-item-name>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qad-item-name>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qad-item-name>.title>.red{
    color:red
  }
  .qad-item-name>.input{
    height:36px;
    background:rgba(246,246,246,1);
    border:1px solid rgba(222,226,230,1);
    opacity:1;
    border-radius:4px;
  }

</style>
