<template>
  <div class="qar-preview-container">
    <div class="title">{{this.container.title}}</div>
    <input type="hidden" :value="this.container.qsId"/>
    <div class="center" v-if="this.container.isEmpty()">
      无可渲染内容
    </div>
    <div v-if="!this.container.isEmpty()">
      <div v-for="(item) in this.container.getItems()" :key="item.key">
        <component :is="container.getRenderClass(item.type)" :renderKey="item.key"></component>
      </div>
    </div>
  </div>
</template>

<script>
import QANameFormItemRender from '@/components/qa/renders/QANameFormItemRender'
import QAPhoneFormItemRender from '@/components/qa/renders/QAPhoneFormItemRender'
import QAWeixinFormItemRender from '@/components/qa/renders/QAWeixinFormItemRender'
import QATextLineFormItemRender from '@/components/qa/renders/QATextLineFormItemRender'
import QARadioButtonFormItemRender from '@/components/qa/renders/QARadioButtonFormItemRender'
import QACheckboxFormItemRender from '@/components/qa/renders/QACheckboxFormItemRender'
import QAAreaFormItemRender from '@/components/qa/renders/QAAreaFormItemRender'
import moment from 'moment'
import { NoticeUtils } from '@/common/utils'

/**
 * 问卷查看器，功能类似渲染器，但没有保存预览功能
 */
export default {
  name: 'QAViewer',
  data:function(){
    return {
    }
  },
  computed:{
    container:function(){
      return this.$store.state.qa.container
    },
    qsName:{
      get(){
        return this.container.title
      },
      set(newVal){
        this.$store.commit("qa/setTitle", newVal)
      }
    }
  },
  methods:{
    verify(){
      if (this.container.isEmpty()){
        NoticeUtils.warn("问卷没有添加元素！")
        return false
      }
      for(let i=0; i<=this.$children.length-1;i++){
        const c=this.$children[i]
        const ret=c.verify()
        if (!ret){
          return false
        }
      }
      return true
    }
  },
  components:{
    "item-name-render":QANameFormItemRender,
    "item-phone-render":QAPhoneFormItemRender,
    "item-wechat-render":QAWeixinFormItemRender,
    "item-text-render":QATextLineFormItemRender,
    "item-radio-render":QARadioButtonFormItemRender,
    "item-mulselet-render":QACheckboxFormItemRender,
    "item-area-render":QAAreaFormItemRender
  }}
</script>

<style lang="less" scoped>
  .qar-preview-container{
    width:100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    text-align: left;
    justify-content: flex-start;
    align-items: flex-start;
    background:rgba(247,247,247,1);
    margin: 12px;
    .title{
      font-size: 18px;
      font-weight: bold;
      opacity: 1;
      border: none;
      background-color: transparent;
    }
    >*{
      margin: 12px 4px;
      width: 100%;
      height: auto;
    }
    >.center{
      display: flex;
      height: 100%;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      text-align: center;
    }
  }

</style>
