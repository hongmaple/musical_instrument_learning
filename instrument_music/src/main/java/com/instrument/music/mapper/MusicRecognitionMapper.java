package com.instrument.music.mapper;

import java.util.List;
import com.instrument.music.domain.MusicRecognition;

/**
 * recognitionMapper接口
 * 
 * @author maple
 * @date 2024-05-04
 */
public interface MusicRecognitionMapper 
{
    /**
     * 查询recognition
     * 
     * @param id recognition主键
     * @return recognition
     */
    public MusicRecognition selectMusicRecognitionById(Long id);

    /**
     * 查询recognition列表
     * 
     * @param musicRecognition recognition
     * @return recognition集合
     */
    public List<MusicRecognition> selectMusicRecognitionList(MusicRecognition musicRecognition);

    /**
     * 新增recognition
     * 
     * @param musicRecognition recognition
     * @return 结果
     */
    public int insertMusicRecognition(MusicRecognition musicRecognition);

    /**
     * 修改recognition
     * 
     * @param musicRecognition recognition
     * @return 结果
     */
    public int updateMusicRecognition(MusicRecognition musicRecognition);

    /**
     * 删除recognition
     * 
     * @param id recognition主键
     * @return 结果
     */
    public int deleteMusicRecognitionById(Long id);

    /**
     * 批量删除recognition
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMusicRecognitionByIds(Long[] ids);
}
