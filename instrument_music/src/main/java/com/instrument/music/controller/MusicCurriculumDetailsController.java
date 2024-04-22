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
import com.instrument.music.domain.MusicCurriculumDetails;
import com.instrument.music.service.IMusicCurriculumDetailsService;
import com.instrument.common.utils.poi.ExcelUtil;
import com.instrument.common.core.page.TableDataInfo;

/**
 * 课程详情Controller
 * 
 * @author maple
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/music/curriculumDetails")
public class MusicCurriculumDetailsController extends BaseController
{
    @Autowired
    private IMusicCurriculumDetailsService musicCurriculumDetailsService;

    /**
     * 查询课程详情列表
     */
    @PreAuthorize("@ss.hasPermi('music:curriculumDetails:list')")
    @GetMapping("/list")
    public TableDataInfo list(MusicCurriculumDetails musicCurriculumDetails)
    {
        startPage();
        List<MusicCurriculumDetails> list = musicCurriculumDetailsService.selectMusicCurriculumDetailsList(musicCurriculumDetails);
        return getDataTable(list);
    }

    /**
     * 导出课程详情列表
     */
    @PreAuthorize("@ss.hasPermi('music:curriculumDetails:export')")
    @Log(title = "课程详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MusicCurriculumDetails musicCurriculumDetails)
    {
        List<MusicCurriculumDetails> list = musicCurriculumDetailsService.selectMusicCurriculumDetailsList(musicCurriculumDetails);
        ExcelUtil<MusicCurriculumDetails> util = new ExcelUtil<MusicCurriculumDetails>(MusicCurriculumDetails.class);
        util.exportExcel(response, list, "课程详情数据");
    }

    /**
     * 获取课程详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('music:curriculumDetails:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(musicCurriculumDetailsService.selectMusicCurriculumDetailsById(id));
    }

    /**
     * 新增课程详情
     */
    @PreAuthorize("@ss.hasPermi('music:curriculumDetails:add')")
    @Log(title = "课程详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MusicCurriculumDetails musicCurriculumDetails)
    {
        return toAjax(musicCurriculumDetailsService.insertMusicCurriculumDetails(musicCurriculumDetails));
    }

    /**
     * 修改课程详情
     */
    @PreAuthorize("@ss.hasPermi('music:curriculumDetails:edit')")
    @Log(title = "课程详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MusicCurriculumDetails musicCurriculumDetails)
    {
        return toAjax(musicCurriculumDetailsService.updateMusicCurriculumDetails(musicCurriculumDetails));
    }

    /**
     * 删除课程详情
     */
    @PreAuthorize("@ss.hasPermi('music:curriculumDetails:remove')")
    @Log(title = "课程详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(musicCurriculumDetailsService.deleteMusicCurriculumDetailsByIds(ids));
    }
}
