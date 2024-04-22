package com.instrument.cms.message.controller;

import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;

import com.instrument.cms.message.domain.CmsMessageLike;
import com.instrument.common.core.domain.entity.SysUser;
import com.instrument.common.exception.ServiceException;
import com.instrument.common.utils.SecurityUtils;
import com.instrument.framework.web.service.SysPermissionService;
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
import com.instrument.cms.message.domain.CmsMessage;
import com.instrument.cms.message.service.ICmsMessageService;
import com.instrument.common.utils.poi.ExcelUtil;
import com.instrument.common.core.page.TableDataInfo;

/**
 * 留言管理Controller
 * 
 * @author ning
 * @date 2022-01-15
 */
@RestController
@RequestMapping("/cms/message")
public class CmsMessageController extends BaseController
{
    @Autowired
    private ICmsMessageService cmsMessageService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 首页查询留言列表
     */
    @GetMapping("/cms/list")
    public TableDataInfo cmsList(CmsMessage cmsMessage)
    {
        startPage();
        List<CmsMessage> list = cmsMessageService.selectMessageList(cmsMessage);
        return getDataTable(list);
    }

    /**
     * 首页新增留言
     */
    @PostMapping("/cms/addMessage")
    public AjaxResult addMessage(@RequestBody CmsMessage cmsMessage)
    {
        Long parentId = cmsMessage.getParentId();
        if (parentId!=null){
            CmsMessage message = cmsMessageService.selectCmsMessageById(parentId);
            if (message.getMainId()!=null){
                cmsMessage.setMainId(message.getMainId());
            }else {
                cmsMessage.setMainId(parentId);
            }
        }
        return toAjax(cmsMessageService.insertCmsMessage(cmsMessage));
    }

    /**
     * 首页新增点赞
     */
    @PostMapping("/cms/addCmsMessageLike")
    public AjaxResult addCmsMessageLike(@RequestBody CmsMessageLike cmsMessageLike)
    {
        return toAjax(cmsMessageService.addCmsMessageLike(cmsMessageLike));
    }

    /**
     * 首页删除点赞
     */
    @Log(title = "删除留言点赞", businessType = BusinessType.DELETE)
    @PostMapping("/cms/delCmsMessageLike")
    public AjaxResult delCmsMessageLike(@RequestBody CmsMessageLike cmsMessageLike)
    {
        return toAjax(cmsMessageService.delCmsMessageLike(cmsMessageLike));
    }

    /**
     * 查询留言管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:message:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmsMessage cmsMessage)
    {
        startPage();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(getLoginUser().getUser());
        if (!SecurityUtils.isAdmin(getUserId())&&!roles.contains("admin")&&!roles.contains("cms")){
            cmsMessage.setCreateBy(getUsername());
        }
        cmsMessage.setDelFlag("0");
        List<CmsMessage> list = cmsMessageService.selectCmsMessageList(cmsMessage);
        return getDataTable(list);
    }

    /**
     * 导出留言管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:message:export')")
    @Log(title = "留言管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsMessage cmsMessage)
    {
        List<CmsMessage> list = cmsMessageService.selectCmsMessageList(cmsMessage);
        ExcelUtil<CmsMessage> util = new ExcelUtil<CmsMessage>(CmsMessage.class);
        util.exportExcel(response, list, "留言管理数据");
    }

    /**
     * 获取留言管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('cms:message:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cmsMessageService.selectCmsMessageById(id));
    }

    /**
     * 新增留言管理
     */
    @PreAuthorize("@ss.hasPermi('cms:message:add')")
    @Log(title = "留言管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsMessage cmsMessage)
    {
        return toAjax(cmsMessageService.insertCmsMessage(cmsMessage));
    }

    /**
     * 修改留言管理
     */
    @PreAuthorize("@ss.hasPermi('cms:message:edit')")
    @Log(title = "留言管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsMessage cmsMessage)
    {
        return toAjax(cmsMessageService.updateCmsMessage(cmsMessage));
    }

    /**
     * 删除留言管理
     */
    @PreAuthorize("@ss.hasPermi('cms:message:remove')")
    @Log(title = "留言管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmsMessageService.deleteCmsMessageByIds(ids));
    }
}
