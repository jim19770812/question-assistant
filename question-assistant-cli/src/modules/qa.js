import {ObjectUtlls} from '@/common/utils'
import Vue from 'vue'
import jq from 'jsonpath'
/**
 * 定义类型和对应组件设计器/编辑器/渲染器的映射关系
 * @type {object}
 */
const typeClassMapper={
  fitem_name:
  { designer:"item-name-designer",
    editor:"item-name-editor",
    render:"item-name-render"
  },
  fitem_phone:{
    designer:"item-phone-designer",
    editor:"item-phone-editor",
    render:"item-phone-render"
  },
  fitem_wechat:{
    designer:"item-wechat-designer",
    editor:"item-wechat-editor",
    render:"item-wechat-render"
  },
  fitem_area:{
    designer:"item-area-designer",
    editor:"item-area-editor",
    render:"item-area-render"
  },
  fitem_text:{
    designer:"item-text-designer",
    editor:"item-text-editor",
    render:"item-text-render"
  },
  fitem_radio:{
    designer:"item-select-designer",
    editor:"item-select-editor",
    render:"item-select-render"
  },
  fitem_mulselet:{
    designer:"item-mulselet-designer",
    editor:"item-mulselet-editor",
    render:"item-mulselet-render"
  }
}

class Container{
  constructor () {
    console.log("constructor")
    this.items=[]
    this.index=-1
  }

  /**
   * 添加项
   * @param {object} item
   * @param {boolean} autoSelect
   * @returns {void}
   */
  add(item, autoSelect=true){
    this.items.push(item)
    if (autoSelect){
      this.index=this.items.length-1
    }
  }

  /**
   * 移除指定索引的项
   * @param {number} index
   * @returns {void}
   */
  remove(index){
    this.items.splice(index)
  }

  /**
   * 获取项列表
   * @returns {[object]}
   */
  getItems(){
    return this.items
  }

  /**
   * 选中某项
   * @param {number}index
   * @returns {void}
   */
  select(index){
    this.index=index
  }

  /**
   * 根据索引获取某项
   * @param {number}index
   * @returns {object}
   */
  getItem(index){
    return this.items[index]
  }

  /**
   * 当前选中项
   * @returns {object}
   */
  getSelectedItem(){
    if (this.index<0){
      return null
    }
    return this.items[this.index]
  }

  /**
   * 判断项列表是否是空
   * @returns {boolean}
   */
  isEmpty(){
    return this.items.length===0
  }

  /**
   * 获取项的数量
   * @returns {number}
   */
  size(){
    return this.items.length
  }

  /**
   * 根据项目名获得设计器类名
   * @param {string} itemName
   * @returns {string}
   */
  getDesignerClass(itemName){
    return ObjectUtlls.hasProperty(typeClassMapper, itemName)?typeClassMapper[itemName].designer:""
  }

  /**
   * 根据项目名获得编辑器类名
   * @param {string} itemName
   * @returns {string}
   */
  getEditorClass(itemName){
    return ObjectUtlls.hasProperty(typeClassMapper, itemName)?typeClassMapper[itemName].editor:""
  }

  /**
   * 根据项目名获得渲染器类名
   * @param {string} itemName
   * @returns {string}
   */
  getRenderClass(itemName){
    return ObjectUtlls.hasProperty(typeClassMapper, itemName)?typeClassMapper[itemName].render:""
  }
}

const state={
  container:new Container()
}

const mutations={
  showThis:(states)=>{
    console.log("showThis, states.container", states.container)
  },
  add: (states, payload)=> {
    states.container.add(payload)
    console.log("add，states.container", states.container)
  },
  remove:(states, index)=>{
    states.container.remove(index)
    console.log("remove，states.container", states.container)
  },
  /**
   * 选中组件
   * @param {this} states
   * @param {int} index
   */
  select:(states, index)=>{
    states.container.index=index
  },
  /**
   * 更新组件对象
   * @param {object}states 状态对象
   * @param {{path, newVal}} payload 载荷，path是jsonpath路径，item是更新后的值
   * @returns {void}
   */
  updateValue(states, payload){
    if (!ObjectUtlls.hasProperty(payload, "path")){
      throw new Error("updateValue时发现未传入有效的更新路径")
    }
    if (!ObjectUtlls.hasProperty(payload, "newVal")){
      throw new Error("updateValue时发现未传入有效的更新内容")
    }
    const ret=jq.value(states.container, payload.path, payload.newVal)
    if (ret !== payload.newVal){
      throw new Error(`未能根据jsonpath：${payload.path}修改对象值`)
    }
    console.log("after updateValue, ret=",  ret, JSON.stringify(states.container.items[states.container.index]))
  },
  /**
   * 批量更新组件对象值
   * @param {object}states
   * @param {[{path, newVal}]} payload
   */
  updateValues(states, payload){
    if (!(payload instanceof Array)){
      throw new Error("updateValues时发现未传入有效的数组")
    }
    payload.forEach(payLoadItem=>{
      this.updateValue(states, payLoadItem)
    })
  }
}

