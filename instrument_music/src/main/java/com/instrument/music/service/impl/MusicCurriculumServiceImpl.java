package com.instrument.music.service.impl;

import java.util.List;
import com.instrument.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instrument.music.mapper.MusicCurriculumMapper;
import com.instrument.music.domain.MusicCurriculum;
import com.instrument.music.service.IMusicCurriculumService;

/**
 * 课程Service业务层处理
 * 
 * @author maple
 * @date 2024-04-20
 */
@Service
public class MusicCurriculumServiceImpl implements IMusicCurriculumService 
{
    @Autowired
    private MusicCurriculumMapper musicCurriculumMapper;

    /**
     * 查询课程
     * 
     * @param id 课程主键
     * @return 课程
     */
    @Override
    public MusicCurriculum selectMusicCurriculumById(Long id)
    {
        return musicCurriculumMapper.selectMusicCurriculumById(id);
    }

    /**
     * 查询课程列表
     * 
     * @param musicCurriculum 课程
     * @return 课程
     */
    @Override
    public List<MusicCurriculum> selectMusicCurriculumList(MusicCurriculum musicCurriculum)
    {
        return musicCurriculumMapper.selectMusicCurriculumList(musicCurriculum);
    }

    /**
     * 新增课程
     * 
     * @param musicCurriculum 课程
     * @return 结果
     */
    @Override
    public int insertMusicCurriculum(MusicCurriculum musicCurriculum)
    {
        musicCurriculum.setCreateTime(DateUtils.getNowDate());
        return musicCurriculumMapper.insertMusicCurriculum(musicCurriculum);
    }

    /**
     * 修改课程
     * 
     * @param musicCurriculum 课程
     * @return 结果
     */
    @Override
    public int updateMusicCurriculum(MusicCurriculum musicCurriculum)
    {
        musicCurriculum.setUpdateTime(DateUtils.getNowDate());
        return musicCurriculumMapper.updateMusicCurriculum(musicCurriculum);
    }

    /**
     * 批量删除课程
     * 
     * @param ids 需要删除的课程主键
     * @return 结果
     */
    @Override
    public int deleteMusicCurriculumByIds(Long[] ids)
    {
        return musicCurriculumMapper.deleteMusicCurriculumByIds(ids);
    }

    /**
     * 删除课程信息
     * 
     * @param id 课程主键
     * @return 结果
     */
    @Override
    public int deleteMusicCurriculumById(Long id)
    {
        return musicCurriculumMapper.deleteMusicCurriculumById(id);
    }
}
