<template>
  <div>
    <div v-bind:class="{'qad-item-checkbox':true, selected:selected}" @click="click">
      <div class="title">
        {{item?item.title:""}}<span class="red" v-show="item.notEmpty">*</span>
      </div>
      <div v-for="(op,index) in item.options" :key="index">
        <input type="checkbox" disabled="disabled"/> <label>{{op}}</label>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: 'QACheckboxFormItemDesigner',
  data:function(){
    return {
      key:""
    }
  },
  created:function(){
    this.key=this.$store.state.qa.container.getLastestItem().key
  },
  methods:{
    click:function(){
      this.$store.commit('qa/select', this.key)
    }
  },
  computed:{
    container:function(){
      return this.$store.state.qa.container
    },
    item:function(){
      return this.$store.state.qa.container.getItem(this.key)
    },
    selected:function(){
      return this.$store.state.qa.container.key===this.key
    }
  }
}
</script>

<style lang="less" scoped>
  .qad-item-checkbox{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qad-item-checkbox>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qad-item-checkbox>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qad-item-checkbox>.title>.red{
    color:red
  }
  .qad-item-checkbox>.input{
    height:36px;
    background:rgba(246,246,246,1);
    border:1px solid rgba(222,226,230,1);
    opacity:1;
    border-radius:4px;
  }
  .selected{
    border: solid 1px #00a57b;
  }
</style>
