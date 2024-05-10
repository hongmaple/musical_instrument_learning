package com.instrument.mall.mapper;

import java.util.List;
import com.instrument.mall.domain.MallOrderDetail;
import org.apache.ibatis.annotations.Param;

/**
 * 订单详情Mapper接口
 * 
 * @author maple
 * @date 2024-04-20
 */
public interface MallOrderDetailMapper 
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
     * 删除订单详情
     * 
     * @param id 订单详情主键
     * @return 结果
     */
    public int deleteMallOrderDetailById(Long id);

    /**
     * 批量删除订单详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMallOrderDetailByIds(Long[] ids);

    int insertMallOrderDetailList(@Param("mallOrderDetails") List<MallOrderDetail> mallOrderDetails);
}
