package com.instrument.mall.service.impl;

import java.util.List;
import com.instrument.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instrument.mall.mapper.MallGoodsMapper;
import com.instrument.mall.domain.MallGoods;
import com.instrument.mall.service.IMallGoodsService;

/**
 * 商品Service业务层处理
 * 
 * @author maple
 * @date 2024-04-20
 */
@Service
public class MallGoodsServiceImpl implements IMallGoodsService 
{
    @Autowired
    private MallGoodsMapper mallGoodsMapper;

    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    @Override
    public MallGoods selectMallGoodsById(Long id)
    {
        return mallGoodsMapper.selectMallGoodsById(id);
    }

    /**
     * 查询商品列表
     * 
     * @param mallGoods 商品
     * @return 商品
     */
    @Override
    public List<MallGoods> selectMallGoodsList(MallGoods mallGoods)
    {
        return mallGoodsMapper.selectMallGoodsList(mallGoods);
    }

    /**
     * 新增商品
     * 
     * @param mallGoods 商品
     * @return 结果
     */
    @Override
    public int insertMallGoods(MallGoods mallGoods)
    {
        mallGoods.setCreateTime(DateUtils.getNowDate());
        return mallGoodsMapper.insertMallGoods(mallGoods);
    }

    /**
     * 修改商品
     * 
     * @param mallGoods 商品
     * @return 结果
     */
    @Override
    public int updateMallGoods(MallGoods mallGoods)
    {
        mallGoods.setUpdateTime(DateUtils.getNowDate());
        return mallGoodsMapper.updateMallGoods(mallGoods);
    }

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品主键
     * @return 结果
     */
    @Override
    public int deleteMallGoodsByIds(Long[] ids)
    {
        return mallGoodsMapper.deleteMallGoodsByIds(ids);
    }

    /**
     * 删除商品信息
     * 
     * @param id 商品主键
     * @return 结果
     */
    @Override
    public int deleteMallGoodsById(Long id)
    {
        return mallGoodsMapper.deleteMallGoodsById(id);
    }
}
