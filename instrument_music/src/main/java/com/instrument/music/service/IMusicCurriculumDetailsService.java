package com.instrument.music.service;

import java.util.List;
import com.instrument.music.domain.MusicCurriculumDetails;

/**
 * 课程详情Service接口
 * 
 * @author maple
 * @date 2024-04-20
 */
public interface IMusicCurriculumDetailsService 
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
     * 批量删除课程详情
     * 
     * @param ids 需要删除的课程详情主键集合
     * @return 结果
     */
    public int deleteMusicCurriculumDetailsByIds(Long[] ids);

    /**
     * 删除课程详情信息
     * 
     * @param id 课程详情主键
     * @return 结果
     */
    public int deleteMusicCurriculumDetailsById(Long id);
}
