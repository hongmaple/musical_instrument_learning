package com.instrument.cms.forum.comment.controller;

import com.instrument.cms.forum.comment.domain.ForumComment;
import com.instrument.cms.forum.comment.domain.ForumCommentLike;
import com.instrument.cms.forum.comment.service.IForumCommentService;
import com.instrument.common.annotation.Log;
import com.instrument.common.core.controller.BaseController;
import com.instrument.common.core.domain.AjaxResult;
import com.instrument.common.core.page.TableDataInfo;
import com.instrument.common.enums.BusinessType;
import com.instrument.common.utils.SecurityUtils;
import com.instrument.common.utils.poi.ExcelUtil;
import com.instrument.framework.web.service.SysPermissionService;
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
@RequestMapping("/forum/comment")
public class ForumCommentController extends BaseController
{
    @Autowired
    private IForumCommentService forumCommentService;

    @Autowired
    private SysPermissionService permissionService;


    /**
     * 首页查询评论列表
     */
    @GetMapping("/forum/list")
    public TableDataInfo forumList(ForumComment forumComment)
    {
        startPage();
        List<ForumComment> list = forumCommentService.selectCommentList(forumComment);
        return getDataTable(list);
    }

    /**
     * 首页新增评论
     */
    @PostMapping("/forum/addComment")
    public AjaxResult addComment(@RequestBody ForumComment forumComment)
    {
        Long parentId = forumComment.getParentId();
        if (parentId!=null){
            ForumComment comment = forumCommentService.selectForumCommentById(parentId);
            if (comment.getMainId()!=null){
                forumComment.setMainId(comment.getMainId());
            }else {
                forumComment.setMainId(parentId);
            }
        }
        return toAjax(forumCommentService.insertForumComment(forumComment));
    }

    /**
     * 首页新增点赞
     */
    @PostMapping("/forum/addForumCommentLike")
    public AjaxResult addForumCommentLike(@RequestBody ForumCommentLike forumCommentLike)
    {
        return toAjax(forumCommentService.addForumCommentLike(forumCommentLike));
    }

    /**
     * 首页删除点赞
     */
    @Log(title = "删除评论点赞", businessType = BusinessType.DELETE)
    @PostMapping("/forum/delForumCommentLike")
    public AjaxResult delForumCommentLike(@RequestBody ForumCommentLike ForumCommentLike)
    {
        return toAjax(forumCommentService.delForumCommentLike(ForumCommentLike));
    }

    /**
     * 查询评论管理列表
     */
    @PreAuthorize("@ss.hasPermi('forum:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(ForumComment ForumComment)
    {
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(getLoginUser().getUser());
        if (!SecurityUtils.isAdmin(getUserId())&&!roles.contains("admin")&&!roles.contains("cms")){
            ForumComment.setCreateBy(getUsername());
        }
        ForumComment.setDelFlag("0");
        startPage();
        List<ForumComment> list = forumCommentService.selectForumCommentList(ForumComment);
        return getDataTable(list);
    }

    /**
     * 导出评论管理列表
     */
    @PreAuthorize("@ss.hasPermi('forum:comment:export')")
    @Log(title = "评论管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ForumComment ForumComment)
    {
        List<ForumComment> list = forumCommentService.selectForumCommentList(ForumComment);
        ExcelUtil<ForumComment> util = new ExcelUtil<ForumComment>(ForumComment.class);
        util.exportExcel(response, list, "评论管理数据");
    }

    /**
     * 获取评论管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('forum:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(forumCommentService.selectForumCommentById(id));
    }

    /**
     * 新增评论管理
     */
    @PreAuthorize("@ss.hasPermi('forum:comment:add')")
    @Log(title = "评论管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ForumComment ForumComment)
    {
        return toAjax(forumCommentService.insertForumComment(ForumComment));
    }

    /**
     * 修改评论管理
     */
    @PreAuthorize("@ss.hasPermi('forum:comment:edit')")
    @Log(title = "评论管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ForumComment ForumComment)
    {
        return toAjax(forumCommentService.updateForumComment(ForumComment));
    }

    /**
     * 删除评论管理
     */
    @PreAuthorize("@ss.hasPermi('forum:comment:remove')")
    @Log(title = "评论管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(forumCommentService.deleteForumCommentByIds(ids));
    }
}
