package com.instrument.music.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.instrument.common.utils.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instrument.music.mapper.MusicInstrumentMapper;
import com.instrument.music.domain.MusicInstrument;
import com.instrument.music.service.IMusicInstrumentService;

/**
 * 乐器Service业务层处理
 * 
 * @author maple
 * @date 2024-04-20
 */
@Service
public class MusicInstrumentServiceImpl implements IMusicInstrumentService 
{
    @Autowired
    private MusicInstrumentMapper musicInstrumentMapper;

    /**
     * 查询乐器
     * 
     * @param id 乐器主键
     * @return 乐器
     */
    @Override
    public MusicInstrument selectMusicInstrumentById(Long id)
    {
        return musicInstrumentMapper.selectMusicInstrumentById(id);
    }

    /**
     * 查询乐器列表
     * 
     * @param musicInstrument 乐器
     * @return 乐器
     */
    @Override
    public List<MusicInstrument> selectMusicInstrumentList(MusicInstrument musicInstrument)
    {
        return musicInstrumentMapper.selectMusicInstrumentList(musicInstrument);
    }

    /**
     * 新增乐器
     * 
     * @param musicInstrument 乐器
     * @return 结果
     */
    @Override
    public int insertMusicInstrument(MusicInstrument musicInstrument)
    {
        musicInstrument.setCreateTime(DateUtils.getNowDate());
        return musicInstrumentMapper.insertMusicInstrument(musicInstrument);
    }

    /**
     * 修改乐器
     * 
     * @param musicInstrument 乐器
     * @return 结果
     */
    @Override
    public int updateMusicInstrument(MusicInstrument musicInstrument)
    {
        musicInstrument.setUpdateTime(DateUtils.getNowDate());
        return musicInstrumentMapper.updateMusicInstrument(musicInstrument);
    }

    /**
     * 批量删除乐器
     * 
     * @param ids 需要删除的乐器主键
     * @return 结果
     */
    @Override
    public int deleteMusicInstrumentByIds(Long[] ids)
    {
        return musicInstrumentMapper.deleteMusicInstrumentByIds(ids);
    }

    /**
     * 删除乐器信息
     * 
     * @param id 乐器主键
     * @return 结果
     */
    @Override
    public int deleteMusicInstrumentById(Long id)
    {
        return musicInstrumentMapper.deleteMusicInstrumentById(id);
    }

    @Override
    public List<MusicInstrument> selectMusicInstrumentByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) return new ArrayList<>();
        return musicInstrumentMapper.selectMusicInstrumentByIds(ids);
    }
}
