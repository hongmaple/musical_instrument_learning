package com.instrument.mall.mapper;

import java.util.List;
import com.instrument.mall.domain.MallGoods;

/**
 * 商品Mapper接口
 * 
 * @author maple
 * @date 2024-04-20
 */
public interface MallGoodsMapper 
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
     * 删除商品
     * 
     * @param id 商品主键
     * @return 结果
     */
    public int deleteMallGoodsById(Long id);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMallGoodsByIds(Long[] ids);
}
