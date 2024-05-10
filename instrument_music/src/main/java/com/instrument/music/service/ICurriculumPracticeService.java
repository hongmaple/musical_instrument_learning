package com.instrument.music.service;

import java.util.List;
import com.instrument.music.domain.CurriculumPractice;

/**
 * 课程练习Service接口
 * 
 * @author maple
 * @date 2024-04-28
 */
public interface ICurriculumPracticeService 
{
    /**
     * 查询课程练习
     * 
     * @param id 课程练习主键
     * @return 课程练习
     */
    public CurriculumPractice selectCurriculumPracticeById(Long id);

    /**
     * 查询课程练习列表
     * 
     * @param curriculumPractice 课程练习
     * @return 课程练习集合
     */
    public List<CurriculumPractice> selectCurriculumPracticeList(CurriculumPractice curriculumPractice);

    /**
     * 新增课程练习
     * 
     * @param curriculumPractice 课程练习
     * @return 结果
     */
    public int insertCurriculumPractice(CurriculumPractice curriculumPractice);

    /**
     * 修改课程练习
     * 
     * @param curriculumPractice 课程练习
     * @return 结果
     */
    public int updateCurriculumPractice(CurriculumPractice curriculumPractice);

    /**
     * 批量删除课程练习
     * 
     * @param ids 需要删除的课程练习主键集合
     * @return 结果
     */
    public int deleteCurriculumPracticeByIds(Long[] ids);

    /**
     * 删除课程练习信息
     * 
     * @param id 课程练习主键
     * @return 结果
     */
    public int deleteCurriculumPracticeById(Long id);
}
