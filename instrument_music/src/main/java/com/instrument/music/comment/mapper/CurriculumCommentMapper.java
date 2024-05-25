package com.instrument.music.comment.mapper;

import com.instrument.music.comment.domain.CurriculumComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论管理Mapper接口
 * 
 * @author ning
 * @date 2022-01-21
 */
public interface CurriculumCommentMapper 
{
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

    public List<CurriculumComment> selectCurriculumCommentListBetweenCreateTime(@Param("type") String type, @Param("delFlag") String delFlag, @Param("createTimeBegin") String createTimeBegin, @Param("createTimeEnd") String createTimeEnd);

    /**
     * 查询子留言列表
     */
    public List<CurriculumComment> selectChildCommentList(CurriculumComment CurriculumComment);

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
     * 删除评论管理
     * 
     * @param id 评论管理主键
     * @return 结果
     */
    public int deleteCurriculumCommentById(Long id);

    /**
     * 批量删除评论管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCurriculumCommentByIds(Long[] ids);

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
