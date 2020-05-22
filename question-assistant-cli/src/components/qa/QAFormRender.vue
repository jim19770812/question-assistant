<template>
  <div class="qar-form-render">
    <qa-render-container></qa-render-container>
    <div class="qar-optpane">
    </div>
  </div>
</template>

<script>
import QARenderContainer from '@/components/qa/QARenderContainer'

export default {
  name: 'QAFormRender',
  data:function(){
    return {
    }
  },
  components:{
    "qa-render-container":QARenderContainer
  }
}
</script>

<style lang="less" scoped>
  @import '../../assets/styles/common.css';
  *{
    margin:0;
    padding: 0;
  }
  .qar-form-render{
    height: 800px;
    width: 100%;
    opacity:1;
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: flex-start;
    justify-items: flex-start;
    line-height: 20px;
    overflow: hidden;
    /*background-color: blue;*/
    background-color:rgba(255,255,255,1);
  }
</style>
