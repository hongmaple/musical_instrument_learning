package com.instrument.music.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.instrument.common.utils.SecurityUtils;
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
import com.instrument.music.domain.MusicRecognition;
import com.instrument.music.service.IMusicRecognitionService;
import com.instrument.common.utils.poi.ExcelUtil;
import com.instrument.common.core.page.TableDataInfo;

/**
 * recognitionController
 * 
 * @author maple
 * @date 2024-05-04
 */
@RestController
@RequestMapping("/music/recognition")
public class MusicRecognitionController extends BaseController
{
    @Autowired
    private IMusicRecognitionService musicRecognitionService;

    /**
     * 查询recognition列表
     */
    @PreAuthorize("@ss.hasPermi('music:recognition:list')")
    @GetMapping("/list")
    public TableDataInfo list(MusicRecognition musicRecognition)
    {
        if (!SecurityUtils.isAdmin(getUserId())){
            musicRecognition.setCreateBy(getUsername());
        }
        startPage();
        List<MusicRecognition> list = musicRecognitionService.selectMusicRecognitionList(musicRecognition);
        return getDataTable(list);
    }

    /**
     * 导出recognition列表
     */
    @PreAuthorize("@ss.hasPermi('music:recognition:export')")
    @Log(title = "recognition", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MusicRecognition musicRecognition)
    {
        List<MusicRecognition> list = musicRecognitionService.selectMusicRecognitionList(musicRecognition);
        ExcelUtil<MusicRecognition> util = new ExcelUtil<MusicRecognition>(MusicRecognition.class);
        util.exportExcel(response, list, "recognition数据");
    }

    /**
     * 获取recognition详细信息
     */
    @PreAuthorize("@ss.hasPermi('music:recognition:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(musicRecognitionService.selectMusicRecognitionById(id));
    }

    /**
     * 新增recognition
     */
    @PreAuthorize("@ss.hasPermi('music:recognition:add')")
    @Log(title = "recognition", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MusicRecognition musicRecognition)
    {
        musicRecognition.setCreateBy(getUsername());
        return toAjax(musicRecognitionService.insertMusicRecognition(musicRecognition));
    }

    /**
     * 修改recognition
     */
    @PreAuthorize("@ss.hasPermi('music:recognition:edit')")
    @Log(title = "recognition", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MusicRecognition musicRecognition)
    {
        musicRecognition.setUpdateBy(getUsername());
        return toAjax(musicRecognitionService.updateMusicRecognition(musicRecognition));
    }

    /**
     * 删除recognition
     */
    @PreAuthorize("@ss.hasPermi('music:recognition:remove')")
    @Log(title = "recognition", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(musicRecognitionService.deleteMusicRecognitionByIds(ids));
    }
}
