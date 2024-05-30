package com.instrument.cms.forum.comment.mapper;

import com.instrument.cms.forum.comment.domain.ForumCommentLike;

import java.util.List;

/**
 * @program: RuoYi-Vue
 * @Author: WangNing
 * @Description: 〈${DESCRIPTION}〉
 * @Date: 2022/1/22 20:08
 */
public interface ForumCommentLikeMapper {
    /**
     * 查询列表
     */
    public List<ForumCommentLike> selectForumCommentLikeList(ForumCommentLike ForumCommentLike);
    /**
     * 新增
     */
    public int addForumCommentLike(ForumCommentLike ForumCommentLike);
    /**
     * 删除关联
     */
    public int deleteForumCommentLike(ForumCommentLike ForumCommentLike);
}
