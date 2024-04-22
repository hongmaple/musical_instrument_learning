package com.instrument.mall.service;

import java.util.List;
import com.instrument.mall.domain.MallOrder;

/**
 * 订单Service接口
 * 
 * @author maple
 * @date 2024-04-20
 */
public interface IMallOrderService 
{
    /**
     * 查询订单
     * 
     * @param orderId 订单主键
     * @return 订单
     */
    public MallOrder selectMallOrderByOrderId(String orderId);

    /**
     * 查询订单列表
     * 
     * @param mallOrder 订单
     * @return 订单集合
     */
    public List<MallOrder> selectMallOrderList(MallOrder mallOrder);

    /**
     * 新增订单
     * 
     * @param mallOrder 订单
     * @return 结果
     */
    public int insertMallOrder(MallOrder mallOrder);

    /**
     * 修改订单
     * 
     * @param mallOrder 订单
     * @return 结果
     */
    public int updateMallOrder(MallOrder mallOrder);

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteMallOrderByOrderIds(String[] orderIds);

    /**
     * 删除订单信息
     * 
     * @param orderId 订单主键
     * @return 结果
     */
    public int deleteMallOrderByOrderId(String orderId);
}
