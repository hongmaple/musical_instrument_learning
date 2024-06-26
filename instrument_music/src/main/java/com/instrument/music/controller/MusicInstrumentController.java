package com.instrument.music.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.instrument.music.domain.InstrumentCategory;
import com.instrument.music.service.IInstrumentCategoryService;
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
import com.instrument.music.domain.MusicInstrument;
import com.instrument.music.service.IMusicInstrumentService;
import com.instrument.common.utils.poi.ExcelUtil;
import com.instrument.common.core.page.TableDataInfo;

/**
 * 乐器Controller
 * 
 * @author maple
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/music/instrument")
public class MusicInstrumentController extends BaseController
{
    @Autowired
    private IMusicInstrumentService musicInstrumentService;
    @Autowired
    private IInstrumentCategoryService instrumentCategoryService;

    /**
     * 查询乐器列表
     */
    @PreAuthorize("@ss.hasPermi('music:instrument:list')")
    @GetMapping("/list")
    public TableDataInfo list(MusicInstrument musicInstrument)
    {
        startPage();
        List<MusicInstrument> list = musicInstrumentService.selectMusicInstrumentList(musicInstrument);
        List<InstrumentCategory> instrumentCategories = instrumentCategoryService.selectInstrumentCategoryByIds(list.stream().map(MusicInstrument::getCategoryId).collect(Collectors.toList()));
        list.forEach(m -> m.setInstrumentCategory(instrumentCategories.stream()
                .filter(i -> i.getId().equals(m.getCategoryId()))
                .findFirst().orElse(new InstrumentCategory())));
        return getDataTable(list);
    }

    /**
     * 查询乐器列表
     */
    @PreAuthorize("@ss.hasPermi('music:instrument:customerListInstrument')")
    @GetMapping("/customerListInstrument")
    public TableDataInfo customerListInstrument(MusicInstrument musicInstrument)
    {
        startPage();
        List<MusicInstrument> list = musicInstrumentService.selectMusicInstrumentList(musicInstrument);
        List<InstrumentCategory> instrumentCategories = instrumentCategoryService.selectInstrumentCategoryByIds(list.stream().map(MusicInstrument::getCategoryId).collect(Collectors.toList()));
        list.forEach(m -> m.setInstrumentCategory(instrumentCategories.stream()
                .filter(i -> i.getId().equals(m.getCategoryId()))
                .findFirst().orElse(new InstrumentCategory())));
        return getDataTable(list);
    }

    /**
     * 导出乐器列表
     */
    @PreAuthorize("@ss.hasPermi('music:instrument:export')")
    @Log(title = "乐器", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MusicInstrument musicInstrument)
    {
        List<MusicInstrument> list = musicInstrumentService.selectMusicInstrumentList(musicInstrument);
        ExcelUtil<MusicInstrument> util = new ExcelUtil<MusicInstrument>(MusicInstrument.class);
        util.exportExcel(response, list, "乐器数据");
    }

    /**
     * 获取乐器详细信息
     */
    @PreAuthorize("@ss.hasPermi('music:instrument:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        MusicInstrument musicInstrument = musicInstrumentService.selectMusicInstrumentById(id);
        musicInstrument.setInstrumentCategory(instrumentCategoryService.selectInstrumentCategoryById(musicInstrument.getCategoryId()));
        return AjaxResult.success(musicInstrument);
    }

    /**
     * 新增乐器
     */
    @PreAuthorize("@ss.hasPermi('music:instrument:add')")
    @Log(title = "乐器", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MusicInstrument musicInstrument)
    {
        musicInstrument.setCreateBy(getUsername());
        return toAjax(musicInstrumentService.insertMusicInstrument(musicInstrument));
    }

    /**
     * 修改乐器
     */
    @PreAuthorize("@ss.hasPermi('music:instrument:edit')")
    @Log(title = "乐器", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MusicInstrument musicInstrument)
    {
        musicInstrument.setUpdateBy(getUsername());
        return toAjax(musicInstrumentService.updateMusicInstrument(musicInstrument));
    }

    /**
     * 删除乐器
     */
    @PreAuthorize("@ss.hasPermi('music:instrument:remove')")
    @Log(title = "乐器", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(musicInstrumentService.deleteMusicInstrumentByIds(ids));
    }
}
