<template>
  <div>
    <div class="qad-container">
      <div class="qad-header">
        <router-link :to='{name:"qalist"}' class="qad-header-gohome">返回首页</router-link>
        <span>报名助手</span>
        <div class="qad-header-buttons-container">
          <a href="#" class="enabled">预览</a>
          <a href="#" class="enabled">保存</a>
          <a href="#" class="enabled">发布</a>
          <a href="#" class="disabled">退出</a>
        </div>
      </div>
      <div class="qad-main-container">
        <div class="qad-leftpane">
          <div class="qad-lp-qa-type">题目类型</div>
          <div class="qad-lp-uinfo">
            <span class="qad-lp-qa-items">个人信息</span>
            <div class="qad-lp-box">
              <a href="#">
                <div class="fitem_name" draggable="true" @dragstart="formItemDragStart" @dragend="formItemDragEnd">姓名</div>
              </a>
              <a href="#">
                <div class="fitem_phone" draggable="true" @dragstart="formItemDragStart" @dragend="formItemDragEnd">电话</div>
              </a>
              <a href="#">
                <div class="fitem_wechat" draggable="true" @dragstart="formItemDragStart" @dragend="formItemDragEnd">微信</div>
              </a>
              <a href="#">
                <div class="fitem_area" draggable="true" @dragstart="formItemDragStart" @dragend="formItemDragEnd">省市区</div>
              </a>
            </div>
          </div>
          <div class="qad-lp-text">
            <span class="qad-lp-qa-items" draggable="true" @dragstart="formItemDragStart" @dragend="formItemDragEnd">文本</span>
            <div class="qad-lp-box">
              <a href="#">
                <div class="fitem_text" draggable="true" @dragstart="formItemDragStart" @dragend="formItemDragEnd">多行文本</div>
            </a>
            </div>
          </div>
          <div class="qad-lp-option">
            <span class="qad-lp-qa-items">选择题</span>
            <div class="qad-lp-box">
              <a href="#">
                <div class="fitem_radio" draggable="true" @dragstart="formItemDragStart" @dragend="formItemDragEnd">单项选择</div>
              </a>
              <a href="#">
                <div class="fitem_mulselet" draggable="true" @dragstart="formItemDragStart" @dragend="formItemDragEnd">多项选择</div>
              </a>
            </div>
          </div>
        </div>
        <qa-designer-container ref="designer"></qa-designer-container>
        <qa-editor-container ref="editor"></qa-editor-container>
      </div>
    </div>
  </div>
</template>

<script>
import QaDesignerContainer from "@/components/qa/QADesignerContainer"
import QAEditorContainer from "@/components/qa/QAEditorContainer"
export default {
  name: 'QAFormDesigner',
  components: {
    'qa-designer-container':QaDesignerContainer,
    'qa-editor-container': QAEditorContainer
  },
  methods:{
    /**
     * 开始拖动表单项目
     * @param {DragEvent} event 事件对象
     * @return {void} 不返回
     */
    formItemDragStart(event){
      const clazz=event.target.getAttribute("class")
      event.dataTransfer.setData("formitem", clazz)
      event.dataTransfer.effectAllowed="move"
      console.log("drag start", clazz)
    },
    /**
     *
     * @param {DragEvent}event
     */
    formItemDragEnd(event){
      console.log("dragend")
      event.dataTransfer.clearData()
    }
  }

}
</script>

