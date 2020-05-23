<template>
  <div class="qad-comp-render-container">
<!--    <input type="text" v-model="searchKey">-->
<!--    <button @click="test">测试</button>-->
<!--    <button @click="test2">测试2</button>-->
      <div :class="{'qad-content-pane':true, 'align-center':this.container.isEmpty(), 'align-start':!this.container.isEmpty()}" @dragover.prevent @drop="formItemDrop($event)">
        <div v-if="this.container.isEmpty()">
          请从左侧选择一个题目并拖拽到这里
        </div>
          <div v-if="!this.container.isEmpty()">
            <div v-for="(item) in this.container.getItems()" :key="item.key">
              <component :is="container.getDesignerClass(item.type)"></component>
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
import QARadioButtonFormItemDesigner from '@/components/qa/designers/QARadioButtonFormItemDesigner'
import QACheckboxFormItemDesigner from '@/components/qa/designers/QACheckboxFormItemDesigner'
import QAAreaFormItemDesigner from '@/components/qa/designers/QAAreaFormItemDesigner'

/*表单项设计器容器*/
export default {
  name: 'QaDesignerContainer',
  data: function () {
    return {
      searchKey:""
    }
  },
  computed: {
    container () {
      return this.$store.state.qa.container
    }
  },
  methods: {
    /**
     * 根据key查找组件
     * @param {str} key
     * @returns {VueComponent}
     */
    findVueComponentByKey(key){
      const ret=this.$children.filter(o=>o.key===key)
      if (ret.length>0){
        return ret[0]
      }
      return null
    },
    test(){
      const t=this.findVueComponentByKey(this.searchKey)
      console.log(t)
    },
    test2() {
      this.$children.forEach(o=>{
        console.log(o.key)
      })
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
        data = QAUtils.createfPhoneItemData("手机号", "", true)//手机号
      } else if (clazz === "fitem_wechat") {
        data = QAUtils.createfWeixinItemData("微信号", "", true)//微信
      } else if (clazz === "fitem_area") {
        data = QAUtils.createfAreaItemData("省市区", true, "", "", "")
      } else if (clazz === "fitem_radio") {
        data = QAUtils.createfRadioItemData("单项选择", true, ["选项1","选项2","选项3"], "", "")
      } else if (clazz === "fitem_mulselet") {
        data = QAUtils.createfMulseletItemData("多项选择", true, ["选项1","选项2","选项3"])
      } else {
        data = QAUtils.createfTextItemData("多行文本", true)
      }
      console.log(`添加组件${data.type}-key${data.key}`)
      this.$store.commit('qa/add', data)
      // this.$forceUpdate()
    }
  },
  components: {
    "item-name-designer":QANameFormItemDesigner,
    "item-phone-designer":QAPhoneFormItemDesigner,
    "item-wechat-designer":QAWeixinFormItemDesigner,
    "item-text-designer":QATextLineFormItemDesigner,
    "item-radio-designer":QARadioButtonFormItemDesigner,
    "item-mulselet-designer":QACheckboxFormItemDesigner,
    "item-area-designer":QAAreaFormItemDesigner
  }
}

</script>

<style scoped lang="less">
  .qad-comp-render-container{
    height:100%;
    display: flex;
    flex-wrap: nowrap;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    flex:1;
  }
  .qad-comp-render-container>.qad-content-pane{
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
  .qad-comp-render-container>.align-center{
    justify-content: center;
    align-items: center;
  }
  .qad-comp-render-container>.align-start{
    justify-content: flex-start;
    align-items: flex-start;
  }
</style>
