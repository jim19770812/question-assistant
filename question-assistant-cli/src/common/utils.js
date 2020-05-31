import jq from 'jsonpath'
import axios from 'axios'
import { APIException, ErrorConsts } from '@/exceptions/exceptions'
import vue from '@/main'
import moment from 'moment'

/**
 * 对象工具类
 */
export class ObjectUtlls{
  /**
   * 判断是否是undefined
   * @param {str|int|float|undefined|NaN} o 对象
   * @return {boolean}
   */
  static isUndef(o){
    return typeof(o)=="undefined"
  }

  /**
   * 判断是否是null
   * @param {string | int | float | undefined | NaN}o
   * @return {boolean} none
   */
  static isNull(o){
    return o ===null
  }

  /**
   * 是否是未定义或null
   * @param {object | number | string | date} o
   * @returns {boolean}
   */
  static isUnDefOrNull(o){
    return this.isUndef(o) || this.isNull(o)
  }

  /**
   * 判断对象是否有某属性
   * @param {object} target
   * @param {string} propName
   * @returns {boolean}
   */
  static hasProperty(target, propName){
    return Object.prototype.hasOwnProperty.call(target, propName)
  }
}

/**
 * 字符串工具类
 */
export class StringUtils{

  /**
   * 判断字符串是否是空白,同时判断undefined,null,NaN
   * @param {str|int|float|undefined|null|NaN}o
   * @return {boolean}
   */
  static isBlank(o){
    const ret=ObjectUtlls.isNull(o) || ObjectUtlls.isUndef(o) || o===""
    return ret
  }
}

/**
 * 组件工具类
 */
export class ComponentUtils{
  /**
   * 下划线转换驼峰
   * @param {string} name
   * @returns {*}
   */
  static toCamal(name) {
    return name.replace(/_(\w)/g, function(all, letter){
      return letter.toUpperCase();
    });
  }

  /**
   * 驼峰转换下划线
   * @param {string}name
   * @returns {string}
   */
  static toUnderline(name) {
    return name.replace(/([A-Z])/g,"_$1").toLowerCase();
  }
}

export class JsonPathUtils{
  /**
   * 查找单节点
   * @param {object}obj
   * @param {string}path
   * @returns {object}
   */
  static findSingleNode(obj, path){
    const ret=JsonPathUtils.findNodeList(obj, path)
    return ret[0]
  }

  /**
   * 查找节点列表
   * @param {object}obj
   * @param {string}path
   * @returns {object}
   */
  static findNodeList(obj, path){
    const ret=jq.query(obj, path)
    if (ObjectUtlls.isNull(ret) || ObjectUtlls.isUndef(ret)){
      return null
    }
    if (!Array.isArray(ret)){
      throw new APIException(ErrorConsts.CLI_1006, "findSingleNode查找到的节点并非数组！")
    }
    return ret
  }
}

/**
 * SessionStorage工具类
 */
export class StorageUtils{
  /**
   * 从storage中获取属性
   * @param {string} attrName
   * @returns {string|null}
   */
  static get(attrName){
    if (StorageUtils.contains(attrName)){
      return localStorage.getItem(attrName)
    }
    return null;
  }

  /**
   * 向storage中写入属性
   * @param {string} attrName
   * @param {string} attrVal
   * @returns {void}
   */
  static set(attrName, attrVal){
    localStorage.setItem(attrName, attrVal)
    console.log("Storage 已经写入"+attrName)
  }

  /**
   * 判断是否包含某属性
   * @param {string} attrName
   * @returns {boolean}
   */
  static contains(attrName){
    return ObjectUtlls.hasProperty(localStorage, attrName)
  }

  /**
   * 从storage中移除属性
   * @param {string} attrName
   * @returns {void}
   */
  static remove(attrName){
    const ret=StorageUtils.contains(attrName)
    if (ret){
      localStorage.removeItem(attrName)
      console.log("Storage 已经移除"+attrName)
    }
  }
}

/**
 * 令牌工具
 */
export class TokenUtils{
  /**
   * 判断token是否存在
   * 过期会返回空串，没过期就自动延期24小时
   * @returns {boolean}
   */
  static hasToken(){
    if (!StorageUtils.contains("token")) {
      return false
    }
    return true;
  }

  /**
   * 保存token，默认1天过期
   * @param {string} token
   * @returns {boolean}
   */
  static saveToken(token){
    StorageUtils.remove("token")
    const dt = new Date()
    StorageUtils.set("token", JSON.stringify({
      token:token,
      expire:dt.getTime() + 1000 * 60 * 60 * 24
    }))
    return true
  }

  /**
   * 移除token
   * @returns {void}
   */
  static removeToken(){
    StorageUtils.remove("token")
  }
  /**
   * 获取token,先判断是否过期，
   * 过期会返回空串，没过期就自动延期24小时
   * @returns {string}
   */
  static getToken(){
    const temp=StorageUtils.get("token")
    if (ObjectUtlls.isUnDefOrNull(temp)){
      return ""
    }
    const t=JSON.parse(temp)
    if (t!==null) {
      const dt = new Date()
      if (t.expire > dt.getTime()) {
        //未过期，延长过期时间
        StorageUtils.remove("token")
        StorageUtils.set("token", JSON.stringify({
          token: t.token,
          expire: moment().add(1, 'days').toDate().getTime()
        }))
      }else{
        //如果过期就返回空串
        StorageUtils.remove("token")
        return ""
      }
    }
    return t.token
  }
}

/**
 * 下拉通知工具
 */
export class NoticeUtils{
  /**
   * 显示成功提示
   * @param {string}message
   * @returns {void}
   */
  static success(message){
    vue.$message({
      type:"success",
      message:message,
      showClose: true
    })
  }

  /**
   * 显示提示信息
   * @param {string}message
   * @returns {void}
   */
  static info(message){
    vue.$message({
      type:"info",
      message:message,
      showClose: true
    })
  }

  /**
   * 显示警告提示
   * @param {string}message
   * @returns {void}
   */
  static warn(message){
    vue.$message({
      type:"warning",
      message:message,
      showClose: true
    })
  }

  /**
   * 显示错误提示
   * @param {string}message
   * @returns {void}
   */
  static error(message){
    vue.$message({
      type: 'error',
      message: message,
      showClose: true
    })
  }
}

