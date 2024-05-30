package com.instrument.cms.forum.comment.service;

import com.instrument.cms.forum.comment.domain.ForumComment;
import com.instrument.cms.forum.comment.domain.ForumCommentLike;

import java.util.List;

/**
 * 评论管理Service接口
 * 
 * @author ning
 * @date 2022-01-21
 */
public interface IForumCommentService 
{
    /**
     * 首页查询留言列表
     */
    public List<ForumComment> selectCommentList(ForumComment forumComment);

    /**
     * 首页新增点赞
     */
    public int addForumCommentLike(ForumCommentLike forumCommentLike);

    /**
     * 首页删除点赞
     */
    public int delForumCommentLike(ForumCommentLike forumCommentLike);

    /**
     * 查询评论管理
     * 
     * @param id 评论管理主键
     * @return 评论管理
     */
    public ForumComment selectForumCommentById(Long id);

    /**
     * 查询评论管理列表
     * 
     * @param ForumComment 评论管理
     * @return 评论管理集合
     */
    public List<ForumComment> selectForumCommentList(ForumComment forumComment);

    /**
     * 新增评论管理
     * 
     * @param ForumComment 评论管理
     * @return 结果
     */
    public int insertForumComment(ForumComment forumComment);

    /**
     * 修改评论管理
     * 
     * @param ForumComment 评论管理
     * @return 结果
     */
    public int updateForumComment(ForumComment forumComment);

    /**
     * 批量删除评论管理
     * 
     * @param ids 需要删除的评论管理主键集合
     * @return 结果
     */
    public int deleteForumCommentByIds(Long[] ids);

    /**
     * 删除评论管理信息
     * 
     * @param id 评论管理主键
     * @return 结果
     */
    public int deleteForumCommentById(Long id);
}
