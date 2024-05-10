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
import com.instrument.mall.domain.MallOrderDetail;
import com.instrument.mall.service.IMallOrderDetailService;
import com.instrument.common.utils.poi.ExcelUtil;
import com.instrument.common.core.page.TableDataInfo;

/**
 * 订单详情Controller
 * 
 * @author maple
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/mall/mallDetail")
public class MallOrderDetailController extends BaseController
{
    @Autowired
    private IMallOrderDetailService mallOrderDetailService;

    /**
     * 查询订单详情列表
     */
    @PreAuthorize("@ss.hasPermi('mall:mallDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(MallOrderDetail mallOrderDetail)
    {
        startPage();
        List<MallOrderDetail> list = mallOrderDetailService.selectMallOrderDetailList(mallOrderDetail);
        return getDataTable(list);
    }

    /**
     * 导出订单详情列表
     */
    @PreAuthorize("@ss.hasPermi('mall:mallDetail:export')")
    @Log(title = "订单详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MallOrderDetail mallOrderDetail)
    {
        List<MallOrderDetail> list = mallOrderDetailService.selectMallOrderDetailList(mallOrderDetail);
        ExcelUtil<MallOrderDetail> util = new ExcelUtil<MallOrderDetail>(MallOrderDetail.class);
        util.exportExcel(response, list, "订单详情数据");
    }

    /**
     * 获取订单详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:mallDetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mallOrderDetailService.selectMallOrderDetailById(id));
    }

    /**
     * 新增订单详情
     */
    @PreAuthorize("@ss.hasPermi('mall:mallDetail:add')")
    @Log(title = "订单详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MallOrderDetail mallOrderDetail)
    {
        mallOrderDetail.setCreateBy(getUsername());
        return toAjax(mallOrderDetailService.insertMallOrderDetail(mallOrderDetail));
    }

    /**
     * 修改订单详情
     */
    @PreAuthorize("@ss.hasPermi('mall:mallDetail:edit')")
    @Log(title = "订单详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MallOrderDetail mallOrderDetail)
    {
        mallOrderDetail.setUpdateBy(getUsername());
        return toAjax(mallOrderDetailService.updateMallOrderDetail(mallOrderDetail));
    }

    /**
     * 删除订单详情
     */
    @PreAuthorize("@ss.hasPermi('mall:mallDetail:remove')")
    @Log(title = "订单详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mallOrderDetailService.deleteMallOrderDetailByIds(ids));
    }
}
