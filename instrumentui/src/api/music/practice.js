import request from '@/utils/request'

// 查询课程练习列表
export function listPractice(query) {
  return request({
    url: '/music/practice/list',
    method: 'get',
    params: query
  })
}

// 查询我的课程练习列表
export function myListPractice(query) {
  return request({
    url: '/music/practice/myList',
    method: 'get',
    params: query
  })
}

// 查询课程练习详细
export function getPractice(id) {
  return request({
    url: '/music/practice/' + id,
    method: 'get'
  })
}

// 新增课程练习
export function addPractice(data) {
  return request({
    url: '/music/practice',
    method: 'post',
    data: data
  })
}

// 修改课程练习
export function updatePractice(data) {
  return request({
    url: '/music/practice',
    method: 'put',
    data: data
  })
}

// 删除课程练习
export function delPractice(id) {
  return request({
    url: '/music/practice/' + id,
    method: 'delete'
  })
}
