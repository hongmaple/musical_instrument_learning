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
import com.instrument.mall.domain.MallOrder;
import com.instrument.mall.service.IMallOrderService;
import com.instrument.common.utils.poi.ExcelUtil;
import com.instrument.common.core.page.TableDataInfo;

/**
 * 订单Controller
 * 
 * @author maple
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/mall/order")
public class MallOrderController extends BaseController
{
    @Autowired
    private IMallOrderService mallOrderService;

    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('mall:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(MallOrder mallOrder)
    {
        startPage();
        List<MallOrder> list = mallOrderService.selectMallOrderList(mallOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('mall:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MallOrder mallOrder)
    {
        List<MallOrder> list = mallOrderService.selectMallOrderList(mallOrder);
        ExcelUtil<MallOrder> util = new ExcelUtil<MallOrder>(MallOrder.class);
        util.exportExcel(response, list, "订单数据");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mall:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") String orderId)
    {
        return AjaxResult.success(mallOrderService.selectMallOrderByOrderId(orderId));
    }

    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('mall:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MallOrder mallOrder)
    {
        return toAjax(mallOrderService.insertMallOrder(mallOrder));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('mall:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MallOrder mallOrder)
    {
        return toAjax(mallOrderService.updateMallOrder(mallOrder));
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('mall:order:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable String[] orderIds)
    {
        return toAjax(mallOrderService.deleteMallOrderByOrderIds(orderIds));
    }
}
