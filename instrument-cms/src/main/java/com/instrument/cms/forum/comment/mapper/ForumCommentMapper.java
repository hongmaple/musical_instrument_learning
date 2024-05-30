package com.instrument.cms.forum.comment.mapper;

import com.instrument.cms.forum.comment.domain.ForumComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论管理Mapper接口
 * 
 * @author ning
 * @date 2022-01-21
 */
public interface ForumCommentMapper 
{
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
    public List<ForumComment> selectForumCommentList(ForumComment ForumComment);

    public List<ForumComment> selectForumCommentListBetweenCreateTime(@Param("type") String type, @Param("delFlag") String delFlag, @Param("createTimeBegin") String createTimeBegin, @Param("createTimeEnd") String createTimeEnd);

    /**
     * 查询子留言列表
     */
    public List<ForumComment> selectChildCommentList(ForumComment ForumComment);

    /**
     * 新增评论管理
     * 
     * @param ForumComment 评论管理
     * @return 结果
     */
    public int insertForumComment(ForumComment ForumComment);

    /**
     * 修改评论管理
     * 
     * @param ForumComment 评论管理
     * @return 结果
     */
    public int updateForumComment(ForumComment ForumComment);

    /**
     * 删除评论管理
     * 
     * @param id 评论管理主键
     * @return 结果
     */
    public int deleteForumCommentById(Long id);

    /**
     * 批量删除评论管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteForumCommentByIds(Long[] ids);

    /**
     * 删除评论管理
     *
     * @param id 评论管理主键
     * @return 结果
     */
    public int updateDelFlagById(Long id);

    /**
     * 批量删除评论管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int updateDelFlagByIds(Long[] ids);
}
