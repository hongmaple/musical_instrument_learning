package com.instrument.mall.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.instrument.common.exception.ServiceException;
import com.instrument.common.utils.DateUtils;
import com.instrument.common.utils.bean.BeanUtils;
import com.instrument.common.utils.uuid.IdUtils;
import com.instrument.common.utils.uuid.IdWorker;
import com.instrument.mall.domain.MallOrderDetail;
import com.instrument.mall.mapper.MallOrderDetailMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instrument.mall.mapper.MallOrderMapper;
import com.instrument.mall.domain.MallOrder;
import com.instrument.mall.service.IMallOrderService;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private MallOrderDetailMapper mallOrderDetailMapper;

    @Autowired
    private IdWorker idWorker;

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
    @Transactional
    public int insertMallOrder(MallOrder mallOrder)
    {
        mallOrder.setCreateTime(DateUtils.getNowDate());
        mallOrder.setOrderId(String.valueOf(idWorker.nextId()));
        if (CollectionUtils.isEmpty(mallOrder.getMallGoodsList())) throw new ServiceException("请选择商品");
        List<MallOrderDetail> mallOrderDetails = new ArrayList<>();
        mallOrder.getMallGoodsList().forEach(mallGoods -> {
            MallOrderDetail mallOrderDetail = new MallOrderDetail();
            BeanUtils.copyProperties(mallGoods,mallOrderDetail);
            mallOrderDetail.setImage(mallGoods.getImages());
            mallOrderDetail.setGoodsId(mallGoods.getId());
            mallOrderDetail.setOrderId(mallOrder.getOrderId());
            mallOrderDetails.add(mallOrderDetail);
        });
        BigDecimal sum = mallOrderDetails.stream().map(MallOrderDetail::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        mallOrder.setActualPay(sum);
        mallOrder.setTotalPay(sum);
        if (mallOrderDetailMapper.insertMallOrderDetailList(mallOrderDetails)==0) throw new ServiceException("购买失败");
        if (mallOrderMapper.insertMallOrder(mallOrder)==0) throw new ServiceException("购买失败");
        return 1;
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
        if (mallOrder.getStatus() == 2) {
            mallOrder.setPaymentTime(DateUtils.getNowDate());
        } else if (mallOrder.getStatus() == 3) {
            mallOrder.setConsignTime(DateUtils.getNowDate());
        } else if (mallOrder.getStatus() == 4) {
            mallOrder.setEndTime(DateUtils.getNowDate());
        } else if (mallOrder.getStatus() == 5) {
            mallOrder.setCloseTime(DateUtils.getNowDate());
        }
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
