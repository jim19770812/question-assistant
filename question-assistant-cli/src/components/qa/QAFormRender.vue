<template>
  <div class="qar-form-render">
    <qa-viewer></qa-viewer>
    <qa-render-opt></qa-render-opt>
  </div>
</template>

<script>
import QAViewer from '@/components/qa/QAViewer'
import QARenderOpt from '@/components/qa/QARenderOpt'

export default {
  name: 'QAFormRender',
  data:function(){
    return {
    }
  },
  components:{
    "qa-viewer":QAViewer,
    "qa-render-opt": QARenderOpt
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
    height: 100%;
    width: 100%;
    opacity:1;
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: flex-start;
    justify-items: flex-start;
    line-height: 20px;
    overflow: hidden;
    background-color:rgba(255,255,255,1);
  }
</style>
