package com.instrument.music.mapper;

import java.util.List;
import com.instrument.music.domain.MusicInstrument;

/**
 * 乐器Mapper接口
 * 
 * @author maple
 * @date 2024-04-20
 */
public interface MusicInstrumentMapper 
{
    /**
     * 查询乐器
     * 
     * @param id 乐器主键
     * @return 乐器
     */
    public MusicInstrument selectMusicInstrumentById(Long id);

    /**
     * 查询乐器列表
     * 
     * @param musicInstrument 乐器
     * @return 乐器集合
     */
    public List<MusicInstrument> selectMusicInstrumentList(MusicInstrument musicInstrument);

    /**
     * 新增乐器
     * 
     * @param musicInstrument 乐器
     * @return 结果
     */
    public int insertMusicInstrument(MusicInstrument musicInstrument);

    /**
     * 修改乐器
     * 
     * @param musicInstrument 乐器
     * @return 结果
     */
    public int updateMusicInstrument(MusicInstrument musicInstrument);

    /**
     * 删除乐器
     * 
     * @param id 乐器主键
     * @return 结果
     */
    public int deleteMusicInstrumentById(Long id);

    /**
     * 批量删除乐器
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMusicInstrumentByIds(Long[] ids);
}
