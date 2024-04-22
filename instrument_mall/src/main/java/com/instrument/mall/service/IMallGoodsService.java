package com.instrument.mall.service;

import java.util.List;
import com.instrument.mall.domain.MallGoods;

/**
 * 商品Service接口
 * 
 * @author maple
 * @date 2024-04-20
 */
public interface IMallGoodsService 
{
    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    public MallGoods selectMallGoodsById(Long id);

    /**
     * 查询商品列表
     * 
     * @param mallGoods 商品
     * @return 商品集合
     */
    public List<MallGoods> selectMallGoodsList(MallGoods mallGoods);

    /**
     * 新增商品
     * 
     * @param mallGoods 商品
     * @return 结果
     */
    public int insertMallGoods(MallGoods mallGoods);

    /**
     * 修改商品
     * 
     * @param mallGoods 商品
     * @return 结果
     */
    public int updateMallGoods(MallGoods mallGoods);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品主键集合
     * @return 结果
     */
    public int deleteMallGoodsByIds(Long[] ids);

    /**
     * 删除商品信息
     * 
     * @param id 商品主键
     * @return 结果
     */
    public int deleteMallGoodsById(Long id);
}
