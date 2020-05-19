<template>
  <div>
    <div class="qad-comp-render-container">
<!--      <div>-->
<!--        {{this.container}}-->
<!--      </div>-->
        <div class="qad-content-pane" @dragover.prevent @drop="formItemDrop($event)">
          <div v-if="this.container.isEmpty()">
            请从左侧选择一个题目并拖拽到这里
            <button @click="showThis">showThis</button>
          </div>
            <div v-if="!this.container.isEmpty()">
              <div v-for="(comp, idx) in this.container.getItems()" :key="idx">
                <component :is="container.getDesignerClass(comp.type)" key="idx"></component>
              </div>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import {QAUtils} from '../../modules/qa.js'
import QANameFormItemDesigner from '@/components/qa/designers/QANameFormItemDesigner'
import QAPhoneFormItemDesigner from '@/components/qa/designers/QAPhoneFormItemDesigner'
import QAWeixinFormItemDesigner from '@/components/qa/designers/QAWeixinFormItemDesigner'
import QATextLineFormItemDesigner from '@/components/qa/designers/QATextLineFormItemDesigner'

/*表单项设计器容器*/
export default {
  name: 'QaDesignerContainer',
  data: function () {
    return {}
  },
  computed: {
    container () {
      return this.$store.state.qa.container
    }
  },
  methods: {
    showThis () {
    },
    /**
     *
     * @param {DragEvent} event
     */
    formItemDrop (event) {
      const clazz = event.dataTransfer.getData("formitem")
      let data = {}
      if (clazz === "fitem_name") {
        data = QAUtils.createfNameItemData("姓名", "", true)
      } else if (clazz === "fitem_phone") {
        data = QAUtils.createfPhoneItemData("", true)//手机号
      } else if (clazz === "fitem_wechat") {
        data = QAUtils.createfWeixinItemData("", true)//微信
      } else if (clazz === "fitem_area") {
        data = QAUtils.createfAreaItemData("省市区", true, "", "", "")
      } else if (clazz === "fitem_radio") {
        data = QAUtils.createfRadioItemData("单项选择", true, "", "", "")
      } else if (clazz === "fitem_mulselet") {
        data = QAUtils.createfMulseletItemData("多项选择", true, [])
      } else {
        data = QAUtils.createfTextItemData("多行文本", true)
      }
      this.$store.commit('qa/add', data)
      // this.$forceUpdate()
    }
  },
  components: {
    "item-name-designer":QANameFormItemDesigner,
    "item-phone-designer":QAPhoneFormItemDesigner,
    "item-wechat-designer":QAWeixinFormItemDesigner,
    "item-text-designer":QATextLineFormItemDesigner

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
  .selected{
    border: solid 1px #00a57b;
  }

</style>
