import request from '@/utils/request'

// 查询recognition列表
export function listRecognition(query) {
  return request({
    url: '/music/recognition/list',
    method: 'get',
    params: query
  })
}

// 查询recognition详细
export function getRecognition(id) {
  return request({
    url: '/music/recognition/' + id,
    method: 'get'
  })
}

// 新增recognition
export function addRecognition(data) {
  return request({
    url: '/music/recognition',
    method: 'post',
    data: data
  })
}

// 修改recognition
export function updateRecognition(data) {
  return request({
    url: '/music/recognition',
    method: 'put',
    data: data
  })
}

// 删除recognition
export function delRecognition(id) {
  return request({
    url: '/music/recognition/' + id,
    method: 'delete'
  })
}
