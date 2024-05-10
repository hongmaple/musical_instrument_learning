package com.instrument.music.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.instrument.common.utils.SecurityUtils;
import com.instrument.music.domain.MusicInstrument;
import com.instrument.music.service.IMusicInstrumentService;
import org.apache.commons.collections4.CollectionUtils;
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
import com.instrument.music.domain.MusicCurriculum;
import com.instrument.music.service.IMusicCurriculumService;
import com.instrument.common.utils.poi.ExcelUtil;
import com.instrument.common.core.page.TableDataInfo;

/**
 * 课程Controller
 * 
 * @author maple
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/music/curriculum")
public class MusicCurriculumController extends BaseController
{
    @Autowired
    private IMusicCurriculumService musicCurriculumService;

    @Autowired
    private IMusicInstrumentService musicInstrumentService;

    /**
     * 查询课程列表
     */
    @PreAuthorize("@ss.hasPermi('music:curriculum:list')")
    @GetMapping("/list")
    public TableDataInfo list(MusicCurriculum musicCurriculum)
    {
        startPage();
        List<MusicCurriculum> list = musicCurriculumService.selectMusicCurriculumList(musicCurriculum);
        List<Long> iIds = list.stream().map(MusicCurriculum::getInstrumentId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(iIds)) return getDataTable(list);
        List<MusicInstrument> musicInstruments = musicInstrumentService.selectMusicInstrumentByIds(iIds);
        list.forEach(musicCurriculum1 -> musicInstruments.stream().filter(m -> m.getId().equals(musicCurriculum1.getInstrumentId())).findAny().ifPresent(musicCurriculum1::setMusicInstrument));
        return getDataTable(list);
    }

    /**
     * 查询课程列表
     */
    @PreAuthorize("@ss.hasPermi('music:curriculum:customerListCurriculum')")
    @GetMapping("/customerListCurriculum")
    public TableDataInfo customerListCurriculum(MusicCurriculum musicCurriculum)
    {
        musicCurriculum.setStatus("1");
        startPage();
        List<MusicCurriculum> list = musicCurriculumService.selectMusicCurriculumList(musicCurriculum);
        List<Long> iIds = list.stream().map(MusicCurriculum::getInstrumentId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(iIds)) return getDataTable(list);
        List<MusicInstrument> musicInstruments = musicInstrumentService.selectMusicInstrumentByIds(iIds);
        list.forEach(musicCurriculum1 -> musicInstruments.stream().filter(m -> m.getId().equals(musicCurriculum1.getInstrumentId())).findAny().ifPresent(musicCurriculum1::setMusicInstrument));
        return getDataTable(list);
    }

    /**
     * 导出课程列表
     */
    @PreAuthorize("@ss.hasPermi('music:curriculum:export')")
    @Log(title = "课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MusicCurriculum musicCurriculum)
    {
        List<MusicCurriculum> list = musicCurriculumService.selectMusicCurriculumList(musicCurriculum);
        ExcelUtil<MusicCurriculum> util = new ExcelUtil<MusicCurriculum>(MusicCurriculum.class);
        util.exportExcel(response, list, "课程数据");
    }

    /**
     * 获取课程详细信息
     */
    @PreAuthorize("@ss.hasPermi('music:curriculum:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(musicCurriculumService.selectMusicCurriculumById(id));
    }

    /**
     * 新增课程
     */
    @PreAuthorize("@ss.hasPermi('music:curriculum:add')")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MusicCurriculum musicCurriculum)
    {
        musicCurriculum.setCreateBy(getUsername());
        return toAjax(musicCurriculumService.insertMusicCurriculum(musicCurriculum));
    }

    /**
     * 修改课程
     */
    @PreAuthorize("@ss.hasPermi('music:curriculum:edit')")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MusicCurriculum musicCurriculum)
    {
        musicCurriculum.setUpdateBy(getUsername());
        return toAjax(musicCurriculumService.updateMusicCurriculum(musicCurriculum));
    }

    /**
     * 删除课程
     */
    @PreAuthorize("@ss.hasPermi('music:curriculum:remove')")
    @Log(title = "课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(musicCurriculumService.deleteMusicCurriculumByIds(ids));
    }
}
