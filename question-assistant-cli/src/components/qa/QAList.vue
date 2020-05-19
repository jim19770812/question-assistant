<template>
  <div>
    <div class="qa-list-main">
      <div class="qa-list-container">
        <div class="qa-list-header">
          <div>
            报名助手
          </div>
          <div class="qa-list-header-user-container">
            <div class="qa-list-header-user-flex">
              <a href="#" class="qa-return-home">返回首页</a>
              <span class="qa-default-user-icon"></span>
              <span class="qa-default-user-name">张三丰</span>
              <span class="arrow-down"></span>
            </div>
            <div class="qa-list-header-user-opt-list-container">
              <div class="qa-list-header-user-opt-list">
                <a href="#" class="logout-txt">退出登录</a>
                <a href="#" class="logout-button"></a>
              </div>
            </div>
          </div>
        </div>
        <div class="qa-list-header-container">
          <div class="qa-list-header-container-tabs">
            <div class="qa-list-header-container-tab qa-list-header-container-tab-active">
              <div class="qa-list-header-container-number">325</div>
              <div class="qa-list-header-container-status">全部问卷</div>
            </div>
            <div class="qa-list-header-container-tab qa-list-header-container-tab-deactive">
              <div class="qa-list-header-container-number">94</div>
              <div class="qa-list-header-container-status">待发布</div>
            </div>
            <div class="qa-list-header-container-tab qa-list-header-container-tab-deactive">
              <div class="qa-list-header-container-number">129</div>
              <div class="qa-list-header-container-status">已发布</div>
            </div>
            <div class="qa-list-header-container-tab qa-list-header-container-tab-deactive">
              <div class="qa-list-header-container-number">211</div>
              <div class="qa-list-header-container-status">已停止</div>
            </div>
          </div>
          <div class="qa-list-operator-bar">
            <div class="qa-list-operator-bar-box">
              <input class="bar-search-input" type="text" placeholder="请输入问卷名称/ID"/>
              <a href="#" class="bar-search-del-btn"></a>
              <a href="#" class="bar-search-find-btn"></a>
              <a class="bar-operate-add-or-close" href="#" @click="barOperateAddOrCloseClicked">{{barOperateAddOrCloseTitle}}</a>
            </div>
            <div class="popup-dialog" v-if="barOperatAdd">
              <div class="popup-dialog-row">
                <div class="popup-dialog-cell">
                  <div class="dlg-container right-border">
                    <div class="dlg-template-icon"></div>
                    <div class="dialog-item-title">问卷模板</div>
                    <div class="dialog-item-content">项目会议、品牌活动等 报名表模板</div>
                  </div>
                </div>
                <div class="popup-dialog-cell">
                  <div class="dlg-container">
                    <div class="dlg-template-empty-qa"></div>
                    <div class="dialog-item-title">空白问卷</div>
                    <div class="dialog-item-content">空白问卷，需要您自主 设计题目</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="qa-list-body">
          <div class="qa-list-body-header">
            <div class="qa-list-body-header-box" style="width:148px">
              <label>问卷ID</label>
            </div>
            <div class="qa-list-body-header-box" style="width:334px">
              <label>问卷名称</label>
            </div>
            <div class="qa-list-body-header-box" style="width:148px">
              <label>问卷状态</label>
            </div>
            <div class="qa-list-body-header-box" style="width:120px">
              <label>问卷数量</label>
            </div>
            <div class="qa-list-body-header-box" style="width:194px">
              <label>创建时间</label>
            </div>
            <div class="qa-list-body-header-box" style="width:168px">
              <label>操作</label>
            </div>
          </div>
          <div class="qa-list-body-container">
            <div class="qa-list-body-container-row">
              <div class="qa-list-body-content-cell" style="width:148px">
                <div class="qa-list-body-qa-id qa-list-body-qa-id-st1">
                  问卷ID
                </div>
              </div>
              <div class="qa-list-body-content-cell" style="width:334px">
                <span class="qa-list-body-qa-name">问卷名称问卷名称问卷名称问卷名称问卷名称问卷名称</span>
                <span class="qa-list-body-qa-name-ellipsis"></span>
              </div>
              <div class="qa-list-body-content-cell" style="width:148px">
                <span class="qa-list-body-qa-st1-icon"></span>
                <span>问卷状态</span>
              </div>
              <div class="qa-list-body-content-cell" style="width:120px">
                <span class="qa-list-body-qa-count">34</span>
              </div>
              <div class="qa-list-body-content-cell" style="width:194px">
                <span class="qa-list-body-create-time">2020/5/15/ 16:34:15</span>
              </div>
              <div class="qa-list-body-content-cell" style="width:168px">
                <a href="#" class="qa-list-body-op-copy"></a>
                <a href="#" class="qa-list-body-op-edit"></a>
                <a href="#" class="qa-list-body-op-del"></a>
              </div>
            </div>
            <div class="qa-list-body-container-row">
              <div class="qa-list-body-content-cell" style="width:148px">
                <div class="qa-list-body-qa-id qa-list-body-qa-id-st2">
                  问卷ID
                </div>
              </div>
              <div class="qa-list-body-content-cell" style="width:334px">
                <span class="qa-list-body-qa-name">问卷名称问卷名称问卷名称问卷名称问卷名称问卷名称</span>
                <span class="qa-list-body-qa-name-ellipsis"></span>
              </div>
              <div class="qa-list-body-content-cell" style="width:148px">
                <span class="qa-list-body-qa-st2-icon"></span>
                <span>问卷状态</span>
              </div>
              <div class="qa-list-body-content-cell" style="width:120px">
                <span class="qa-list-body-qa-count">34</span>
              </div>
              <div class="qa-list-body-content-cell" style="width:194px">
                <span class="qa-list-body-create-time">2020/5/15/ 16:34:15</span>
              </div>
              <div class="qa-list-body-content-cell" style="width:168px">
                <a href="#" class="qa-list-body-op-copy"></a>
                <a href="#" class="qa-list-body-op-edit"></a>
                <a href="#" class="qa-list-body-op-del"></a>
              </div>
            </div>
          </div>
          <div class="qa-list-footer">
            <a href="#" class="qa-list-footer-pageup"></a>
            <input class="qa-list-footer-pagenum" type="text" value="3"/>
            <span>/</span>
            <a href="#" class="qa-list-footer-pagecount">10</a>
            <a href="#" class="qa-list-footer-pagedown"></a>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: 'QAList',
  data:function(){
    return {
      barOperatAdd:true /*是否是添加操作*/
    }
  },
  computed:{
    barOperateAddOrCloseTitle:function(){ /*添加/关闭按钮,点击切换图标*/
      if (this.barOperatAdd){
        return "+"
      }else{
        return "x"
      }
    }
  },
  methods:{
    barOperateAddOrCloseClicked:function(){
      this.barOperatAdd=!this.barOperatAdd
    }
  }
}
</script>

