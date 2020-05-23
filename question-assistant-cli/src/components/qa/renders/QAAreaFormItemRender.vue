<template>
  <div class="qad-item-area-box">
    <div v-bind:class="{'qad-item-area':true, selected:selected}">
      <div class="title">{{item?item.title:""}}
        <span class="red" v-if="!errVisible && item.notEmpty">*</span>
        <span v-if="errVisible" class="red">{{errMessage}}</span>
      </div>
      <select ref="province" class="input input-offset" name="" id="province" @change="provinceChange($event)">
        <option v-for="(item, index) in this.options.province" :value="item.ar_code" :key="index">{{item.ar_name}}</option>
      </select>
      <select ref="city" class="input input-offset" name="" id="city" @change="cityChange($event)">
        <option v-for="(item, index) in this.filtered.city" :value="item.ar_code" :key="index">{{item.ar_name}}</option>
      </select>
      <select ref="county" class="input input-offset" name="" id="county" @change="countyChange($event)">
        <option v-for="(item, index) in this.filtered.county" :value="item.ar_code" :key="index">{{item.ar_name}}</option>
      </select>
    </div>
  </div>
</template>

<script>
import { JsonPathUtils, ObjectUtlls } from '@/common/utils'
import _provinceData from "@/data/province.json"
import _cityData from "@/data/city.json"
import _countyData from "@/data/county.json"
export default {
  name: 'QAAreaFormItemRender',
  props:{
    renderKey:{
      required:true,
      type:String
    }
  },
  /**
   *
   * @returns {{provinceOptions: [], countyOptions: [], filteredCounties: [], cityOptions: [], filteredCities: [], errVisible: boolean, errMessage: string}}
   */
  data:function(){
    return {
      errVisible:false,
      errMessage:"",
      options:{/*所有原始选项*/
        province:[{
          "ar_id": 0,
          "ar_code": "000000",
          "ar_name": "",
          "ar_parent_code": "000000"
        }].concat(_provinceData),
        city:_cityData,
        county:_countyData
      },
      selected:["", "", ""], /*省,市,县编码数组*/
      filtered:{/*过滤后的列表*/
        city:[],
        county:[]
      }
    }
  },
  methods:{
    provinceChange:function(event){
      // "ar_code": "110100",
      // "ar_name": "北京市",
      // "ar_parent_code": "110000"
      this.filtered.city=this.options.city.filter(function(c){
        const ret=c.ar_parent_code===event.target.value
        return ret
      })
      this.updateValue(0, event.target.value)
      console.log("city list", this.filtered.city)
    },
    cityChange:function(event){
      this.filtered.county=this.options.county.filter(function(c){
        const ret=c.ar_parent_code===event.target.value
        return ret
      })
      this.updateValue(1, event.target.value)
      console.log("county list", this.filtered.county)
    },
    countyChange:function(event){
      this.updateValue(2, event.target.value)
      console.log("event.target.value", event.target.value)
    },
    /**
     * 更新省/市/县的值
     * @param {number} idx
     * @param {string} value
     * @returns {void}
     */
    updateValue(idx, value){
      if (this.errVisible) {
        this.errVisible=false;
        this.errMessage=''
      }
      if (!ObjectUtlls.isNull(this.$store.state.qa.container.getItem(this.renderKey))){
        const index=this.$store.state.qa.container.indexByKey(this.renderKey)
        const temp=this.$store.state.qa.container.getItem(this.renderKey).val
        temp[idx]=value
        this.$store.commit("qa/updateValue", {path: `$.items[${index}].val`,
          newVal: temp,
          key:this.renderKey
        })
      }
    },
    verify:function(){
      const index=this.$store.state.qa.container.indexByKey(this.renderKey)
      const notEmpty=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].notEmpty`)
      const val=JsonPathUtils.findSingleNode(this.container, `$.items[${index}].val`)

      if (notEmpty && (!Array.isArray(val) || (Array.isArray(val) && val.length===0))){
        this.errVisible=true
        this.errMessage=`${this.item.title}不能是空！`
        return
      }
      if (notEmpty && Array.isArray(val) && val.filter(o=>{
        return o===""
      }).length>0){
        this.errVisible=true
        this.errMessage=`${this.item.title}无效，每项都要选择！`
        return
      }
    }
  },
  computed:{
    item(){
      return this.$store.state.qa.container.getItem(this.renderKey)
    },
    container(){
      return this.$store.state.qa.container
    }
  }
}
</script>

<style lang="less" scoped>
  .qad-item-area-box{
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items:flex-start;
  }
  .qad-item-area{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    justify-content: flex-start;
    align-items: center;
  }
  .qad-item-area>*{
    margin: 12px 20px;
    width:280px;
    text-align: left;
  }
  .qad-item-area>.title{
    height:18px;
    font-size:13px;
    font-family:PingFang SC;
    font-weight:500;
    line-height:16px;
    color:rgba(51,51,51,1);
    opacity:1;
  }
  .qad-item-area>.title>.red{
    color:red
  }
  .qad-item-area>.input{
    height:36px;
    background:rgba(246,246,246,1);
    border:1px solid rgba(222,226,230,1);
    opacity:1;
    border-radius:4px;
  }
  .qad-item-area>.input> *{
    margin: 8px auto auto 4px;
  }
  .selected{
    border: solid 1px #00a57b;
  }
</style>

