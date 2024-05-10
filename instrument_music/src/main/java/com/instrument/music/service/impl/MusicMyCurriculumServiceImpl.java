package com.instrument.music.service.impl;

import java.util.List;

import com.instrument.common.exception.ServiceException;
import com.instrument.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instrument.music.mapper.MusicMyCurriculumMapper;
import com.instrument.music.domain.MusicMyCurriculum;
import com.instrument.music.service.IMusicMyCurriculumService;

/**
 * 我的课程Service业务层处理
 * 
 * @author maple
 * @date 2024-04-28
 */
@Service
public class MusicMyCurriculumServiceImpl implements IMusicMyCurriculumService 
{
    @Autowired
    private MusicMyCurriculumMapper musicMyCurriculumMapper;

    /**
     * 查询我的课程
     * 
     * @param id 我的课程主键
     * @return 我的课程
     */
    @Override
    public MusicMyCurriculum selectMusicMyCurriculumById(Long id)
    {
        return musicMyCurriculumMapper.selectMusicMyCurriculumById(id);
    }

    /**
     * 查询我的课程列表
     * 
     * @param musicMyCurriculum 我的课程
     * @return 我的课程
     */
    @Override
    public List<MusicMyCurriculum> selectMusicMyCurriculumList(MusicMyCurriculum musicMyCurriculum)
    {
        return musicMyCurriculumMapper.selectMusicMyCurriculumList(musicMyCurriculum);
    }

    /**
     * 新增我的课程
     * 
     * @param musicMyCurriculum 我的课程
     * @return 结果
     */
    @Override
    public int insertMusicMyCurriculum(MusicMyCurriculum musicMyCurriculum)
    {
        musicMyCurriculum.setCreateTime(DateUtils.getNowDate());
        MusicMyCurriculum musicMyCurriculum1 = new MusicMyCurriculum();
        musicMyCurriculum1.setCreateBy(musicMyCurriculum.getCreateBy());
        musicMyCurriculum1.setCurriculumId(musicMyCurriculum.getCurriculumId());
        long count = musicMyCurriculumMapper.selectMusicMyCurriculumList(musicMyCurriculum1).size();
        if (count>0) throw new ServiceException("已经添加到我的课程了");
        return musicMyCurriculumMapper.insertMusicMyCurriculum(musicMyCurriculum);
    }

    /**
     * 修改我的课程
     * 
     * @param musicMyCurriculum 我的课程
     * @return 结果
     */
    @Override
    public int updateMusicMyCurriculum(MusicMyCurriculum musicMyCurriculum)
    {
        musicMyCurriculum.setUpdateTime(DateUtils.getNowDate());
        return musicMyCurriculumMapper.updateMusicMyCurriculum(musicMyCurriculum);
    }

    /**
     * 批量删除我的课程
     * 
     * @param ids 需要删除的我的课程主键
     * @return 结果
     */
    @Override
    public int deleteMusicMyCurriculumByIds(Long[] ids)
    {
        return musicMyCurriculumMapper.deleteMusicMyCurriculumByIds(ids);
    }

    /**
     * 删除我的课程信息
     * 
     * @param id 我的课程主键
     * @return 结果
     */
    @Override
    public int deleteMusicMyCurriculumById(Long id)
    {
        return musicMyCurriculumMapper.deleteMusicMyCurriculumById(id);
    }
}
