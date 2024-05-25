package com.instrument.music.comment.service;


import com.instrument.music.comment.domain.CurriculumComment;
import com.instrument.music.comment.domain.CurriculumCommentLike;

import java.util.List;

/**
 * 评论管理Service接口
 * 
 * @author ning
 * @date 2022-01-21
 */
public interface ICurriculumCommentService 
{
    /**
     * 首页查询留言列表
     */
    public List<CurriculumComment> selectCommentList(CurriculumComment CurriculumComment);

    /**
     * 首页新增点赞
     */
    public int addCurriculumCommentLike(CurriculumCommentLike CurriculumCommentLike);

    /**
     * 首页删除点赞
     */
    public int delCurriculumCommentLike(CurriculumCommentLike CurriculumCommentLike);

    /**
     * 查询评论管理
     * 
     * @param id 评论管理主键
     * @return 评论管理
     */
    public CurriculumComment selectCurriculumCommentById(Long id);

    /**
     * 查询评论管理列表
     * 
     * @param CurriculumComment 评论管理
     * @return 评论管理集合
     */
    public List<CurriculumComment> selectCurriculumCommentList(CurriculumComment CurriculumComment);

    /**
     * 新增评论管理
     * 
     * @param CurriculumComment 评论管理
     * @return 结果
     */
    public int insertCurriculumComment(CurriculumComment CurriculumComment);

    /**
     * 修改评论管理
     * 
     * @param CurriculumComment 评论管理
     * @return 结果
     */
    public int updateCurriculumComment(CurriculumComment CurriculumComment);

    /**
     * 批量删除评论管理
     * 
     * @param ids 需要删除的评论管理主键集合
     * @return 结果
     */
    public int deleteCurriculumCommentByIds(Long[] ids);

    /**
     * 删除评论管理信息
     * 
     * @param id 评论管理主键
     * @return 结果
     */
    public int deleteCurriculumCommentById(Long id);
}