export class QAUtils{
  /**
   * 创建名字组件数据
   * @param {string} title 标题
   * @param {string} defVal 默认值
   * @param {boolean} notEmpty 是否非空
   * @param {int} minLen 最小长度
   * @param {int} maxLen 最大长度
   * @returns {object}
   */
  static createfNameItemData(title, defVal, notEmpty, minLen, maxLen){
    return {
      type:"fitem_name",
      title:title,
      val:defVal,
      notEmpty:!ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true,
      minLen:{
        enabled:ObjectUtlls.isUndef(minLen),
        val:!ObjectUtlls.isUndef(minLen) && !ObjectUtlls.isNull(minLen)?minLen:0
      },
      maxLen:{
        enabled:ObjectUtlls.isUndef(maxLen),
        val:!ObjectUtlls.isUndef(maxLen) && !ObjectUtlls.isNull(maxLen)?maxLen:0
      }
    }
  }

  /**
   * 创建电话组件数据
   * @param {string} phone 手机号
   * @param {bool} notEmpty 是否非空
   * @returns {object}
   */
  static createfPhoneItemData(phone, notEmpty){
    return {
      type:"fitem_phone",
      val:phone,
      notEmpty:ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true
    }
  }

  /**
   * 创建文本组件数据
   * @param {string}text 文字
   * @param {bool}notEmpty 是否非空
   * @param {int}minLen 最小长度
   * @param {int}maxLen 最大长度
   * @returns {object}
   */
  static createfTextItemData(text, notEmpty, minLen, maxLen){
    return {
      type:"fitem_text",
      val:text,
      notEmpty:{
        enabled:ObjectUtlls.isUndef(notEmpty),
        val:!ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true
      },
      minLen:{
        enabled:ObjectUtlls.isUndef(minLen),
        val:!ObjectUtlls.isUndef(minLen) && !ObjectUtlls.isNull(minLen)?minLen:0
      },
      maxLen:{
        enabled:ObjectUtlls.isUndef(maxLen),
        val:!ObjectUtlls.isUndef(maxLen) && !ObjectUtlls.isNull(maxLen)?maxLen:0
      }
    }
  }

  /**
   * 创建微信组件数据
   * @param {string} weixin 微信号
   * @param {bool} notEmpty 是否非空
   * @returns {object}
   */
  static createfWeixinItemData(weixin, notEmpty){
    return {
      type:"fitem_weixin",
      val:weixin,
      notEmpty:{
        enabled:ObjectUtlls.isUndef(notEmpty),
        val:!ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true
      }
    }
  }

  /**
   * 创建radio button数据
   * @param {string} title 标题
   * @param {bool} notEmpty 是否非空
   * @param {array} options 待选项
   * @returns {object}
   */
  static createfRadioItemData(title, notEmpty, options){
    return {
      type:"fitem_radio",
      val:options,
      notEmpty:{
        enabled:ObjectUtlls.isUndef(notEmpty),
        val:!ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true
      }
    }
  }

  /**
   * 创建多选组件数据
   * @param {string}title 标题
   * @param {bool}notEmpty 是否非空
   * @param {array}options 待选里诶包
   * @param {int}minOptionCnt 最少选择数
   * @param {int} maxOptionCnt 最多选择数
   * @returns {object}
   */
  static createfMulseletItemData(title, notEmpty, options, minOptionCnt, maxOptionCnt){
    return {
      type:"fitem_mulselet",
      val:options,
      notEmpty:{
        enabled:ObjectUtlls.isUndef(notEmpty),
        val:!ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true
      },
      minOptionCnt:{
        enabled:ObjectUtlls.isUndef(minOptionCnt),
        val:!ObjectUtlls.isUndef(minOptionCnt) && !ObjectUtlls.isNull(minOptionCnt)?minOptionCnt:true
      },
      maxOptionCnt:{
        enabled:ObjectUtlls.isUndef(maxOptionCnt),
        val:!ObjectUtlls.isUndef(maxOptionCnt) && !ObjectUtlls.isNull(maxOptionCnt)?maxOptionCnt:true
      }
    }
  }

  /**
   *
   * 创建地区组件数据
   * @param {string}title 标题
   * @param{bool} notEmpty 是否非空
   * @param {string} provinceKey 省关键字
   * @param {string} cityKey 市关键字
   * @param {string}coutyKey 县关键字
   * @returns {object}
   */
  static createfAreaItemData(title, notEmpty,provinceKey, cityKey, coutyKey){
    return {
      type:"fitem_area",
      notEmpty:{
        enabled:ObjectUtlls.isUndef(notEmpty),
        val:!ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true
      },
      provinceKey:!ObjectUtlls.isUndef(provinceKey) && !ObjectUtlls.isNull(provinceKey)?provinceKey:0,
      cityKey:!ObjectUtlls.isUndef(cityKey) && !ObjectUtlls.isNull(cityKey)?cityKey:0,
      coutyKey:!ObjectUtlls.isUndef(coutyKey) && !ObjectUtlls.isNull(coutyKey)?coutyKey:0
    }
  }
}

export default {
  state,
  mutations,
  Container,
  namespaced:true
}
