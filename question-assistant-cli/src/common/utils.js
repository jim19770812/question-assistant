class ObjectUtlls{
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
}

class StringUtils{

  /**
   * 判断字符串是否是空白,同时判断undefined,null,NaN
   * @param {str|int|float|undefined|null|NaN}o
   * @return {boolean}
   */
  static isBlank(o){
    return !ObjectUtlls.isNull(o) && !ObjectUtlls.isUndef(o) && !isNaN(o) && o!==""
  }
}

export default [ObjectUtlls, StringUtils]
