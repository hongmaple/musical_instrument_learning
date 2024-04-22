package com.instrument.cms.forum.mapper;


import com.instrument.cms.forum.domain.CmsForum;

import java.util.List;

/**
 * 论坛Mapper接口
 * 
 * @author maple
 * @date 2024-04-20
 */
public interface CmsForumMapper 
{
    /**
     * 查询论坛
     * 
     * @param id 论坛主键
     * @return 论坛
     */
    public CmsForum selectCmsForumById(Long id);

    /**
     * 查询论坛列表
     * 
     * @param cmsForum 论坛
     * @return 论坛集合
     */
    public List<CmsForum> selectCmsForumList(CmsForum cmsForum);

    /**
     * 新增论坛
     * 
     * @param cmsForum 论坛
     * @return 结果
     */
    public int insertCmsForum(CmsForum cmsForum);

    /**
     * 修改论坛
     * 
     * @param cmsForum 论坛
     * @return 结果
     */
    public int updateCmsForum(CmsForum cmsForum);

    /**
     * 删除论坛
     * 
     * @param id 论坛主键
     * @return 结果
     */
    public int deleteCmsForumById(Long id);

    /**
     * 批量删除论坛
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmsForumByIds(Long[] ids);
}
