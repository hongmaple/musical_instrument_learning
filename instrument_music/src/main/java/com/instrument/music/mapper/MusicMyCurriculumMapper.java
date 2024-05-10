package com.instrument.music.mapper;

import java.util.List;
import com.instrument.music.domain.MusicMyCurriculum;

/**
 * 我的课程Mapper接口
 * 
 * @author maple
 * @date 2024-04-28
 */
public interface MusicMyCurriculumMapper 
{
    /**
     * 查询我的课程
     * 
     * @param id 我的课程主键
     * @return 我的课程
     */
    public MusicMyCurriculum selectMusicMyCurriculumById(Long id);

    /**
     * 查询我的课程列表
     * 
     * @param musicMyCurriculum 我的课程
     * @return 我的课程集合
     */
    public List<MusicMyCurriculum> selectMusicMyCurriculumList(MusicMyCurriculum musicMyCurriculum);

    /**
     * 新增我的课程
     * 
     * @param musicMyCurriculum 我的课程
     * @return 结果
     */
    public int insertMusicMyCurriculum(MusicMyCurriculum musicMyCurriculum);

    /**
     * 修改我的课程
     * 
     * @param musicMyCurriculum 我的课程
     * @return 结果
     */
    public int updateMusicMyCurriculum(MusicMyCurriculum musicMyCurriculum);

    /**
     * 删除我的课程
     * 
     * @param id 我的课程主键
     * @return 结果
     */
    public int deleteMusicMyCurriculumById(Long id);

    /**
     * 批量删除我的课程
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMusicMyCurriculumByIds(Long[] ids);
}
