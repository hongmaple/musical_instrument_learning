package com.instrument.cms.forum.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.instrument.cms.forum.domain.CmsForum;
import com.instrument.cms.forum.service.ICmsForumService;
import com.instrument.common.utils.poi.ExcelUtil;
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
import com.instrument.common.core.page.TableDataInfo;

/**
 * 论坛Controller
 * 
 * @author maple
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/cms/forum")
public class CmsForumController extends BaseController
{
    @Autowired
    private ICmsForumService cmsForumService;

    /**
     * 查询论坛列表
     */
    @PreAuthorize("@ss.hasPermi('cms:forum:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmsForum cmsForum)
    {
        startPage();
        List<CmsForum> list = cmsForumService.selectCmsForumList(cmsForum);
        return getDataTable(list);
    }

    /**
     * 导出论坛列表
     */
    @PreAuthorize("@ss.hasPermi('cms:forum:export')")
    @Log(title = "论坛", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsForum cmsForum)
    {
        List<CmsForum> list = cmsForumService.selectCmsForumList(cmsForum);
        ExcelUtil<CmsForum> util = new ExcelUtil<CmsForum>(CmsForum.class);
        util.exportExcel(response, list, "论坛数据");
    }

    /**
     * 获取论坛详细信息
     */
    @PreAuthorize("@ss.hasPermi('cms:forum:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cmsForumService.selectCmsForumById(id));
    }

    /**
     * 新增论坛
     */
    @PreAuthorize("@ss.hasPermi('cms:forum:add')")
    @Log(title = "论坛", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsForum cmsForum)
    {
        return toAjax(cmsForumService.insertCmsForum(cmsForum));
    }

    /**
     * 修改论坛
     */
    @PreAuthorize("@ss.hasPermi('cms:forum:edit')")
    @Log(title = "论坛", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsForum cmsForum)
    {
        return toAjax(cmsForumService.updateCmsForum(cmsForum));
    }

    /**
     * 删除论坛
     */
    @PreAuthorize("@ss.hasPermi('cms:forum:remove')")
    @Log(title = "论坛", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmsForumService.deleteCmsForumByIds(ids));
    }
}
