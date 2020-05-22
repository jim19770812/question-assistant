<template>
  <div class="qar-container">
    <!--    <input type="text" v-model="searchKey">-->
    <!--    <button @click="test">测试</button>-->
    <!--    <button @click="test2">测试2</button>-->
    <div :class="{'qar-content-pane':true, 'align-center':this.container.isEmpty(), 'align-start':!this.container.isEmpty()}">
      <div v-if="this.container.isEmpty()">
        无可渲染内容
      </div>
      <div v-if="!this.container.isEmpty()">
        <div v-for="(item) in this.container.getItems()" :key="item.key">
          <component :is="container.getRenderClass(item.type)"></component>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
/*表单项渲染器容器*/
export default {
  name: 'QARenderContainer',
  data:function(){
    return {}
  },
  mounted () {

  },
  methods:{

  },
  computed:{
    container:function(){
      return this.$store.state.qa.container
    }
  }
}
</script>

<style lang="less" scoped>
  .qar-container{
    height:100%;
    display: flex;
    flex-wrap: nowrap;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    flex:1;
  }
  .qar-container>.qar-content-pane{
    width:320px;
    height:100%;
    min-height: 720px;
    background:rgba(255,255,255,1);
    box-shadow:0 3px 24px rgba(0,0,0,0.08);
    font-size:12px;
    font-family:PingFang SC;
    font-weight:400;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:0.4;
    display: flex;
  }
  .qar-container>.align-center{
    justify-content: center;
    align-items: center;
  }
  .qar-container>.align-start{
    justify-content: flex-start;
    align-items: flex-start;
  }
</style>
