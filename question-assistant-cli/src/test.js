// /**
//  * 下划线转换驼峰
//  * @param {string} name
//  * @returns {*}
//  */
// function toCamal(name) {
//   return name.replace(/_(\w)/g, function(all, letter){
//     return letter.toUpperCase();
//   });
// }
//
// /**
//  * 驼峰转换下划线
//  * @param {string}name
//  * @returns {string}
//  */
// function toUnderline(name) {
//   return name.replace(/([A-Z])/g,"_$1").toLowerCase();
// }
//
// const t=toCamal("fitem_name")
// console.log(t)
// const s=toUnderline(t)
// console.log(s)

// const m={}
// const t=[{"qs_status":0,"cnt":3},{"qs_status":1,"cnt":2},{"qs_status":2,"cnt":2},{"qs_status":-1,"cnt":7}]
// t.forEach(o=>{
//   m[o.qs_status]=o.cnt
// })
// console.log(m)
//

var moment = require('moment');
const t=moment().add(1, 'days')
console.log(t.toDate().getTime())
