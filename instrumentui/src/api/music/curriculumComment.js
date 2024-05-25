import request from '@/utils/request'

// 首页查询评论列表
export function curriculumListComment(query) {
  return request({
    url: '/curriculum/comment/curriculum/list',
    headers: {
      isToken: false
    },
    method: 'get',
    params: query,
    timeout: 40000
  })
}

// 首页新增评论
export function curriculumAddComment(data) {
  return request({
    url: '/curriculum/comment/curriculum/addComment',
    method: 'post',
    data: data,
    headers: {
      isToken: false
    },
    timeout: 40000
  })
}

// 首页新增点赞
export function addcurriculumCommentLike(data) {
  return request({
    url: '/curriculum/comment/curriculum/addcurriculumCommentLike',
    method: 'post',
    data: data,
    headers: {
      isToken: false
    },
    timeout: 40000
  })
}

// 首页删除点赞
export function delcurriculumCommentLike(data) {
  return request({
    url: '/curriculum/comment/curriculum/delcurriculumCommentLike',
    method: 'post',
    data: data,
    headers: {
      isToken: false
    },
    timeout: 40000
  })
}

// 查询评论管理列表
export function listComment(query) {
  return request({
    url: '/curriculum/comment/list',
    method: 'get',
    params: query
  })
}

// 查询评论管理详细
export function getComment(id) {
  return request({
    url: '/curriculum/comment/' + id,
    method: 'get'
  })
}

// 新增评论管理
export function addComment(data) {
  return request({
    url: '/curriculum/comment',
    method: 'post',
    data: data
  })
}

// 修改评论管理
export function updateComment(data) {
  return request({
    url: '/curriculum/comment',
    method: 'put',
    data: data
  })
}

// 删除评论管理
export function delComment(id) {
  return request({
    url: '/curriculum/comment/' + id,
    method: 'delete'
  })
}
