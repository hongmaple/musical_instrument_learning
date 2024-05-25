package com.instrument.music.comment.controller;

import com.instrument.common.annotation.Log;
import com.instrument.common.core.controller.BaseController;
import com.instrument.common.core.domain.AjaxResult;
import com.instrument.common.core.page.TableDataInfo;
import com.instrument.common.enums.BusinessType;
import com.instrument.common.utils.SecurityUtils;
import com.instrument.common.utils.poi.ExcelUtil;
import com.instrument.framework.web.service.SysPermissionService;
import com.instrument.music.comment.domain.CurriculumComment;
import com.instrument.music.comment.domain.CurriculumCommentLike;
import com.instrument.music.comment.service.ICurriculumCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * 评论管理Controller
 * 
 * @author ning
 * @date 2022-01-21
 */
@RestController
@RequestMapping("/curriculum/comment")
public class CurriculumCommentController extends BaseController
{
    @Autowired
    private ICurriculumCommentService curriculumCommentService;

    @Autowired
    private SysPermissionService permissionService;


    /**
     * 首页查询评论列表
     */
    @GetMapping("/curriculum/list")
    public TableDataInfo curriculumList(CurriculumComment curriculumComment)
    {
        startPage();
        List<CurriculumComment> list = curriculumCommentService.selectCommentList(curriculumComment);
        return getDataTable(list);
    }

    /**
     * 首页新增评论
     */
    @PostMapping("/curriculum/addComment")
    public AjaxResult addComment(@RequestBody CurriculumComment curriculumComment)
    {
        Long parentId = curriculumComment.getParentId();
        if (parentId!=null){
            CurriculumComment comment = curriculumCommentService.selectCurriculumCommentById(parentId);
            if (comment.getMainId()!=null){
                curriculumComment.setMainId(comment.getMainId());
            }else {
                curriculumComment.setMainId(parentId);
            }
        }
        return toAjax(curriculumCommentService.insertCurriculumComment(curriculumComment));
    }

    /**
     * 首页新增点赞
     */
    @PostMapping("/curriculum/addcurriculumCommentLike")
    public AjaxResult addcurriculumCommentLike(@RequestBody CurriculumCommentLike curriculumCommentLike)
    {
        return toAjax(curriculumCommentService.addCurriculumCommentLike(curriculumCommentLike));
    }

    /**
     * 首页删除点赞
     */
    @Log(title = "删除评论点赞", businessType = BusinessType.DELETE)
    @PostMapping("/curriculum/delcurriculumCommentLike")
    public AjaxResult delcurriculumCommentLike(@RequestBody CurriculumCommentLike curriculumCommentLike)
    {
        return toAjax(curriculumCommentService.delCurriculumCommentLike(curriculumCommentLike));
    }

    /**
     * 查询评论管理列表
     */
    @PreAuthorize("@ss.hasPermi('curriculum:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(CurriculumComment curriculumComment)
    {
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(getLoginUser().getUser());
        if (!SecurityUtils.isAdmin(getUserId())&&!roles.contains("admin")&&!roles.contains("curriculum")){
            curriculumComment.setCreateBy(getUsername());
        }
        curriculumComment.setDelFlag("0");
        startPage();
        List<CurriculumComment> list = curriculumCommentService.selectCommentList(curriculumComment);
        return getDataTable(list);
    }

    /**
     * 导出评论管理列表
     */
    @PreAuthorize("@ss.hasPermi('curriculum:comment:export')")
    @Log(title = "评论管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CurriculumComment curriculumComment)
    {
        List<CurriculumComment> list = curriculumCommentService.selectCurriculumCommentList(curriculumComment);
        ExcelUtil<CurriculumComment> util = new ExcelUtil<CurriculumComment>(CurriculumComment.class);
        util.exportExcel(response, list, "评论管理数据");
    }

    /**
     * 获取评论管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('curriculum:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(curriculumCommentService.selectCurriculumCommentById(id));
    }

    /**
     * 新增评论管理
     */
    @PreAuthorize("@ss.hasPermi('curriculum:comment:add')")
    @Log(title = "评论管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CurriculumComment curriculumComment)
    {
        return toAjax(curriculumCommentService.insertCurriculumComment(curriculumComment));
    }

    /**
     * 修改评论管理
     */
    @PreAuthorize("@ss.hasPermi('curriculum:comment:edit')")
    @Log(title = "评论管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CurriculumComment curriculumComment)
    {
        return toAjax(curriculumCommentService.updateCurriculumComment(curriculumComment));
    }

    /**
     * 删除评论管理
     */
    @PreAuthorize("@ss.hasPermi('curriculum:comment:remove')")
    @Log(title = "评论管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(curriculumCommentService.deleteCurriculumCommentByIds(ids));
    }
}
