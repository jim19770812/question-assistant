import {ObjectUtlls} from '@/common/utils'
import Vue from 'vue'
import jq from 'jsonpath'
import {v1 as uuid1} from 'uuid'
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
    this.key="" //当前选中项的key
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
      this.key=this.items[this.items.length-1].key
    }
  }

  /**
   * 移除指定key的项
   * @param {string} key
   * @returns {int}
   */
  remove(key){
    const idx=this.indexByKey(key)
    let ret=null
    if (idx>=0){
      ret=this.items.splice(idx)
    }
    return ret?1:0
  }

  /**
   * 获取项列表
   * @returns {[object]}
   */
  getItems(){
    return this.items
  }

  /**
   * 根据key找到对应的索引
   * @param {string} key
   * @return {number}
   */
  indexByKey(key){
    for(let result=0; result<=this.items.length-1; result++){
      if (this.items[result].key===key){
        return result
      }
    }
    return -1
  }

  /**
   * 根据key获取某项
   * @param {string}key
   * @returns {object}
   */
  getItem(key){
    const index=this.indexByKey(key)
    return this.items[index]
  }

  /**
   * 获取最后一个项
   * @returns {null|*}
   */
  getLastestItem(){
    if (this.items.length<0){
      return null
    }
    return this.items[this.items.length-1]
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
    const ret=ObjectUtlls.hasProperty(typeClassMapper, itemName)?typeClassMapper[itemName].designer:""
    return ret
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
  /**
   * 添加
   * @param {object} states
   * @param {object} payload 载荷：需要添加的json对象
   */
  add: (states, payload)=> {
    states.container.add(payload)
    console.log("add，states.container", states.container)
  },
  /**
   * 移除
   * @param {object} states
   * @param {string}key
   */
  remove:(states, key)=>{
    const idx=states.container.indexByKey(key)
    if (idx>=0){
      states.container.remove(key)
    }
    console.log("remove，states.container", states.container)
  },
  /**
   * 根据key选中组件
   * @param {this}states
   * @param {string} key uuid
   * @returns {void}
   */
  select:(states, key)=>{
    const idx=states.container.indexByKey(key)
    if (idx>=0){
      states.container.key=key
    }
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
    if (ObjectUtlls.hasProperty(payload, "key")){
      const index=states.container.indexByKey(payload.key)
      console.log("after updateValue, ret=",  ret, JSON.stringify(states.container.items[index]))
    }else{
      console.log("after updateValue, ret=",  ret)
    }
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
      key:uuid1(),
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
      key:uuid1(),
      val:phone,
      notEmpty:ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true
    }
  }

  /**
   * 创建文本组件数据
   * @param {string}title 标题
   * @param {bool}notEmpty 是否非空
   * @param {int}rows 行数
   * @param {int}cols 列数
   * @returns {object}
   */
  static createfTextItemData(title, notEmpty, rows, cols){
    return {
      type:"fitem_text",
      key:uuid1(),
      title: title,
      val:"",
      notEmpty:ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true,
      rows:!ObjectUtlls.isUndef(rows) && !ObjectUtlls.isNull(minLen)?minLen:0,
      cols:!ObjectUtlls.isUndef(cols) && !ObjectUtlls.isNull(maxLen)?maxLen:0}
  }

  /**
   * 创建微信组件数据
   * @param {string} weixin 微信号
   * @param {bool} notEmpty 是否非空
   * @returns {object}
   */
  static createfWeixinItemData(weixin, notEmpty){
    return {
      type:"fitem_wechat",
      key:uuid1(),
      val:weixin,
      notEmpty:ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true
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
      key:uuid1(),
      val:options,
      notEmpty:ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true
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
      key:uuid1(),
      val:options,
      notEmpty:ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true,
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
      key:uuid1(),
      notEmpty:ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true,
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
