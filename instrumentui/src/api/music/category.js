import request from '@/utils/request'

// 查询乐器分类列表
export function listCategory(query) {
  return request({
    url: '/music/category/list',
    method: 'get',
    params: query
  })
}

// 查询乐器分类详细
export function getCategory(id) {
  return request({
    url: '/music/category/' + id,
    method: 'get'
  })
}

// 新增乐器分类
export function addCategory(data) {
  return request({
    url: '/music/category',
    method: 'post',
    data: data
  })
}

// 修改乐器分类
export function updateCategory(data) {
  return request({
    url: '/music/category',
    method: 'put',
    data: data
  })
}

// 删除乐器分类
export function delCategory(id) {
  return request({
    url: '/music/category/' + id,
    method: 'delete'
  })
}
