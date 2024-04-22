package com.instrument.cms.forum.service.impl;


import com.instrument.cms.forum.domain.CmsForum;
import com.instrument.cms.forum.mapper.CmsForumMapper;
import com.instrument.cms.forum.service.ICmsForumService;
import com.instrument.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 论坛Service业务层处理
 * 
 * @author maple
 * @date 2024-04-20
 */
@Service
public class CmsForumServiceImpl implements ICmsForumService
{
    @Autowired
    private CmsForumMapper cmsForumMapper;

    /**
     * 查询论坛
     * 
     * @param id 论坛主键
     * @return 论坛
     */
    @Override
    public CmsForum selectCmsForumById(Long id)
    {
        return cmsForumMapper.selectCmsForumById(id);
    }

    /**
     * 查询论坛列表
     * 
     * @param cmsForum 论坛
     * @return 论坛
     */
    @Override
    public List<CmsForum> selectCmsForumList(CmsForum cmsForum)
    {
        return cmsForumMapper.selectCmsForumList(cmsForum);
    }

    /**
     * 新增论坛
     * 
     * @param cmsForum 论坛
     * @return 结果
     */
    @Override
    public int insertCmsForum(CmsForum cmsForum)
    {
        cmsForum.setCreateTime(DateUtils.getNowDate());
        return cmsForumMapper.insertCmsForum(cmsForum);
    }

    /**
     * 修改论坛
     * 
     * @param cmsForum 论坛
     * @return 结果
     */
    @Override
    public int updateCmsForum(CmsForum cmsForum)
    {
        cmsForum.setUpdateTime(DateUtils.getNowDate());
        return cmsForumMapper.updateCmsForum(cmsForum);
    }

    /**
     * 批量删除论坛
     * 
     * @param ids 需要删除的论坛主键
     * @return 结果
     */
    @Override
    public int deleteCmsForumByIds(Long[] ids)
    {
        return cmsForumMapper.deleteCmsForumByIds(ids);
    }

    /**
     * 删除论坛信息
     * 
     * @param id 论坛主键
     * @return 结果
     */
    @Override
    public int deleteCmsForumById(Long id)
    {
        return cmsForumMapper.deleteCmsForumById(id);
    }
}
