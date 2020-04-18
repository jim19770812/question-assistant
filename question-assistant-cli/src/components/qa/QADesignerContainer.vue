<template>
  <div>
    <div class="qad-comp-render-container">
        <div class="qad-content-pane" @dragover.prevent @drop="formItemDrop($event)">
            <div :v-if="compList.length = 0">
              请从左侧选择一个题目
            </div>
            <div :v-if="compList.length > 0">

            </div>
        </div>
    </div>
  </div>
</template>

<script>
import {mapState, mapGetters} from "vuex"
/*表单项设计器容器*/
export default {
  name: 'QaDesignerContainer',
  data:function(){
    return {
    }
  },
  created:function(){
    // console.log("test", this.$store)
  },
  computed:{
    ...mapState({
      compList:states=>states.qa.compList
    })
  },
  methods:{
    ...mapGetters({
      getCompList:"qa/getCompList"
    }),
    /**
     *
     * @param {DragEvent} event
     */
    formItemDrop(event){
      const clazz=event.dataTransfer.getData("formitem")
      console.log("drop", clazz)
    }
  }
}
</script>

<style scoped lang="less">
  .qad-comp-render-container{
    width:100%;
    height:100%;
    display: flex;
    flex-wrap: nowrap;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .qad-comp-render-container>.qad-content-pane{
    width:320px;
    height:665px;
    background:rgba(255,255,255,1);
    box-shadow:0 3px 24px rgba(0,0,0,0.08);
    font-size:12px;
    font-family:PingFang SC;
    font-weight:400;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:0.4;
    display: flex;
    justify-content: center;
    align-items: center;
  }

</style>
