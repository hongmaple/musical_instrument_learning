package com.instrument.music.service.impl;

import java.util.List;
import com.instrument.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instrument.music.mapper.MusicCurriculumDetailsMapper;
import com.instrument.music.domain.MusicCurriculumDetails;
import com.instrument.music.service.IMusicCurriculumDetailsService;

/**
 * 课程详情Service业务层处理
 * 
 * @author maple
 * @date 2024-04-20
 */
@Service
public class MusicCurriculumDetailsServiceImpl implements IMusicCurriculumDetailsService 
{
    @Autowired
    private MusicCurriculumDetailsMapper musicCurriculumDetailsMapper;

    /**
     * 查询课程详情
     * 
     * @param id 课程详情主键
     * @return 课程详情
     */
    @Override
    public MusicCurriculumDetails selectMusicCurriculumDetailsById(Long id)
    {
        return musicCurriculumDetailsMapper.selectMusicCurriculumDetailsById(id);
    }

    /**
     * 查询课程详情列表
     * 
     * @param musicCurriculumDetails 课程详情
     * @return 课程详情
     */
    @Override
    public List<MusicCurriculumDetails> selectMusicCurriculumDetailsList(MusicCurriculumDetails musicCurriculumDetails)
    {
        return musicCurriculumDetailsMapper.selectMusicCurriculumDetailsList(musicCurriculumDetails);
    }

    /**
     * 新增课程详情
     * 
     * @param musicCurriculumDetails 课程详情
     * @return 结果
     */
    @Override
    public int insertMusicCurriculumDetails(MusicCurriculumDetails musicCurriculumDetails)
    {
        musicCurriculumDetails.setCreateTime(DateUtils.getNowDate());
        return musicCurriculumDetailsMapper.insertMusicCurriculumDetails(musicCurriculumDetails);
    }

    /**
     * 修改课程详情
     * 
     * @param musicCurriculumDetails 课程详情
     * @return 结果
     */
    @Override
    public int updateMusicCurriculumDetails(MusicCurriculumDetails musicCurriculumDetails)
    {
        musicCurriculumDetails.setUpdateTime(DateUtils.getNowDate());
        return musicCurriculumDetailsMapper.updateMusicCurriculumDetails(musicCurriculumDetails);
    }

    /**
     * 批量删除课程详情
     * 
     * @param ids 需要删除的课程详情主键
     * @return 结果
     */
    @Override
    public int deleteMusicCurriculumDetailsByIds(Long[] ids)
    {
        return musicCurriculumDetailsMapper.deleteMusicCurriculumDetailsByIds(ids);
    }

    /**
     * 删除课程详情信息
     * 
     * @param id 课程详情主键
     * @return 结果
     */
    @Override
    public int deleteMusicCurriculumDetailsById(Long id)
    {
        return musicCurriculumDetailsMapper.deleteMusicCurriculumDetailsById(id);
    }
}
