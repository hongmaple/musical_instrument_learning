package com.instrument.music.comment.mapper;

import com.instrument.music.comment.domain.CurriculumCommentLike;

import java.util.List;

/**
 * @program: RuoYi-Vue
 * @Author: WangNing
 * @Description: 〈${DESCRIPTION}〉
 * @Date: 2022/1/22 20:08
 */
public interface CurriculumCommentLikeMapper {
    /**
     * 查询列表
     */
    public List<CurriculumCommentLike> selectcurriculumCommentLikeList(CurriculumCommentLike curriculumCommentLike);
    /**
     * 新增
     */
    public int addcurriculumCommentLike(CurriculumCommentLike curriculumCommentLike);
    /**
     * 删除关联
     */
    public int deletecurriculumCommentLike(CurriculumCommentLike curriculumCommentLike);
}
