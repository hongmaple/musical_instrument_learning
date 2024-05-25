package com.instrument.music.controller;

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
import com.instrument.music.domain.InstrumentCategory;
import com.instrument.music.service.IInstrumentCategoryService;
import com.instrument.common.utils.poi.ExcelUtil;
import com.instrument.common.core.page.TableDataInfo;

/**
 * 乐器分类Controller
 * 
 * @author maple
 * @date 2024-05-14
 */
@RestController
@RequestMapping("/music/category")
public class InstrumentCategoryController extends BaseController
{
    @Autowired
    private IInstrumentCategoryService instrumentCategoryService;

    /**
     * 查询乐器分类列表
     */
    @PreAuthorize("@ss.hasPermi('music:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(InstrumentCategory instrumentCategory)
    {
        startPage();
        List<InstrumentCategory> list = instrumentCategoryService.selectInstrumentCategoryList(instrumentCategory);
        return getDataTable(list);
    }

    /**
     * 导出乐器分类列表
     */
    @PreAuthorize("@ss.hasPermi('music:category:export')")
    @Log(title = "乐器分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InstrumentCategory instrumentCategory)
    {
        List<InstrumentCategory> list = instrumentCategoryService.selectInstrumentCategoryList(instrumentCategory);
        ExcelUtil<InstrumentCategory> util = new ExcelUtil<InstrumentCategory>(InstrumentCategory.class);
        util.exportExcel(response, list, "乐器分类数据");
    }

    /**
     * 获取乐器分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('music:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(instrumentCategoryService.selectInstrumentCategoryById(id));
    }

    /**
     * 新增乐器分类
     */
    @PreAuthorize("@ss.hasPermi('music:category:add')")
    @Log(title = "乐器分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InstrumentCategory instrumentCategory)
    {
        instrumentCategory.setCreateBy(getUsername());
        return toAjax(instrumentCategoryService.insertInstrumentCategory(instrumentCategory));
    }

    /**
     * 修改乐器分类
     */
    @PreAuthorize("@ss.hasPermi('music:category:edit')")
    @Log(title = "乐器分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InstrumentCategory instrumentCategory)
    {
        instrumentCategory.setCreateBy(getUsername());
        return toAjax(instrumentCategoryService.updateInstrumentCategory(instrumentCategory));
    }

    /**
     * 删除乐器分类
     */
    @PreAuthorize("@ss.hasPermi('music:category:remove')")
    @Log(title = "乐器分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(instrumentCategoryService.deleteInstrumentCategoryByIds(ids));
    }
}
