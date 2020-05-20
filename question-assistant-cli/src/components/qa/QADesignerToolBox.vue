<template>
    <div class="qad-toolbox" v-show="visabled">
      <a href="#" @click="moveUpIt($event)">上移</a>
      <a href="#" @click="moveDownIt($event)">下移</a>
      <a href="#" @click="deleteIt($event)">删除</a>
    </div>
</template>

<script>
export default {
  name: 'QADesignerToolBox',
  props:["key"],
  data:function(){
    return{
    }
  },
  created:function(){

  },
  methods:{
    moveUpIt(event){

    },
    moveDownIt(event){

    },
    deleteIt(event){

    }
  },
  computed:{
    visabled(){
      return this.key===this.$store.state.qa.container.key
    }
  }
}
</script>

<style lang="less" scoped>
  .qad-toolbox{
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
  }

  .qad-toolbox .qad-toolbox-button{
    background-color: #00a57b;
    width:32px;
    height: 16px;
    text-align: center;
    padding-top: 4px;
    margin: 2px auto;
  }
</style>