<style scoped lang="less">
  html,body {
    margin: 0;
    padding: 0;
    height: 100%;
    width: 100%;
  }
  a{
    outline: none;
    text-decoration: none;
  }
  .qa-list-main{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-items: center;
  }
  .qa-list-container{
    width:1180px;
    height:1080px;
    background:rgba(247,247,247,1);
    opacity:1;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
  }
  .qa-list-header{
    display: flex;
    flex-direction: row;
    height: 68px;
    flex-wrap: nowrap;
    justify-content: space-between;
    justify-items: center;
    box-sizing: border-box;
    border:1px solid red;
  }
  .qa-list-header>*{
    padding: 20px;
  }
  .qa-list-header-user-container{
    display: flex;
    flex-direction: column;
    z-index: 1;
  }
  .qa-list-header-user-container>.qa-list-header-user-flex{
    display: flex;
    flex-wrap: nowrap;
    flex-direction: row;
    justify-content: flex-end; /*右侧对齐*/
  }
  .qa-list-header-user-container .qa-default-user-name{
    margin: 4px;
  }
  .qa-list-header-user-flex .qa-return-home{
    font-size:14px;
    font-family:PingFang SC;
    font-weight:400;
    line-height:20px;
    color:rgba(27,31,38,1);
    opacity:1;
    margin-right: 48px;
  }
  .qa-list-header-user-container .qa-default-user-icon{
    display: inline-block;
    width: 28px;
    height: 28px;
    background: url("../../assets/default-user-logo.png") no-repeat center center;
    background-size: 28px 28px;
    margin: 0 12px 0 0;
  }
  .qa-list-header-user-container .arrow-down {
    width: 0;
    height: 0;
    border-top: 8px solid rgba(26,33,71,1);
    opacity: 1;
    border-left: 6px solid transparent;
    border-right: 6px solid transparent;
    margin: 10px 0 0 4px;
  }
  .qa-list-header-user-container>.qa-list-header-user-opt-list-container{
    z-index: 2;
    display: block;
    width:240px;
    height:56px;
  }
  .qa-list-header-user-container>.qa-list-header-user-opt-list-container>.qa-list-header-user-opt-list{
    display: flex;
    flex-wrap: nowrap;
    flex-direction: row;
    justify-content: space-between;
    //justify-items: center;
    align-items: center;
    background:rgba(255,255,255,0.93);
    border:1px solid rgba(233,233,233,1);
    box-shadow:0 2px 6px rgba(188,188,188,0.5);
    opacity:1;
    border-radius:10px;
    margin-top: 4px;
    height: 36px;
    /*z-index: 3;*/
  }
  .qa-list-header-user-opt-list>*{
    margin: 20px 20px;
  }
  .qa-list-header-user-opt-list>.logout-txt{
    /*width:48px;*/
    /*height:17px;*/
    font-size:12px;
    font-family:PingFang SC;
    font-weight:400;
    line-height:17px;
    /*-webkit-text-stroke:1 rgba(0,0,0,0.00);*/
    text-stroke:1 rgba(0,0,0,0.00);
    color:#F45738;
    opacity:1;
    /*margin:20px 18px;*/
  }
  .qa-list-header-user-opt-list>.logout-button{
    background-image:url("../../assets/icon_quit.png");
    background-size: 10px 10px;
    background-repeat: no-repeat;
    width: 10px;
    height: 10px;
    outline: none;
    text-decoration: none;
  }
  .qa-list-container>.qa-list-header-container{
    width:100%;
    height: 73px;
    display: flex;
    flex-wrap: nowrap;
    justify-content: space-between;
  }
  .qa-list-header-container>.qa-list-header-container-tabs{
    display: flex;
    flex-wrap: nowrap;
    flex-direction: row;
  }
  .qa-list-header-container .qa-list-header-container-tab-deactive{
    background:rgba(0,166,122,1);
  }
  .qa-list-header-container .qa-list-header-container-tab{
    width:157px;
    height:73px;
    opacity:1;
    border-radius: 5px 5px 0 0;
    margin-right: 12px;
  }
  .qa-list-header-container .qa-list-header-container-tab-active{
    background:rgba(255,255,255,1);
  }
  .qa-list-header-container .qa-list-header-container-tab-active>.qa-list-header-container-number{
    font-size:24px;
    font-family:DIN Alternate;
    font-weight:bold;
    line-height:29px;
    color:rgba(0,146,106,1);
    margin: 12px 0 2px 12px;
    opacity:1;
  }
  .qa-list-header-container .qa-list-header-container-tab-active>.qa-list-header-container-status{
    font-size:14px;
    font-family:PingFang SC;
    font-weight:400;
    line-height:14px;
    color:rgba(0,146,106,1);
    opacity:0.6;
    margin: 2px 0 0 12px;
  }
  .qa-list-header-container .qa-list-header-container-tab-deactive>.qa-list-header-container-number{
    font-size:24px;
    font-family:DIN Alternate;
    font-weight:bold;
    line-height:29px;
    color:rgba(255,255,255,1);
    padding: 12px 0 0 12px;
    opacity:1;
  }
  .qa-list-header-container .qa-list-header-container-tab-deactive>.qa-list-header-container-status{
    font-size:14px;
    font-family:PingFang SC;
    font-weight:400;
    line-height:17px;
    color:rgba(255,255,255,1);
    opacity:0.6;
    margin: 2px 0 0 12px;
  }
  .qa-list-header-container .qa-list-operator-bar{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
    /*width:360px;*/
    width: auto;
    margin-top: 30px;
  }
  .qa-list-header-container .qa-list-operator-bar .qa-list-operator-bar-box{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
    width:360px;
  }
  .qa-list-operator-bar .bar-search-input{
    width:300px;
    height:30px;
    background:rgba(255,255,255,1);
    box-shadow:0px 2px 16px rgba(26,33,71,0.04);
    opacity:1;
    border:0px;
    border-radius:15px;
    z-index: 1;
    padding-left: 22px;
    margin-right: 36px;
    text-decoration: none;
    outline: none;
  }
  .qa-list-operator-bar .bar-search-input input::-webkit-input-placeholder{
    position: relative;
    left: 20px;
  }
  .qa-list-operator-bar .bar-search-del-btn{
    width:15px;
    height: 15px;
    background-image: url("../../assets/icon_del.png");
    background-repeat: no-repeat;
    z-index: 2;
    margin-left: -355px;
  }
  .qa-list-operator-bar .bar-search-find-btn{
    display: block;
    width:10px;
    height: 10px;
    background-image: url("../../assets/icon_search.png");
    background-repeat: no-repeat;
    z-index: 2;
    margin-left: 285px;
  }
  .qa-list-operator-bar .bar-operate-add-or-close{
    font-weight: bolder;
    color: white;
    font-size: larger;
    width:30px;
    height:30px;
    background:rgba(0,165,123,1);
    box-shadow:0px 2px 12px rgba(0,166,122,0.26);
    border-radius:50%;
    opacity:1;
    outline: none;
    text-decoration: none;
    margin-left: 16px;
  }
  .qa-list-main .popup-dialog{
    width:320px;
    height:132px;
    background:rgba(0,166,122,1);
    border:1px solid rgba(233,233,233,1);
    box-shadow:0px 2px 6px rgba(188,188,188,0.5);
    opacity:1;
    border-radius:10px;
    display: table;
    /*order:0;*/
    z-index: 0;/*设置了z-index才能浮动*/
    margin-top: 12px;
  }
  .popup-dialog>.popup-dialog-row{
    display: table-row;
  }
  .popup-dialog>.popup-dialog-row>.popup-dialog-cell{
    display: table-cell;
    vertical-align: middle;
    text-align: center;
  }
  .popup-dialog>.popup-dialog-row .right-border{
    box-sizing: content-box;
    /*padding: 19px 0;*/
    margin: 0 19px 0 0;
    border-right: 1px solid rgba(255,155,122,1);
  }
  .popup-dialog>.popup-dialog-row>.popup-dialog-cell .dlg-container{
    display: flex;
    flex-wrap: nowrap;
    flex-direction: column;
    justify-content: center;
    align-items: self-start;
  }
  .popup-dialog>.popup-dialog-row>.popup-dialog-cell .dlg-container>*{
    margin:5px auto;
    text-align: center;
  }
  .popup-dialog>.popup-dialog-row>.popup-dialog-cell .dlg-template-icon{
    display: block;
    width:24px;
    height: 28px;
    background-image: url("../../assets/icon_Question1.png");
    background-repeat: no-repeat;
  }
  .popup-dialog>.popup-dialog-row>.popup-dialog-cell .dialog-item-title{
    width:auto;
    height:17px;
    font-size:12px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:17px;
    color:rgba(255,255,255,1);
    text-stroke:1 rgba(0,0,0,0.00);
    opacity:1;
  }
  .popup-dialog>.popup-dialog-row>.popup-dialog-cell .dialog-item-content{
    width:auto;
    height:34px;
    font-size:12px;
    font-family:PingFang SC;
    font-weight:300;
    line-height:17px;
    color:rgba(255,255,255,1);
    /*-webkit-text-stroke:1 rgba(0,0,0,0.00);*/
    text-stroke:1 rgba(0,0,0,0.00);
    opacity:0.7;
  }
  .popup-dialog>.popup-dialog-row>.popup-dialog-cell .dlg-template-empty-qa{
    display: block;
    width:24px;
    height: 28px;
    background-image: url("../../assets/icon_Question2.png");
    background-repeat: no-repeat;
  }
  .qa-list-body table{
    border-collapse: collapse;
    width:100%;
    height: 100%;
  }
  .qa-list-container>.qa-list-body{
    width:100%;
    height:778px;
    background:white;
    box-shadow:0px 2px 16px rgba(26,33,71,0.02);
    opacity:1;
    border-radius:0px 16px 16px 16px;
  }
  .qa-list-body .qa-list-body-header{
    width: 100%;
    height: 36px;
    display: flex;
    justify-content: space-between;;
    align-items: center;
    flex-flow: nowrap;
    flex-direction: row;
    margin-top: 30px;
  }
  .qa-list-body .qa-list-body-header>.qa-list-body-header-box{
    display: table-cell;
    height: 36px;
    text-align: center;
    vertical-align: middle;
    background-color: gray;
    background:rgba(240,241,242,1);
    opacity:1;
    width:148px;
    font-size:12px;
    font-family:PingFang SC;
    font-weight:400;
    line-height:10px;
  }
  .qa-list-body .qa-list-body-header-box label{
    display: block;
    padding-top: 10px;
    box-sizing: border-box;
    height: 36px;
    opacity:1;
    color:white;
    font-size:12px;
    color:rgba(153,153,153,1);
    font-family:PingFang SC;
    font-weight:400;
    line-height:10px;
  }
  .qa-list-body .qa-list-body-container{
    margin:0px;
    padding:0px;
  }
  .qa-list-body>.qa-list-body-container>.qa-list-body-container-row{
    display: flex;
    width: 100%;
    flex-direction: column;
    justify-content: space-between;
    flex-flow: nowrap;
    margin: 18px auto 18px auto;
    border-bottom: 1px solid rgba(237,238,242,1);
  }
  .qa-list-body .qa-list-body-container-row .qa-list-body-content-cell{
    padding-top: 12px;
    box-sizing: border-box;
    text-align: center;
    height: 68px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    margin:0;
    padding-left:0;
    padding-right: 0;
  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-qa-id{
    width:88px;
    height:32px;
    opacity:1;
    border-radius:4px;
    padding-top: 5px;
  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-qa-id-st0 { /*待发布*/
    background:rgba(222,223,224,1);
    color:white;
  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-qa-id-st1 { /*已发布*/
    background:rgba(0,166,122,1);
    color:white;
  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-qa-id-st2 { /*已停止*/
    background:rgba(255,86,71,1);
    color:white;
  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-qa-name{
    width:320px;
    word-break: normal;
    overflow: hidden;
    white-space: nowrap;
  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-qa-name-ellipsis{/*提供一个小的遮罩层实现超出文字渐淡*/
    display: block;
    width:60px;
    margin-left: -60px;
    background-image: linear-gradient(to right, rgba(255,255,255,0) 0%, rgba(255,255,255,0.8) 100%);
    /*background-color: blue;*/
  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-qa-st0-icon{/*待发布图标*/
    display: block;
    width:11px;
    height:11px;
    background:rgba(212,213,214,1);
    border-radius: 4.5px;
    margin:7px 3px;
  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-qa-st1-icon{/*已发布图标*/
    display: block;
    width:11px;
    height:11px;
    background:rgba(0,166,122,1);
    border-radius: 4.5px;
    margin:7px 3px;
  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-qa-st2-icon{/*已停止图标*/
    display: block;
    width:11px;
    height:11px;
    background:rgba(255,88,77,1);
    border-radius: 4.5px;
    margin:7px 3px;
  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-qa-count{
    font-size:14px;
    font-family:DIN Alternate;
    font-weight:bold;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-create-time{
    font-size:13px;
    font-family:PingFang SC;
    font-weight:400;
    /*line-height:10px;*/
    color:rgba(153,153,153,1);
    opacity:1;
  }
  .qa-list-body .qa-list-body-content-cell>*{
    margin-right: 19px;
  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-op-copy{
    display: block;
    width: 14px;
    height: 14px;
    background-image: url("../../assets/icon_list_copy.png");

  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-op-edit{
    display: block;
    width: 15px;
    height: 15px;
    background-image: url("../../assets/icon_list_edit.png");
  }
  .qa-list-body .qa-list-body-content-cell .qa-list-body-op-del{
    display: block;
    width: 12px;
    height: 15px;
    background-image: url("../../assets/icon_list_del.png");
  }
  .qa-list-body .qa-list-footer{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: center;
    align-items: center;
    font-size:12px;
    font-family:Arial;
    font-weight:400;
    line-height:14px;
    opacity:1;
  }
  .qa-list-footer .qa-list-footer-pageup{
    display: block;
    height: 8px;
    width:8px;
    background-image: url("../../assets/icon_list_arrow_left.png");
    margin: auto 3px;
  }
  .qa-list-footer .qa-list-footer-pagedown{
    display: block;
    height: 8px;
    width:8px;
    background-image: url("../../assets/icon_list_arrow_right.png");
    margin: auto 3px;
  }
  .qa-list-footer .qa-list-footer-pagenum{
    margin: auto 3px;
    width:20px;
    background:rgba(240,243,246,1);
    border:1px solid rgba(227,227,227,1);
    text-align: center;
    outline: none;
  }
  .qa-list-footer .qa-list-footer-pagecount{
    margin: auto 3px;
    text-align: center;
    color:rgba(26,33,71,0.40);
  }
</style>
