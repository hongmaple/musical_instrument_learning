package com.instrument.mall.service.impl;

import java.util.List;
import com.instrument.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instrument.mall.mapper.MallOrderMapper;
import com.instrument.mall.domain.MallOrder;
import com.instrument.mall.service.IMallOrderService;

/**
 * 订单Service业务层处理
 * 
 * @author maple
 * @date 2024-04-20
 */
@Service
public class MallOrderServiceImpl implements IMallOrderService 
{
    @Autowired
    private MallOrderMapper mallOrderMapper;

    /**
     * 查询订单
     * 
     * @param orderId 订单主键
     * @return 订单
     */
    @Override
    public MallOrder selectMallOrderByOrderId(String orderId)
    {
        return mallOrderMapper.selectMallOrderByOrderId(orderId);
    }

    /**
     * 查询订单列表
     * 
     * @param mallOrder 订单
     * @return 订单
     */
    @Override
    public List<MallOrder> selectMallOrderList(MallOrder mallOrder)
    {
        return mallOrderMapper.selectMallOrderList(mallOrder);
    }

    /**
     * 新增订单
     * 
     * @param mallOrder 订单
     * @return 结果
     */
    @Override
    public int insertMallOrder(MallOrder mallOrder)
    {
        mallOrder.setCreateTime(DateUtils.getNowDate());
        return mallOrderMapper.insertMallOrder(mallOrder);
    }

    /**
     * 修改订单
     * 
     * @param mallOrder 订单
     * @return 结果
     */
    @Override
    public int updateMallOrder(MallOrder mallOrder)
    {
        return mallOrderMapper.updateMallOrder(mallOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteMallOrderByOrderIds(String[] orderIds)
    {
        return mallOrderMapper.deleteMallOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单信息
     * 
     * @param orderId 订单主键
     * @return 结果
     */
    @Override
    public int deleteMallOrderByOrderId(String orderId)
    {
        return mallOrderMapper.deleteMallOrderByOrderId(orderId);
    }
}
