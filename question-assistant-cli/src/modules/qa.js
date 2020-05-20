import {ObjectUtlls} from '@/common/utils'
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
    designer:"item-radio-designer",
    editor:"item-radio-editor",
    render:"item-radio-render"
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
   * @param {{path, key, newVal}} payload 负载对象（path：jsonpath路径，key是项的唯一索引，newVal是更新后的值
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
  // eslint-disable-next-line valid-jsdoc
  /**
   * 应用更新，按照传入的jsonpath找到匹配项列表，然后依次执行回调
   * @param {object}states vue状态对象
   * @param {path, key, function(node):void} payload 负载对象（path：jsonpath路径，key是项的唯一索引，function(node):void是个找到匹配项之后要执行的回调（注意：可能要执行多次））
   * @returns {void}
   */
  applyUpdate(states, payload){
    if (!ObjectUtlls.hasProperty(payload, "path")){
      throw new Error("updateValue时发现未传入有效的更新路径")
    }
    if (!ObjectUtlls.hasProperty(payload, "callback")){
      throw new Error("updateValue时发现未传入有效的回调函数")
    }
    if (typeof(payload.callback)!=="function"){
      throw new Error("updateValue时发现未传入有效的回调函数，callback参数必须是function类型")
    }
    const node=jq.query(states.container, payload.path, 100)
    if (ObjectUtlls.isUndef(node) || !(node instanceof Array)){
      throw new Error(`未能根据jsonpath：${payload.path}修改对象值`)
    }
    node.forEach(n=>{
      payload.callback(n)
    })
    if (ObjectUtlls.hasProperty(payload, "key")){
      const index=states.container.indexByKey(payload.key)
      console.log("after updateValue, ret=",  node, JSON.stringify(states.container.items[index]))
    }else{
      console.log("after updateValue, ret=",  node)
    }
  },
  /**
   * 批量更新组件对象值
   * @param {object}states
   * @param {[{path, key, newVal}]} payload
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
      rows:!ObjectUtlls.isUndef(rows) && !ObjectUtlls.isNull(rows)?rows:0,
      cols:!ObjectUtlls.isUndef(cols) && !ObjectUtlls.isNull(cols)?cols:0}
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
      title:title,
      key:uuid1(),
      val:[], /*选中项*/
      options:options,/*待选项*/
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
      title:title,
      val:[],
      options:options,
      notEmpty:ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true,
      minSelection:{
        enabled:false,
        val:1
      },
      maxSelection:{
        enabled:false,
        val:3
      }
    }
  }

  /**
   *
   * 创建地区组件数据
   * @param {string}title 标题
   * @param{bool} notEmpty 是否非空
   * @param {string} province 省关键字
   * @param {string} city 市关键字
   * @param {string}county 县关键字
   * @returns {object}
   */
  static createfAreaItemData(title, notEmpty,province, city, county){
    return {
      type:"fitem_area",
      key:uuid1(),
      title:title,
      notEmpty:ObjectUtlls.isUndef(notEmpty) && !ObjectUtlls.isNull(notEmpty)?notEmpty:true,
      province:!ObjectUtlls.isUndef(province) && !ObjectUtlls.isNull(province)?province:0,
      city:!ObjectUtlls.isUndef(city) && !ObjectUtlls.isNull(city)?city:0,
      county:!ObjectUtlls.isUndef(county) && !ObjectUtlls.isNull(county)?county:0
    }
  }
}

export default {
  state,
  mutations,
  Container,
  namespaced:true
}
