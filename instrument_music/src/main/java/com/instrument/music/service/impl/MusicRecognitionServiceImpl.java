package com.instrument.music.service.impl;

import java.util.List;
import com.instrument.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instrument.music.mapper.MusicRecognitionMapper;
import com.instrument.music.domain.MusicRecognition;
import com.instrument.music.service.IMusicRecognitionService;

/**
 * recognitionService业务层处理
 * 
 * @author maple
 * @date 2024-05-04
 */
@Service
public class MusicRecognitionServiceImpl implements IMusicRecognitionService 
{
    @Autowired
    private MusicRecognitionMapper musicRecognitionMapper;

    /**
     * 查询recognition
     * 
     * @param id recognition主键
     * @return recognition
     */
    @Override
    public MusicRecognition selectMusicRecognitionById(Long id)
    {
        return musicRecognitionMapper.selectMusicRecognitionById(id);
    }

    /**
     * 查询recognition列表
     * 
     * @param musicRecognition recognition
     * @return recognition
     */
    @Override
    public List<MusicRecognition> selectMusicRecognitionList(MusicRecognition musicRecognition)
    {
        return musicRecognitionMapper.selectMusicRecognitionList(musicRecognition);
    }

    /**
     * 新增recognition
     * 
     * @param musicRecognition recognition
     * @return 结果
     */
    @Override
    public int insertMusicRecognition(MusicRecognition musicRecognition)
    {
        musicRecognition.setCreateTime(DateUtils.getNowDate());
        return musicRecognitionMapper.insertMusicRecognition(musicRecognition);
    }

    /**
     * 修改recognition
     * 
     * @param musicRecognition recognition
     * @return 结果
     */
    @Override
    public int updateMusicRecognition(MusicRecognition musicRecognition)
    {
        musicRecognition.setUpdateTime(DateUtils.getNowDate());
        return musicRecognitionMapper.updateMusicRecognition(musicRecognition);
    }

    /**
     * 批量删除recognition
     * 
     * @param ids 需要删除的recognition主键
     * @return 结果
     */
    @Override
    public int deleteMusicRecognitionByIds(Long[] ids)
    {
        return musicRecognitionMapper.deleteMusicRecognitionByIds(ids);
    }

    /**
     * 删除recognition信息
     * 
     * @param id recognition主键
     * @return 结果
     */
    @Override
    public int deleteMusicRecognitionById(Long id)
    {
        return musicRecognitionMapper.deleteMusicRecognitionById(id);
    }
}
