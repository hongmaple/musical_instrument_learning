package com.instrument.mall.mapper;

import java.util.List;
import com.instrument.mall.domain.MallOrder;

/**
 * 订单Mapper接口
 * 
 * @author maple
 * @date 2024-04-20
 */
public interface MallOrderMapper 
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
     * 删除订单
     * 
     * @param orderId 订单主键
     * @return 结果
     */
    public int deleteMallOrderByOrderId(String orderId);

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMallOrderByOrderIds(String[] orderIds);
}
