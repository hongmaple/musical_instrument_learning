import request from '@/utils/request'

// 查询课程详情列表
export function listCurriculumDetails(query) {
  return request({
    url: '/music/curriculumDetails/list',
    method: 'get',
    params: query
  })
}

// 查询课程详情详细
export function getCurriculumDetails(id) {
  return request({
    url: '/music/curriculumDetails/' + id,
    method: 'get'
  })
}

// 新增课程详情
export function addCurriculumDetails(data) {
  return request({
    url: '/music/curriculumDetails',
    method: 'post',
    data: data
  })
}

// 修改课程详情
export function updateCurriculumDetails(data) {
  return request({
    url: '/music/curriculumDetails',
    method: 'put',
    data: data
  })
}

// 删除课程详情
export function delCurriculumDetails(id) {
  return request({
    url: '/music/curriculumDetails/' + id,
    method: 'delete'
  })
}
