<template>
    <div class="qad-toolbox" v-show="visibled">
      <a href="#" @click="moveUpIt($event)">上移</a>
      <a href="#" @click="moveDownIt($event)">下移</a>
      <a href="#" @click="deleteIt($event)">删除</a>
    </div>
</template>

<script>
export default {
  name: 'QADesignerToolBox',
  props:["componentKey"],
  data:function(){
    return{
    }
  },
  created:function(){

  },
  methods:{
    moveUpIt(event){
      console.log("需要移动的key", this.componentKey, this)
      this.$store.commit("qa/move", {key:this.componentKey,
        incr:-1,
        vueComponent:this
      })

    },
    moveDownIt(event){
      this.$store.commit("qa/move", {key:this.componentKey,
        incr:1,
        vueComponent:this
      })
      this.refershItems()
    },
    deleteIt(event){
      this.$store.commit("qa/remove", this.componentKey)
      this.refershItems()
    },
    refershItems(){
      console.log(this.$parent)
    }
  },
  computed:{
    visibled(){
      return this.componentKey===this.$store.state.qa.container.key
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
    width: 50px;
  }

  .qad-toolbox>*{
    background: #00a57b;
    width:32px;
    height: 20px;
    text-align: center;
    padding-top: 4px;
    margin: 2px auto;
    border-radius: 4px;
    color:white;
  }
</style>
