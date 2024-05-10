package com.instrument.music.service;

import java.util.List;
import com.instrument.music.domain.MusicRecognition;

/**
 * recognitionService接口
 * 
 * @author maple
 * @date 2024-05-04
 */
public interface IMusicRecognitionService 
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
     * 批量删除recognition
     * 
     * @param ids 需要删除的recognition主键集合
     * @return 结果
     */
    public int deleteMusicRecognitionByIds(Long[] ids);

    /**
     * 删除recognition信息
     * 
     * @param id recognition主键
     * @return 结果
     */
    public int deleteMusicRecognitionById(Long id);
}
