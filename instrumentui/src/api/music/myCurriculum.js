import request from '@/utils/request'

// 查询我的课程列表
export function listMyCurriculum(query) {
  return request({
    url: '/music/MyCurriculum/list',
    method: 'get',
    params: query
  })
}

// 查询我的课程详细
export function getMyCurriculum(id) {
  return request({
    url: '/music/MyCurriculum/' + id,
    method: 'get'
  })
}

// 新增我的课程
export function addMyCurriculum(data) {
  return request({
    url: '/music/MyCurriculum',
    method: 'post',
    data: data
  })
}

// 修改我的课程
export function updateMyCurriculum(data) {
  return request({
    url: '/music/MyCurriculum',
    method: 'put',
    data: data
  })
}

// 删除我的课程
export function delMyCurriculum(id) {
  return request({
    url: '/music/MyCurriculum/' + id,
    method: 'delete'
  })
}
