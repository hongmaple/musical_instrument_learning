import request from '@/utils/request'

// 查询订单详情列表
export function listMallDetail(query) {
  return request({
    url: '/mall/mallDetail/list',
    method: 'get',
    params: query
  })
}

// 查询订单详情详细
export function getMallDetail(id) {
  return request({
    url: '/mall/mallDetail/' + id,
    method: 'get'
  })
}

// 新增订单详情
export function addMallDetail(data) {
  return request({
    url: '/mall/mallDetail',
    method: 'post',
    data: data
  })
}

// 修改订单详情
export function updateMallDetail(data) {
  return request({
    url: '/mall/mallDetail',
    method: 'put',
    data: data
  })
}

// 删除订单详情
export function delMallDetail(id) {
  return request({
    url: '/mall/mallDetail/' + id,
    method: 'delete'
  })
}
