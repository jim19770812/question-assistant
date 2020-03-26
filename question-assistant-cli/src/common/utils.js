
class Utils{
  /*
   * 从Vue实例的Childrens里查找某个属性=给定值的Children对象
   */
  static findChildren(childrens, dataPropName, dataPropValue){
    for(const x in childrens){
      if (!Object.prototype.hasOwnProperty.call(x, dataPropName)){
        return null
      }
      if(x[dataPropName]===dataPropValue){
        return x
      }
    }
    return 1
  }
}

export default [Utils]
