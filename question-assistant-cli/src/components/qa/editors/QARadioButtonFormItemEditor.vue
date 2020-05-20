<template>
  <div v-bind:class="{'qae-item-radio':true, selected:selected}"  @click="click">
    <div class="title">标题</div>
    <input type="text" class="input" v-model="title"/>
    <div class="title">选项设置 <a class="buttons-layout button button-add" href="#" @click="addOption">添加</a></div>
    <div v-for="(op, index) in item.options" :value="op" :key="index" >
      <input type="text" :value="op" @input="changeOptionValue(index, $event)">
      <span class="buttons-layout">
        <div class="buttons">
          <a class="button button-del" href="#" @click="removeOption(index)">删除</a>
        </div>
      </span>
    </div>
    <div class="title">校验</div>
    <div>
      <input type="checkbox" v-model="notEmpty"/><span>是否必填</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'QARadioButtonFormItemEditor',
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
    },
    addOption(){
      const index=this.$store.state.qa.container.indexByKey(this.key)
      this.$store.commit("qa/applyUpdate", {path: `$.items[${index}].options`,
        key:this.key,
        callback: (node)=>{
          node.push("新选项")
        }
      })
    },
    removeOption(idx){
      const index=this.$store.state.qa.container.indexByKey(this.key)
      this.$store.commit("qa/applyUpdate", {path: `$.items[${index}].options`,
        key:this.key,
        callback:node=>{
          node.splice(idx, 1)//删除指定项
        }
      })
    },
    changeOptionValue(idx, event){
      console.log(idx, event.target.value)
      const index=this.$store.state.qa.container.indexByKey(this.key)
      this.$store.commit("qa/applyUpdate", {path: `$.items[${index}].options`,
        key:this.key,
        callback:node=>{
          this.$set(node, idx, event.target.value)
        }
      })
    }
  },
  computed:{
    container:function(){
      return this.$store.state.qa.container
    },
    item:function(){
      const ret=this.$store.state.qa.container.getItem(this.key)
      return ret
    },
    selected:function(){
      return this.$store.state.qa.container.key===this.key
    },
    title:{
      get(){
        return this.$store.state.qa.container.getItem(this.key).title
      },
      set(newVal){
        const index=this.$store.state.qa.container.indexByKey(this.key)
        this.$store.commit("qa/updateValue", {path: `$.items[${index}].title`,
          newVal: newVal,
          key:this.key
        })
      }
    },
    notEmpty:{
      get(){
        return this.$store.state.qa.container.getItem(this.key).notEmpty
      },
      set(newVal){
        const index=this.$store.state.qa.container.indexByKey(this.key)
        this.$store.commit("qa/updateValue", {path: `$.items[${index}].notEmpty`,
          newVal: newVal,
          key:this.key
        })
      }
    }
  }
}
</script>

<style lang="less" scoped>
  .qae-item-radio{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qae-item-radio>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qae-item-radio>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qae-item-radio>.title>.red{
    color:red
  }
  .qae-item-radio>.input{
    height:36px;
    background:rgba(246,246,246,1);
    border:1px solid rgba(222,226,230,1);
    opacity:1;
    border-radius:4px;
  }
  .qae-item-radio .buttons-layout{
    display: inline-block;
  }
  .qae-item-radio .buttons{
    display: flex;
    align-content: flex-start;
    align-items: flex-start;
  }
  .button-add{
    background-color: #00a57b;
  }
  .button-del{
    background-color: gray;
  }
  .button{
    display: table-cell;
    color: white;
    height: 16px;
    width:32px;
    font-family: monospace;
    box-shadow:0px 2px 12px rgba(0,166,122,0.2);
    opacity:1;
    border-radius:16px;
    text-align: center;
    vertical-align: middle;
    margin-left:4px;
    padding: 4px;
  }
</style>
