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
    return !ObjectUtlls.isNull(o) && !ObjectUtlls.isUndef(o) && !isNaN(o) && o!==""
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
