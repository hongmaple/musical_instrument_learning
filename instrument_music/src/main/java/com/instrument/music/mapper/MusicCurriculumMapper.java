package com.instrument.music.mapper;

import java.util.List;
import com.instrument.music.domain.MusicCurriculum;

/**
 * 课程Mapper接口
 * 
 * @author maple
 * @date 2024-04-20
 */
public interface MusicCurriculumMapper 
{
    /**
     * 查询课程
     * 
     * @param id 课程主键
     * @return 课程
     */
    public MusicCurriculum selectMusicCurriculumById(Long id);

    /**
     * 查询课程列表
     * 
     * @param musicCurriculum 课程
     * @return 课程集合
     */
    public List<MusicCurriculum> selectMusicCurriculumList(MusicCurriculum musicCurriculum);

    /**
     * 新增课程
     * 
     * @param musicCurriculum 课程
     * @return 结果
     */
    public int insertMusicCurriculum(MusicCurriculum musicCurriculum);

    /**
     * 修改课程
     * 
     * @param musicCurriculum 课程
     * @return 结果
     */
    public int updateMusicCurriculum(MusicCurriculum musicCurriculum);

    /**
     * 删除课程
     * 
     * @param id 课程主键
     * @return 结果
     */
    public int deleteMusicCurriculumById(Long id);

    /**
     * 批量删除课程
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMusicCurriculumByIds(Long[] ids);
}
