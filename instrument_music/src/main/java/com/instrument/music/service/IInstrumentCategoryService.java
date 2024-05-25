package com.instrument.music.service;

import java.util.List;
import com.instrument.music.domain.InstrumentCategory;

/**
 * 乐器分类Service接口
 * 
 * @author maple
 * @date 2024-05-14
 */
public interface IInstrumentCategoryService 
{
    /**
     * 查询乐器分类
     * 
     * @param id 乐器分类主键
     * @return 乐器分类
     */
    public InstrumentCategory selectInstrumentCategoryById(Long id);

    /**
     * 查询乐器分类列表
     * 
     * @param instrumentCategory 乐器分类
     * @return 乐器分类集合
     */
    public List<InstrumentCategory> selectInstrumentCategoryList(InstrumentCategory instrumentCategory);

    /**
     * 新增乐器分类
     * 
     * @param instrumentCategory 乐器分类
     * @return 结果
     */
    public int insertInstrumentCategory(InstrumentCategory instrumentCategory);

    /**
     * 修改乐器分类
     * 
     * @param instrumentCategory 乐器分类
     * @return 结果
     */
    public int updateInstrumentCategory(InstrumentCategory instrumentCategory);

    /**
     * 批量删除乐器分类
     * 
     * @param ids 需要删除的乐器分类主键集合
     * @return 结果
     */
    public int deleteInstrumentCategoryByIds(Long[] ids);

    /**
     * 删除乐器分类信息
     * 
     * @param id 乐器分类主键
     * @return 结果
     */
    public int deleteInstrumentCategoryById(Long id);

    List<InstrumentCategory> selectInstrumentCategoryByIds(List<Long> collect);
}
