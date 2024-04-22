import request from '@/utils/request'

// 查询乐器列表
export function listInstrument(query) {
  return request({
    url: '/music/instrument/list',
    method: 'get',
    params: query
  })
}

// 查询乐器详细
export function getInstrument(id) {
  return request({
    url: '/music/instrument/' + id,
    method: 'get'
  })
}

// 新增乐器
export function addInstrument(data) {
  return request({
    url: '/music/instrument',
    method: 'post',
    data: data
  })
}

// 修改乐器
export function updateInstrument(data) {
  return request({
    url: '/music/instrument',
    method: 'put',
    data: data
  })
}

// 删除乐器
export function delInstrument(id) {
  return request({
    url: '/music/instrument/' + id,
    method: 'delete'
  })
}
