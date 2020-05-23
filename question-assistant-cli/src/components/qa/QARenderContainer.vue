<template>
  <div class="qar-container">
    <div class="qar-preview-container">
      <div class="title">预览</div>
      <div class="center" v-if="this.container.isEmpty()">
        无可渲染内容
      </div>
      <div v-if="!this.container.isEmpty()">
        <div v-for="(item) in this.container.getItems()" :key="item.key">
          <component :is="container.getRenderClass(item.type)" :renderKey="item.key"></component>
        </div>
      </div>
    </div>
    <div class="qar-container-opt">
      <div>
        <input type="text" class="title" value="未命名问卷">
        <div>2020年5月22日 15:22:00</div>
        <div class="qar-opt-button-group">
          <a href="#" class="button1" @click.stop.prevent="editQQ">继续编辑</a>
          <a href="#" class="button2" @click.stop.prevent="previewQA">查看答卷</a>
          <a href="#" class="button2" @click.stop.prevent="stopQA">暂停问卷</a>
        </div>
      </div>
      <div class="qar-share-container">
        <div class="title">问卷分享</div>
        <div class="title-groups">
          <div class="title ">分享链接</div>
          <a href="#" class="copy">复制链接</a>
        </div>
        <div class="input">http://www.sina.com.cn</div>
      </div>
    </div>
  </div>
</template>

<script>
/*表单项渲染器容器*/
import QANameFormItemRender from '@/components/qa/renders/QANameFormItemRender'
import QAPhoneFormItemRender from '@/components/qa/renders/QAPhoneFormItemRender'
import QAWeixinFormItemRender from '@/components/qa/renders/QAWeixinFormItemRender'
import QATextLineFormItemRender from '@/components/qa/renders/QATextLineFormItemRender'
import QARadioButtonFormItemRender from '@/components/qa/renders/QARadioButtonFormItemRender'

export default {
  name: 'QARenderContainer',
  data:function(){
    return {}
  },
  methods:{
    /**
     * 必填项检查
     */
    verify(){
      for(let i=0; i<=this.$children.length-1;i++){
        const c=this.$children[i]
        c.verify()
        console.log(c)
      }
    },
    editQQ(){
      window.alert("继续编辑")
    },
    previewQA(){
      this.verify()
    },
    stopQA(){
      window.alert("暂停")
    }
  },
  computed:{
    getkey(){
      return this.key
    },
    container:function(){
      return this.$store.state.qa.container
    }
  },
  components:{
    "item-name-render":QANameFormItemRender,
    "item-phone-render":QAPhoneFormItemRender,
    "item-wechat-render":QAWeixinFormItemRender,
    "item-text-render":QATextLineFormItemRender,
    "item-radio-render":QARadioButtonFormItemRender
  }
}
</script>

<style lang="less" scoped>
  @import '../../assets/styles/common.css';
  .qar-container{
    background:rgba(247,247,247,1);
    height:100%;
    display: flex;
    flex-wrap: nowrap;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    flex:1;
    margin: 12px;
    border-radius: 8px;
  }
  .qar-container>.qar-preview-container{
    width:50%;
    height: 100%;
    display: flex;
    flex-direction: column;
    text-align: left;
    justify-content: flex-start;
    align-items: flex-start;
    background:rgba(247,247,247,1);
    margin: 12px;
  }
  .qar-container>.qar-preview-container>*{
    margin: 12px 4px;
  }
  .title{
    font-size: 18px;
    font-weight: bold;
    opacity: 1;
    border: none;
    background-color: transparent;
  }
  .qar-container>.qar-preview-container>.center{
    display: flex;
    height: 100%;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
  }
  .qar-container>.qar-preview-container>*{
    width: 100%;
    height: auto;
  }
  .qar-container>.qar-container-opt{
    /*background:rgba(247,247,247,1);*/
    /*background-color: #00a57b;*/
    width:50%;
    height: 100%;
    display: flex;
    text-align: left;
    flex-direction: column;
    justify-content: space-between;
    align-items: flex-start;
  }
  .qar-container>.qar-container-opt>div>*{
    margin:12px 12px;
  }
  .qar-container>.qar-container-opt .qar-opt-button-group{
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: flex-start;
  }
  .qar-opt-button-group> a{
    text-align: center;
    width:86px;
    height:32px;
    /*background:rgba(0,165,123,1);*/
    box-shadow:0px 2px 12px rgba(0,166,122,0.2);
    opacity:1;
    border-radius:12px;
    line-height: 32px;
    margin: auto 6px;
    padding: 4px;
  }
  .qar-opt-button-group>.button1{
    background:rgba(0,165,123,1);
    color:white;
  }
  .qar-opt-button-group>.button2{
    color:black;
    background-color: rgba(200,200,200,0.98);
  }
  .qar-container>.qar-container-opt>.qar-share-container{
    display: flex;
    height: 230px;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    /*justify-self: flex-start;*/
    width: 100%;
    /*background-color: #00a57b;*/
    /*overflow: hidden;*/
  }
  .qar-share-container>.title-groups{
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    /*margin-right: 22px;*/
  }
  .qar-share-container>.title-groups>.copy{
    margin-right: 24px;
    color:#00a57b;
  }
  .qar-container>.qar-container-opt .input{
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
</style>
