import request from '@/utils/request'

// 首页查询评论列表
export function forumListComment(query) {
  return request({
    url: '/forum/comment/forum/list',
    headers: {
      isToken: false
    },
    method: 'get',
    params: query,
    timeout: 40000
  })
}

// 首页新增评论
export function forumAddComment(data) {
  return request({
    url: '/forum/comment/forum/addComment',
    method: 'post',
    data: data,
    headers: {
      isToken: false
    },
    timeout: 40000
  })
}

// 首页新增点赞
export function addForumCommentLike(data) {
  return request({
    url: '/forum/comment/forum/addForumCommentLike',
    method: 'post',
    data: data,
    headers: {
      isToken: false
    },
    timeout: 40000
  })
}

// 首页删除点赞
export function delForumCommentLike(data) {
  return request({
    url: '/forum/comment/forum/delForumCommentLike',
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
    url: '/forum/comment/list',
    method: 'get',
    params: query
  })
}

// 查询评论管理详细
export function getComment(id) {
  return request({
    url: '/forum/comment/' + id,
    method: 'get'
  })
}

// 新增评论管理
export function addComment(data) {
  return request({
    url: '/forum/comment',
    method: 'post',
    data: data
  })
}

// 修改评论管理
export function updateComment(data) {
  return request({
    url: '/forum/comment',
    method: 'put',
    data: data
  })
}

// 删除评论管理
export function delComment(id) {
  return request({
    url: '/forum/comment/' + id,
    method: 'delete'
  })
}
