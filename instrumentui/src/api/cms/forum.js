import request from '@/utils/request'

// 查询论坛列表
export function listForum(query) {
  return request({
    url: '/cms/forum/list',
    method: 'get',
    params: query
  })
}

// 查询论坛详细
export function getForum(id) {
  return request({
    url: '/cms/forum/' + id,
    method: 'get'
  })
}

// 新增论坛
export function addForum(data) {
  return request({
    url: '/cms/forum',
    method: 'post',
    data: data
  })
}

// 修改论坛
export function updateForum(data) {
  return request({
    url: '/cms/forum',
    method: 'put',
    data: data
  })
}

// 删除论坛
export function delForum(id) {
  return request({
    url: '/cms/forum/' + id,
    method: 'delete'
  })
}