<style scoped lang="less">
  @import '../../assets/styles/common.css';
  *{
    margin:0;
    padding: 0;
  }
  .qad-container{
    background:rgba(247,247,247,1);
    opacity:1;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: center;/*水平居中*/
    justify-items: flex-start;
    line-height: 20px;
  }
  .qad-container>.qad-header{
    height: 100%;
    width: 100%;
    background:rgba(255,255,255,1);
    box-shadow:0 1px 0 rgba(220,223,230,1);
    opacity:1;
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: space-between;
    align-items: center;
  }
  .qad-header>.qad-header-gohome{
    padding-left: 24px;
    background:url("../../assets/icon_list_arrow_left.png") no-repeat transparent;
    background-size: 8px 8px;
    background-position: 10px 5px;
  }
  .qad-container>.qad-header>.qad-header-buttons-container{
    display: flex;
    flex-wrap: nowrap;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    width: 360px;
    height: 100%;
    padding-right: 24px;
  }
  .qad-container>.qad-header>.qad-header-buttons-container>*{
    text-align: center;
    width:66px;
    height:32px;
    /*background:rgba(0,165,123,1);*/
    box-shadow:0px 2px 12px rgba(0,166,122,0.2);
    opacity:1;
    border-radius:16px;
    line-height: 32px;
  }
  .qad-container>.qad-header>.qad-header-buttons-container>.enabled{
    background:rgba(0,165,123,1);
    color:white;
  }
  .qad-container>.qad-header>.qad-header-buttons-container>.disabled{
    background:rgba(235,235,235,1);
    color:rgba(51,51,51,1);
  }
  .qad-container>.qad-main-container{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: space-between;
    align-items: center;
  }
  .qad-container>.qad-main-container>.qad-leftpane{
    width:360px;
    height:1012px;
    background:rgba(255,255,255,1);
    box-shadow:5px 0px 5px rgba(23,29,62,0.05);
    opacity:1;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: flex-start;
    text-align: left;
  }
  .qad-container>.qad-main-container>.qad-leftpane>*{
    width:100%;
    margin: 16px auto;
    text-align: left;
  }
  .qad-leftpane .qad-lp-qa-type{
    height: 66px;
    line-height: 66px;
    border-bottom:2px dashed rgba(26,33,71,0.2);
    font-size:16px;
    font-family:PingFang SC;
    font-weight:500;
    color:rgba(27,31,38,1);
    /*margin: 24px;*/
    opacity:1;
  }
  .qad-leftpane .qad-lp-qa-items{
    width:52px;
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:20px;
    color:rgba(27,31,38,1);
    opacity:1;
  }
  .qad-leftpane .qad-lp-uinfo{

  }
  .qad-leftpane .qad-lp-box{
    display: flex;
    flex-wrap: wrap;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
  }
  .qad-leftpane .qad-lp-box>a{
    margin: 12px 8px;
    width:109px;
    height:32px;
    background:rgba(235,235,235,1);
    box-shadow:0 2px 16px rgba(26,33,71,0.04);
    opacity:1;
    border-radius:16px;
    text-align: center;
    padding-left: 5px;
    line-height: 32px;
    color:rgba(51,51,51,1);
  }
  .qad-lp-box .fitem_name{
    background:url("../../assets/icon_edit_name.png") no-repeat transparent;
    background-size: 16px 16px;
    background-position: 16px 6px;
  }
  .qad-lp-box .fitem_phone{
    background:url("../../assets/icon_edit_phone.png") no-repeat transparent;
    background-size: 16px 16px;
    background-position: 16px 6px;
  }
  .qad-lp-box .fitem_wechat{
    background:url("../../assets/icon_edit_wechat.png") no-repeat transparent;
    background-size: 16px 16px;
    background-position: 16px 6px;
  }
  .qad-lp-box .fitem_area{
    background:url("../../assets/icon_edit_position.png") no-repeat transparent;
    background-size: 16px 16px;
    background-position: 12px 6px;
  }
  .qad-lp-box .fitem_text{
    background:url("../../assets/icon_edit_text.png") no-repeat transparent;
    background-size: 16px 16px;
    background-position: 4px 6px;
  }
  .qad-leftpane .fitem_radio{
    background:url("../../assets/icon_edit_danxuan.png") no-repeat transparent;
    background-size: 16px 16px;
    background-position: 4px 6px;
  }
  .qad-leftpane .fitem_mulselet{
    background:url("../../assets/icon_edit_duoxuan.png") no-repeat transparent;
    background-size: 16px 16px;
    background-position: 4px 6px;
  }
  /*.qad-container>.qad-main-container>.qad-comp-editor-container{*/
  /*  width:360px;*/
  /*  height:1012px;*/
  /*  background:rgba(255,255,255,1);*/
  /*  box-shadow:-5px 0px 5px rgba(23,29,62,0.05);*/
  /*  opacity:1;*/
  /*}*/
</style>
