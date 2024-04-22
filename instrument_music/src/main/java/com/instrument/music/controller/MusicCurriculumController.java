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

    /**
     * 查询课程列表
     */
    @PreAuthorize("@ss.hasPermi('music:curriculum:list')")
    @GetMapping("/list")
    public TableDataInfo list(MusicCurriculum musicCurriculum)
    {
        startPage();
        List<MusicCurriculum> list = musicCurriculumService.selectMusicCurriculumList(musicCurriculum);
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
