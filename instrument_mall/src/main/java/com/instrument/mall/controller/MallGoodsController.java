package com.instrument.mall.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.instrument.common.annotation.Log;
import com.instrument.common.core.controller.BaseController;
import com.instrument.common.core.domain.AjaxResult;
import com.instrument.common.enums.BusinessType;
import com.instrument.mall.domain.MallGoods;
import com.instrument.mall.service.IMallGoodsService;
import com.instrument.common.utils.poi.ExcelUtil;
import com.instrument.common.core.page.TableDataInfo;

/**
 * 商品Controller
 * 
 * @author maple
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/mall/goods")
public class MallGoodsController extends BaseController
{
    @Autowired
    private IMallGoodsService mallGoodsService;

    /**
     * 查询商品列表
     */
    @PreAuthorize("@ss.hasPermi('mall:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(MallGoods mallGoods)
    {
        startPage();
        List<MallGoods> list = mallGoodsService.selectMallGoodsList(mallGoods);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @PreAuthorize("@ss.hasPermi('mall:goods:export')")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MallGoods mallGoods)
    {
        List<MallGoods> list = mallGoodsService.selectMallGoodsList(mallGoods);
        ExcelUtil<MallGoods> util = new ExcelUtil<MallGoods>(MallGoods.class);
        util.exportExcel(response, list, "商品数据");
    }

    /**
     * 获取商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:goods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mallGoodsService.selectMallGoodsById(id));
    }

    /**
     * 新增商品
     */
    @PreAuthorize("@ss.hasPermi('mall:goods:add')")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MallGoods mallGoods)
    {
        return toAjax(mallGoodsService.insertMallGoods(mallGoods));
    }

    /**
     * 修改商品
     */
    @PreAuthorize("@ss.hasPermi('mall:goods:edit')")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MallGoods mallGoods)
    {
        return toAjax(mallGoodsService.updateMallGoods(mallGoods));
    }

    /**
     * 删除商品
     */
    @PreAuthorize("@ss.hasPermi('mall:goods:remove')")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mallGoodsService.deleteMallGoodsByIds(ids));
    }
}
