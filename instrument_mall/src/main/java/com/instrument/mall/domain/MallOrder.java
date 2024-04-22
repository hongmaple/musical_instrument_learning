package com.instrument.mall.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.instrument.common.annotation.Excel;
import com.instrument.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单对象 mall_order
 * 
 * @author maple
 * @date 2024-04-20
 */
public class MallOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    @Excel(name = "订单id")
    private String orderId;

    /** 总金额 单位分 */
    @Excel(name = "总金额 单位分")
    private BigDecimal totalPay;

    /** 实付金额 单位分 */
    @Excel(name = "实付金额 单位分")
    private BigDecimal actualPay;

    /** 买家昵称 */
    @Excel(name = "买家昵称")
    private String buyerNick;

    /** 买家全称 */
    @Excel(name = "买家全称")
    private String receiver;

    /** 收货人手机 */
    @Excel(name = "收货人手机")
    private String receiverMobile;

    /** 省份 */
    @Excel(name = "省份")
    private String receiverState;

    /** 城市 */
    @Excel(name = "城市")
    private String receiverCity;

    /** 区/县  */
    @Excel(name = "区/县 ")
    private String receiverDistrict;

    /** 收货地址 */
    @Excel(name = "收货地址")
    private String receiverAddress;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 付款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "付款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentTime;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date consignTime;

    /** 交易完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 交易关闭时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易关闭时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date closeTime;

    /** $column.columnComment */
    private Integer isDeleted;

    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setTotalPay(BigDecimal totalPay) 
    {
        this.totalPay = totalPay;
    }

    public BigDecimal getTotalPay() 
    {
        return totalPay;
    }
    public void setActualPay(BigDecimal actualPay) 
    {
        this.actualPay = actualPay;
    }

    public BigDecimal getActualPay() 
    {
        return actualPay;
    }
    public void setBuyerNick(String buyerNick) 
    {
        this.buyerNick = buyerNick;
    }

    public String getBuyerNick() 
    {
        return buyerNick;
    }
    public void setReceiver(String receiver) 
    {
        this.receiver = receiver;
    }

    public String getReceiver() 
    {
        return receiver;
    }
    public void setReceiverMobile(String receiverMobile) 
    {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverMobile() 
    {
        return receiverMobile;
    }
    public void setReceiverState(String receiverState) 
    {
        this.receiverState = receiverState;
    }

    public String getReceiverState() 
    {
        return receiverState;
    }
    public void setReceiverCity(String receiverCity) 
    {
        this.receiverCity = receiverCity;
    }

    public String getReceiverCity() 
    {
        return receiverCity;
    }
    public void setReceiverDistrict(String receiverDistrict) 
    {
        this.receiverDistrict = receiverDistrict;
    }

    public String getReceiverDistrict() 
    {
        return receiverDistrict;
    }
    public void setReceiverAddress(String receiverAddress) 
    {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverAddress() 
    {
        return receiverAddress;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setPaymentTime(Date paymentTime) 
    {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() 
    {
        return paymentTime;
    }
    public void setConsignTime(Date consignTime) 
    {
        this.consignTime = consignTime;
    }

    public Date getConsignTime() 
    {
        return consignTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setCloseTime(Date closeTime) 
    {
        this.closeTime = closeTime;
    }

    public Date getCloseTime() 
    {
        return closeTime;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("totalPay", getTotalPay())
            .append("actualPay", getActualPay())
            .append("buyerNick", getBuyerNick())
            .append("receiver", getReceiver())
            .append("receiverMobile", getReceiverMobile())
            .append("receiverState", getReceiverState())
            .append("receiverCity", getReceiverCity())
            .append("receiverDistrict", getReceiverDistrict())
            .append("receiverAddress", getReceiverAddress())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("paymentTime", getPaymentTime())
            .append("consignTime", getConsignTime())
            .append("endTime", getEndTime())
            .append("closeTime", getCloseTime())
            .append("isDeleted", getIsDeleted())
            .append("updateBy", getUpdateBy())
            .append("createBy", getCreateBy())
            .toString();
    }
}
