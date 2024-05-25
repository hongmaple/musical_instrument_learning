package com.instrument.music.service.impl;

import java.util.List;
import com.instrument.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instrument.music.mapper.InstrumentCategoryMapper;
import com.instrument.music.domain.InstrumentCategory;
import com.instrument.music.service.IInstrumentCategoryService;

/**
 * 乐器分类Service业务层处理
 * 
 * @author maple
 * @date 2024-05-14
 */
@Service
public class InstrumentCategoryServiceImpl implements IInstrumentCategoryService 
{
    @Autowired
    private InstrumentCategoryMapper instrumentCategoryMapper;

    /**
     * 查询乐器分类
     * 
     * @param id 乐器分类主键
     * @return 乐器分类
     */
    @Override
    public InstrumentCategory selectInstrumentCategoryById(Long id)
    {
        return instrumentCategoryMapper.selectInstrumentCategoryById(id);
    }

    /**
     * 查询乐器分类列表
     * 
     * @param instrumentCategory 乐器分类
     * @return 乐器分类
     */
    @Override
    public List<InstrumentCategory> selectInstrumentCategoryList(InstrumentCategory instrumentCategory)
    {
        return instrumentCategoryMapper.selectInstrumentCategoryList(instrumentCategory);
    }

    /**
     * 新增乐器分类
     * 
     * @param instrumentCategory 乐器分类
     * @return 结果
     */
    @Override
    public int insertInstrumentCategory(InstrumentCategory instrumentCategory)
    {
        instrumentCategory.setCreateTime(DateUtils.getNowDate());
        return instrumentCategoryMapper.insertInstrumentCategory(instrumentCategory);
    }

    /**
     * 修改乐器分类
     * 
     * @param instrumentCategory 乐器分类
     * @return 结果
     */
    @Override
    public int updateInstrumentCategory(InstrumentCategory instrumentCategory)
    {
        instrumentCategory.setUpdateTime(DateUtils.getNowDate());
        return instrumentCategoryMapper.updateInstrumentCategory(instrumentCategory);
    }

    /**
     * 批量删除乐器分类
     * 
     * @param ids 需要删除的乐器分类主键
     * @return 结果
     */
    @Override
    public int deleteInstrumentCategoryByIds(Long[] ids)
    {
        return instrumentCategoryMapper.deleteInstrumentCategoryByIds(ids);
    }

    /**
     * 删除乐器分类信息
     * 
     * @param id 乐器分类主键
     * @return 结果
     */
    @Override
    public int deleteInstrumentCategoryById(Long id)
    {
        return instrumentCategoryMapper.deleteInstrumentCategoryById(id);
    }

    @Override
    public List<InstrumentCategory> selectInstrumentCategoryByIds(List<Long> ids) {
        return instrumentCategoryMapper.selectInstrumentCategoryByIds(ids);
    }
}
