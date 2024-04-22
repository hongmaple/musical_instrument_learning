import request from '@/utils/request'

// 查询课程列表
export function listCurriculum(query) {
  return request({
    url: '/music/curriculum/list',
    method: 'get',
    params: query
  })
}

// 查询课程详细
export function getCurriculum(id) {
  return request({
    url: '/music/curriculum/' + id,
    method: 'get'
  })
}

// 新增课程
export function addCurriculum(data) {
  return request({
    url: '/music/curriculum',
    method: 'post',
    data: data
  })
}

// 修改课程
export function updateCurriculum(data) {
  return request({
    url: '/music/curriculum',
    method: 'put',
    data: data
  })
}

// 删除课程
export function delCurriculum(id) {
  return request({
    url: '/music/curriculum/' + id,
    method: 'delete'
  })
}
