<template>
  <div v-bind:class="{'qad-item-name':true, selected:selected}"  @click="click">
    <div class="title">{{item.title}} <span class="red" v-show="item.notEmpty">*</span></div>
    <div class="input">{{item.val}}</div>
  </div>
</template>

<script>
export default {
  name: 'QANameFormItemDesigner',
  data:function(){
    return {
      index:-1
    }
  },
  created:function(){
    this.index=Math.max(this.$store.state.qa.container.items.length-1,0)
  },
  methods:{
    click:function(){
      this.$store.commit('qa/select', this.index)
    }
  },
  computed:{
    item:function(){
      if (this.$store.state.qa.container.index<0){
        return null
      }
      const ret=this.$store.state.qa.container.getSelectedItem()
      return ret
    },
    selected:function(){
      return this.$store.state.qa.container.index===this.index
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
