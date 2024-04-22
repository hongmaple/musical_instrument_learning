package com.instrument.mall.domain;


import com.instrument.common.annotation.Excel;
import com.instrument.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 订单详情对象 mall_order_detail
 * 
 * @author maple
 * @date 2024-04-20
 */
public class MallOrderDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单详情id  */
    private Long id;

    /** 订单id */
    @Excel(name = "订单id")
    private String orderId;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 商品标题 */
    @Excel(name = "商品标题")
    private String title;

    /** 价格,单位：元 */
    @Excel(name = "价格,单位：元")
    private BigDecimal price;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String image;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("goodsId", getGoodsId())
            .append("title", getTitle())
            .append("price", getPrice())
            .append("image", getImage())
            .toString();
    }
}
