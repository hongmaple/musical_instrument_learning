package com.instrument.mall.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instrument.mall.mapper.MallOrderDetailMapper;
import com.instrument.mall.domain.MallOrderDetail;
import com.instrument.mall.service.IMallOrderDetailService;

/**
 * 订单详情Service业务层处理
 * 
 * @author maple
 * @date 2024-04-20
 */
@Service
public class MallOrderDetailServiceImpl implements IMallOrderDetailService 
{
    @Autowired
    private MallOrderDetailMapper mallOrderDetailMapper;

    /**
     * 查询订单详情
     * 
     * @param id 订单详情主键
     * @return 订单详情
     */
    @Override
    public MallOrderDetail selectMallOrderDetailById(Long id)
    {
        return mallOrderDetailMapper.selectMallOrderDetailById(id);
    }

    /**
     * 查询订单详情列表
     * 
     * @param mallOrderDetail 订单详情
     * @return 订单详情
     */
    @Override
    public List<MallOrderDetail> selectMallOrderDetailList(MallOrderDetail mallOrderDetail)
    {
        return mallOrderDetailMapper.selectMallOrderDetailList(mallOrderDetail);
    }

    /**
     * 新增订单详情
     * 
     * @param mallOrderDetail 订单详情
     * @return 结果
     */
    @Override
    public int insertMallOrderDetail(MallOrderDetail mallOrderDetail)
    {
        return mallOrderDetailMapper.insertMallOrderDetail(mallOrderDetail);
    }

    /**
     * 修改订单详情
     * 
     * @param mallOrderDetail 订单详情
     * @return 结果
     */
    @Override
    public int updateMallOrderDetail(MallOrderDetail mallOrderDetail)
    {
        return mallOrderDetailMapper.updateMallOrderDetail(mallOrderDetail);
    }

    /**
     * 批量删除订单详情
     * 
     * @param ids 需要删除的订单详情主键
     * @return 结果
     */
    @Override
    public int deleteMallOrderDetailByIds(Long[] ids)
    {
        return mallOrderDetailMapper.deleteMallOrderDetailByIds(ids);
    }

    /**
     * 删除订单详情信息
     * 
     * @param id 订单详情主键
     * @return 结果
     */
    @Override
    public int deleteMallOrderDetailById(Long id)
    {
        return mallOrderDetailMapper.deleteMallOrderDetailById(id);
    }
}
