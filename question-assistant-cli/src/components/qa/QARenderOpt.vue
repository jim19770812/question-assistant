<template>
  <div class="qar-container-opt">
    <div>
      <input type="text" class="title" placeholder="未命名问卷" v-model.trim="qsName"/>
      <div>{{new Date() | dateFormat}}</div>
      <div class="qar-opt-button-group">
        <a href="#" class="button1" @click.stop.prevent="save">保存</a>
        <a href="#" class="button1" @click.stop.prevent="publish">发布</a>
        <a href="#" class="button2" @click.stop.prevent="stop">暂停</a>
      </div>
    </div>
    <div class="qar-share-container">
      <div class="title">问卷分享</div>
      <div class="title-groups">
        <div class="title " @click="share">分享链接</div>
        <a href="#" class="copy"
           v-clipboard:copy="shareUrl"
           v-clipboard:success='copySuccess'
           v-clipboard:error="copyError">复制链接</a>
      </div>
      <div class="share-url">{{shareUrl}}</div>
    </div>
  </div>
</template>

<script>
/*表单项渲染器操作区*/

import {
  questionSheetStatus01,
  questionSheetStatus12,
  saveQuestionSheet
} from '@/requests/modules/qa_requests'
import moment from 'moment'
import { NoticeUtils, ObjectUtlls, StringUtils } from '@/common/utils'
export default {
  name: 'QARenderOpt',
  data:function(){
    return {
    }
  },
  filters:{
    dateFormat(dt){
      return moment(dt).format("YYYY-MM-DD HH:mm:ss")
    }
  },
  methods:{
    /**
     * 必填项检查
     * @returns {boolean}
     */
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
    },
    share(){
      NoticeUtils.warn("暂未实现")
    },
    copySuccess(){
      console.info("复制到剪贴板成功")
    },
    copyError(){
      console.error("复制到剪贴板失败")
    },
    save(){
      if (StringUtils.isBlank(this.qsName) || StringUtils.isBlank(this.qsName)){
        NoticeUtils.warn("问卷还没有名字！")
        return
      }
      if (this.container.isEmpty()){
        NoticeUtils.warn("问卷没有添加元素！")
        return
      }
      saveQuestionSheet(this.container.qsId, this.qsName, this.container.items).then(resp=>{
        NoticeUtils.info("保存成功")
      }).catch(resp=>{
        NoticeUtils.error(resp.message)
      })
    },
    publish(){
      console.log("this.container.qsId", this.container.qsId)
      //状态改成已经发布
      questionSheetStatus01(this.container.qsId).then(resp=>{
        NoticeUtils.info("问卷发布成功")
      }).catch(err=>{
        NoticeUtils.error(err.message)
      })
    },
    stop(){
      //状态改成已停止
      questionSheetStatus12(this.container.qsId).then(resp=>{
        NoticeUtils.info("问卷已停止")
      }).catch(err=>{
        NoticeUtils.error(err.message)
      })
    }
  },
  computed:{
    shareUrl(){
      return ObjectUtlls.isUnDefOrNull(this.container.qsId)?"无":`${this.$home_url}/share/${this.container.qsId}`
    },
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
  }
}
</script>

<style lang="less" scoped>
  @import '../../assets/styles/common.css';
  .title{
    font-size: 18px;
    font-weight: bold;
    opacity: 1;
    border: none;
    background-color: transparent;
  }
  .qar-container-opt{
    width:50%;
    height: 100%;
    display: flex;
    text-align: left;
    flex-direction: column;
    justify-content: space-between;
    align-items: flex-start;
    >div>*{
      margin:12px 12px;
    }
    .qar-opt-button-group{
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-items: flex-start;
      > a{
        text-align: center;
        width:86px;
        height:32px;
        box-shadow:0px 2px 12px rgba(0,166,122,0.2);
        opacity:1;
        border-radius:12px;
        line-height: 32px;
        margin: auto 6px;
        padding: 4px;
      }
      .button1{
        background:rgba(0,165,123,1);
        color:white;
      }
      .button2{
        color:black;
        background-color: rgba(200,200,200,0.98);
      }
    }
    >.qar-share-container{
      display: flex;
      height: 230px;
      flex-direction: column;
      justify-content: flex-start;
      align-items: flex-start;
      /*justify-self: flex-start;*/
      width: 100%;
      /*background-color: #00a57b;*/
      /*overflow: hidden;*/
      >.title-groups{
        width: 100%;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
        /*margin-right: 22px;*/
      }
      >.title-groups>.copy{
        margin-right: 24px;
        color:#00a57b;
      }
    }
    .share-url{
      width:96%;
      height: 28px;
      background-color: rgba(230,230,230,0.98);
      text-decoration: none;
      border: none;
      color:rgba(180,180,180,0.98);
      padding-top:4px;
      outline: none;
      overflow: hidden;
    }
  }
</style>
