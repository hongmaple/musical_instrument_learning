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
import com.instrument.music.domain.CurriculumPractice;
import com.instrument.music.service.ICurriculumPracticeService;
import com.instrument.common.utils.poi.ExcelUtil;
import com.instrument.common.core.page.TableDataInfo;

/**
 * 课程练习Controller
 * 
 * @author maple
 * @date 2024-04-28
 */
@RestController
@RequestMapping("/music/practice")
public class CurriculumPracticeController extends BaseController
{
    @Autowired
    private ICurriculumPracticeService curriculumPracticeService;

    /**
     * 查询课程练习列表
     */
    @PreAuthorize("@ss.hasPermi('music:practice:list')")
    @GetMapping("/list")
    public TableDataInfo list(CurriculumPractice curriculumPractice)
    {
        startPage();
        List<CurriculumPractice> list = curriculumPracticeService.selectCurriculumPracticeList(curriculumPractice);
        return getDataTable(list);
    }

    /**
     * 查询课程练习列表
     */
    @PreAuthorize("@ss.hasPermi('music:practice:myList')")
    @GetMapping("/myList")
    public TableDataInfo myList(CurriculumPractice curriculumPractice)
    {
        startPage();
        curriculumPractice.setCreateBy(getUsername());
        List<CurriculumPractice> list = curriculumPracticeService.selectCurriculumPracticeList(curriculumPractice);
        return getDataTable(list);
    }


    /**
     * 导出课程练习列表
     */
    @PreAuthorize("@ss.hasPermi('music:practice:export')")
    @Log(title = "课程练习", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CurriculumPractice curriculumPractice)
    {
        List<CurriculumPractice> list = curriculumPracticeService.selectCurriculumPracticeList(curriculumPractice);
        ExcelUtil<CurriculumPractice> util = new ExcelUtil<CurriculumPractice>(CurriculumPractice.class);
        util.exportExcel(response, list, "课程练习数据");
    }

    /**
     * 获取课程练习详细信息
     */
    @PreAuthorize("@ss.hasPermi('music:practice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(curriculumPracticeService.selectCurriculumPracticeById(id));
    }

    /**
     * 新增课程练习
     */
    @PreAuthorize("@ss.hasPermi('music:practice:add')")
    @Log(title = "课程练习", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CurriculumPractice curriculumPractice)
    {
        curriculumPractice.setCreateBy(getUsername());
        return toAjax(curriculumPracticeService.insertCurriculumPractice(curriculumPractice));
    }

    /**
     * 修改课程练习
     */
    @PreAuthorize("@ss.hasPermi('music:practice:edit')")
    @Log(title = "课程练习", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CurriculumPractice curriculumPractice)
    {
        curriculumPractice.setUpdateBy(getUsername());
        return toAjax(curriculumPracticeService.updateCurriculumPractice(curriculumPractice));
    }

    /**
     * 删除课程练习
     */
    @PreAuthorize("@ss.hasPermi('music:practice:remove')")
    @Log(title = "课程练习", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(curriculumPracticeService.deleteCurriculumPracticeByIds(ids));
    }
}
