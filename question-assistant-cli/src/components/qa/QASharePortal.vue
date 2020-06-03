<template>
  <div class="qa-share-portal">
    <div class="qa-share-viewer">
      <qa-viewer ref="viewer"></qa-viewer>
    </div>
    <div class="button-groups">
      <a href="#" class="button1" @click="save">保存</a>
    </div>
  </div>
</template>

<script>

import QAViewer from '@/components/qa/QAViewer'
import { getQuestionSheet, saveAnswer, saveQuestionSheet } from '@/requests/modules/qa_requests'
import { NoticeUtils, ObjectUtlls } from '@/common/utils'

export default {
  name: 'QASharePortal',
  data:function(){
    return {
      qsId:0
    }
  },
  computed:{
    container:function(){
      return this.$store.state.qa.container
    }
  },
  mounted(){
    this.qsId=this.$route.query.id
    if(ObjectUtlls.isUnDefOrNull(this.qsId)){
      this.$router.push({name:"e404"})
      return
    }
    getQuestionSheet(this.qsId).then(resp=>{
      const qs=resp.ret
      console.log("qs.qs_template", qs)
      this.$store.commit("qa/forEdit", {qsId:qs.qs_id,
        title:qs.qs_name,
        itemsJson:qs.qs_template,
        status:qs.qs_status}) //设置为编辑问卷模式
    }).catch(err=>{
      NoticeUtils.error(err.message)
    })
  },
  methods:{
    verify(){
      if (this.container.isEmpty()){
        NoticeUtils.warn("问卷没有表单！")
        return false
      }
      this.$refs.viewer.verify()
      return true
    },
    save(){
      this.verify()
      saveAnswer(null, this.container.qsId, this.container.items).then(resp=>{
        NoticeUtils.success("保存成功")
      }).catch(err=>{
        NoticeUtils.success(err.message)
      })
    }
  },
  components:{
    "qa-viewer":QAViewer
  }
}
</script>

<style lang="less" scoped>
  @import '../../assets/styles/common.css';
  .qa-share-portal{
    width: 100%;
    background:rgba(247,247,247,1);
    height:100%;
    display: flex;
    flex-wrap: nowrap;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    flex:1;
    margin: 12px;
    //border-radius: 8px;
    .qa-share-viewer{

    }
    .button-groups{
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
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
  }

</style>
