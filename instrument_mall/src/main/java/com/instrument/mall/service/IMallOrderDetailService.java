package com.instrument.mall.service;

import java.util.List;
import com.instrument.mall.domain.MallOrderDetail;

/**
 * 订单详情Service接口
 * 
 * @author maple
 * @date 2024-04-20
 */
public interface IMallOrderDetailService 
{
    /**
     * 查询订单详情
     * 
     * @param id 订单详情主键
     * @return 订单详情
     */
    public MallOrderDetail selectMallOrderDetailById(Long id);

    /**
     * 查询订单详情列表
     * 
     * @param mallOrderDetail 订单详情
     * @return 订单详情集合
     */
    public List<MallOrderDetail> selectMallOrderDetailList(MallOrderDetail mallOrderDetail);

    /**
     * 新增订单详情
     * 
     * @param mallOrderDetail 订单详情
     * @return 结果
     */
    public int insertMallOrderDetail(MallOrderDetail mallOrderDetail);

    /**
     * 修改订单详情
     * 
     * @param mallOrderDetail 订单详情
     * @return 结果
     */
    public int updateMallOrderDetail(MallOrderDetail mallOrderDetail);

    /**
     * 批量删除订单详情
     * 
     * @param ids 需要删除的订单详情主键集合
     * @return 结果
     */
    public int deleteMallOrderDetailByIds(Long[] ids);

    /**
     * 删除订单详情信息
     * 
     * @param id 订单详情主键
     * @return 结果
     */
    public int deleteMallOrderDetailById(Long id);
}
