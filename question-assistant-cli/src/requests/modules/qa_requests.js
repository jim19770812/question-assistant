import { request } from '@/requests/requests'
import { StringUtils } from '@/common/utils'

/**
 * 获取问卷列表
 * @param {string} idOrName
 * @param {number} pageNum
 * @param {number} pageSize
 * @param {[int]} qsStatusArray 问卷状态（0：待发布，1：已发布，2：已停止）
 * @returns {AxiosPromise}
 */
export function getQuestionSheetList(idOrName, pageNum, pageSize, qsStatusArray){
  const params={
    //idOrName:"",
    pageNum:pageNum,
    pageSize:pageSize,
    qsStatusList:qsStatusArray
  }
  if (!StringUtils.isBlank(idOrName)){
    params.idOrName=idOrName
  }
  return request({
    method:"get",
    url:"/xhr/v1/qa/questionSheet/list",
    params:params
  })
}

/**
 * 获取问卷统计
 * @returns {AxiosPromise}
 */
export function getQuestionSheetStats(){
  return request({
    method:"get",
    url:"/xhr/v1/qa/questionSheet/stats"
  })
}
/**
 * 根据ID查找问题
 * @param {number} qsId
 * @returns {AxiosPromise}
 */
export function getQuestionSheet(qsId){
  return request({
    method:"get",
    url:"/xhr/v1/qa/questionSheet",
    params:{
      id:qsId
    }
  })
}

/**
 * 删除问卷
 * @param {number} qsId
 * @returns {AxiosPromise}
 */
export function removeQuestionSheet(qsId){
  return request({
    method:"delete",
    url:"/xhr/v1/qa/questionSheet",
    params:{
      qs_id:qsId
    }
  })
}
/**
 * 保存问卷（新增/修改）
 * @param {number} qsId
 * @param {string} qsName
 * @param {string|{}} qsTemplate
 * @returns {AxiosPromise}
 */
export function saveQuestionSheet(qsId, qsName, qsTemplate){
  return request({
    method:"post",
    url:"/xhr/v1/qa/questionSheet",
    data:{
      qs_id:qsId,
      qs_name:qsName,
      qs_template:JSON.stringify(qsTemplate)
    }
  })
}

/**
 * 保存回答
 * @param {number} awId
 * @param {number} qsId
 * @param {string|{}} awContent
 * @returns {AxiosPromise}
 */
export function saveAnswer(awId, qsId, awContent){
  return request({
    method:"post",
    url:"/xhr/v1/qa/answer",
    data:{
      qs_id:qsId,
      aw_id:awId,
      aw_content:awContent
    }
  })
}

