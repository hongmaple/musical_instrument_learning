package com.instrument.music.service.impl;

import java.util.List;

import com.instrument.common.exception.ServiceException;
import com.instrument.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instrument.music.mapper.CurriculumPracticeMapper;
import com.instrument.music.domain.CurriculumPractice;
import com.instrument.music.service.ICurriculumPracticeService;

/**
 * 课程练习Service业务层处理
 * 
 * @author maple
 * @date 2024-04-28
 */
@Service
public class CurriculumPracticeServiceImpl implements ICurriculumPracticeService 
{
    @Autowired
    private CurriculumPracticeMapper curriculumPracticeMapper;

    /**
     * 查询课程练习
     * 
     * @param id 课程练习主键
     * @return 课程练习
     */
    @Override
    public CurriculumPractice selectCurriculumPracticeById(Long id)
    {
        return curriculumPracticeMapper.selectCurriculumPracticeById(id);
    }

    /**
     * 查询课程练习列表
     * 
     * @param curriculumPractice 课程练习
     * @return 课程练习
     */
    @Override
    public List<CurriculumPractice> selectCurriculumPracticeList(CurriculumPractice curriculumPractice)
    {
        return curriculumPracticeMapper.selectCurriculumPracticeList(curriculumPractice);
    }

    /**
     * 新增课程练习
     * 
     * @param curriculumPractice 课程练习
     * @return 结果
     */
    @Override
    public int insertCurriculumPractice(CurriculumPractice curriculumPractice)
    {
        curriculumPractice.setCreateTime(DateUtils.getNowDate());
        long count = curriculumPracticeMapper.selectCurriculumPracticeList(curriculumPractice).stream().count();
        if (count>0) throw new ServiceException("已经上传过练习文件了");
        return curriculumPracticeMapper.insertCurriculumPractice(curriculumPractice);
    }

    /**
     * 修改课程练习
     * 
     * @param curriculumPractice 课程练习
     * @return 结果
     */
    @Override
    public int updateCurriculumPractice(CurriculumPractice curriculumPractice)
    {
        curriculumPractice.setUpdateTime(DateUtils.getNowDate());
        return curriculumPracticeMapper.updateCurriculumPractice(curriculumPractice);
    }

    /**
     * 批量删除课程练习
     * 
     * @param ids 需要删除的课程练习主键
     * @return 结果
     */
    @Override
    public int deleteCurriculumPracticeByIds(Long[] ids)
    {
        return curriculumPracticeMapper.deleteCurriculumPracticeByIds(ids);
    }

    /**
     * 删除课程练习信息
     * 
     * @param id 课程练习主键
     * @return 结果
     */
    @Override
    public int deleteCurriculumPracticeById(Long id)
    {
        return curriculumPracticeMapper.deleteCurriculumPracticeById(id);
    }
}
