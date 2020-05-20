<template>
  <div class="qad-comp-editor-container">
    <component :is="type"></component>
  </div>
</template>

<script>
/*表单项编辑器容器*/
import QANameFormItemEditor from '@/components/qa/editors/QANameFormItemEditor'
import QAPhoneFormItemEditor from '@/components/qa/editors/QAPhoneFormItemEditor'
import { ObjectUtlls } from '@/common/utils'
import QAWeixinFormItemEditor from '@/components/qa/editors/QAWeixinFormItemEditor'
import QATextLineFormItemEditor from '@/components/qa/editors/QATextLineFormItemEditor'
import QARadioButtonFormItemEditor from '@/components/qa/editors/QARadioButtonFormItemEditor'
import QACheckboxFormItemEditor from '@/components/qa/editors/QACheckboxFormItemEditor'

export default {
  name: 'QAEditorContainer',
  data:function(){
    return {

    }
  },
  computed:{
    item:function(){
      const ret=this.$store.state.qa.container.getItem(this.$store.state.qa.container.key)
      console.log("item:", ret)
      return ret
    },
    type:function(){
      const comp=this.item
      if (ObjectUtlls.isNull(comp) || ObjectUtlls.isUndef(comp)){
        return ""
      }
      const ret=this.$store.state.qa.container.getEditorClass(comp.type)
      return ret
    }
  },
  components:{
    "item-name-editor":QANameFormItemEditor,
    "item-phone-editor":QAPhoneFormItemEditor,
    "item-wechat-editor":QAWeixinFormItemEditor,
    "item-text-editor":QATextLineFormItemEditor,
    "item-radio-editor":QARadioButtonFormItemEditor,
    "item-mulselet-editor":QACheckboxFormItemEditor
  }
}
</script>

<style scoped lang="less">
  .qad-comp-editor-container{
    width:360px;
    height:1012px;
    background:rgba(255,255,255,1);
    box-shadow:-5px 0px 5px rgba(23,29,62,0.05);
    opacity:1;
  }
</style>
