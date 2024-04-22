package com.instrument.music.mapper;

import java.util.List;
import com.instrument.music.domain.MusicCurriculumDetails;

/**
 * 课程详情Mapper接口
 * 
 * @author maple
 * @date 2024-04-20
 */
public interface MusicCurriculumDetailsMapper 
{
    /**
     * 查询课程详情
     * 
     * @param id 课程详情主键
     * @return 课程详情
     */
    public MusicCurriculumDetails selectMusicCurriculumDetailsById(Long id);

    /**
     * 查询课程详情列表
     * 
     * @param musicCurriculumDetails 课程详情
     * @return 课程详情集合
     */
    public List<MusicCurriculumDetails> selectMusicCurriculumDetailsList(MusicCurriculumDetails musicCurriculumDetails);

    /**
     * 新增课程详情
     * 
     * @param musicCurriculumDetails 课程详情
     * @return 结果
     */
    public int insertMusicCurriculumDetails(MusicCurriculumDetails musicCurriculumDetails);

    /**
     * 修改课程详情
     * 
     * @param musicCurriculumDetails 课程详情
     * @return 结果
     */
    public int updateMusicCurriculumDetails(MusicCurriculumDetails musicCurriculumDetails);

    /**
     * 删除课程详情
     * 
     * @param id 课程详情主键
     * @return 结果
     */
    public int deleteMusicCurriculumDetailsById(Long id);

    /**
     * 批量删除课程详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMusicCurriculumDetailsByIds(Long[] ids);
}
