import request from '@/utils/request'

// 查询订单列表
export function listOrder(query) {
  return request({
    url: '/mall/order/list',
    method: 'get',
    params: query
  })
}

// 查询订单详细
export function getOrder(orderId) {
  return request({
    url: '/mall/order/' + orderId,
    method: 'get'
  })
}

// 新增订单
export function addOrder(data) {
  return request({
    url: '/mall/order',
    method: 'post',
    data: data
  })
}

// 修改订单
export function updateOrder(data) {
  return request({
    url: '/mall/order',
    method: 'put',
    data: data
  })
}

// 删除订单
export function delOrder(orderId) {
  return request({
    url: '/mall/order/' + orderId,
    method: 'delete'
  })
}
