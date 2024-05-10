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
import com.instrument.music.domain.MusicMyCurriculum;
import com.instrument.music.service.IMusicMyCurriculumService;
import com.instrument.common.utils.poi.ExcelUtil;
import com.instrument.common.core.page.TableDataInfo;

/**
 * 我的课程Controller
 * 
 * @author maple
 * @date 2024-04-28
 */
@RestController
@RequestMapping("/music/MyCurriculum")
public class MusicMyCurriculumController extends BaseController
{
    @Autowired
    private IMusicMyCurriculumService musicMyCurriculumService;

    /**
     * 查询我的课程列表
     */
    @PreAuthorize("@ss.hasPermi('music:myCurriculum:list')")
    @GetMapping("/list")
    public TableDataInfo list(MusicMyCurriculum musicMyCurriculum)
    {
        startPage();
        musicMyCurriculum.setCreateBy(getUsername());
        List<MusicMyCurriculum> list = musicMyCurriculumService.selectMusicMyCurriculumList(musicMyCurriculum);
        return getDataTable(list);
    }

    /**
     * 导出我的课程列表
     */
    @PreAuthorize("@ss.hasPermi('music:myCurriculum:export')")
    @Log(title = "我的课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MusicMyCurriculum musicMyCurriculum)
    {
        List<MusicMyCurriculum> list = musicMyCurriculumService.selectMusicMyCurriculumList(musicMyCurriculum);
        ExcelUtil<MusicMyCurriculum> util = new ExcelUtil<MusicMyCurriculum>(MusicMyCurriculum.class);
        util.exportExcel(response, list, "我的课程数据");
    }

    /**
     * 获取我的课程详细信息
     */
    @PreAuthorize("@ss.hasPermi('music:myCurriculum:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(musicMyCurriculumService.selectMusicMyCurriculumById(id));
    }

    /**
     * 新增我的课程
     */
    @PreAuthorize("@ss.hasPermi('music:myCurriculum:add')")
    @Log(title = "我的课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MusicMyCurriculum musicMyCurriculum)
    {
        musicMyCurriculum.setCreateBy(getUsername());
        return toAjax(musicMyCurriculumService.insertMusicMyCurriculum(musicMyCurriculum));
    }

    /**
     * 修改我的课程
     */
    @PreAuthorize("@ss.hasPermi('music:myCurriculum:edit')")
    @Log(title = "我的课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MusicMyCurriculum musicMyCurriculum)
    {
        musicMyCurriculum.setUpdateBy(getUsername());
        return toAjax(musicMyCurriculumService.updateMusicMyCurriculum(musicMyCurriculum));
    }

    /**
     * 删除我的课程
     */
    @PreAuthorize("@ss.hasPermi('music:myCurriculum:remove')")
    @Log(title = "我的课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(musicMyCurriculumService.deleteMusicMyCurriculumByIds(ids));
    }
}
