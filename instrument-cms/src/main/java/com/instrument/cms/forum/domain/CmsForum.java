package com.instrument.cms.forum.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.instrument.common.annotation.Excel;
import com.instrument.common.core.domain.BaseEntity;

/**
 * 论坛对象 cms_forum
 * 
 * @author maple
 * @date 2024-04-20
 */
public class CmsForum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 文本编辑器类型 */
    @Excel(name = "文本编辑器类型")
    private String contentType;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** Markdown格式内容 */
    @Excel(name = "Markdown格式内容")
    private String contentMarkdown;

    /** 置顶（0否 1是） */
    @Excel(name = "置顶", readConverterExp = "0=否,1=是")
    private String top;

    /** 阅读 */
    @Excel(name = "阅读")
    private Long views;

    /** 状态（0暂存 1发布） */
    @Excel(name = "状态", readConverterExp = "0=暂存,1=发布")
    private String status;

    /** 首页图片类型（0地址 1上传） */
    @Excel(name = "首页图片类型", readConverterExp = "0=地址,1=上传")
    private String blogPicType;

    /** 首页图片（ 1上传） */
    @Excel(name = "首页图片", readConverterExp = "1=上传")
    private String blogPic;

    /** 首页图片（ 0地址） */
    @Excel(name = "首页图片", readConverterExp = "0=地址")
    private String blogPicLink;

    /** 简介 */
    @Excel(name = "简介")
    private String blogDesc;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContentType(String contentType) 
    {
        this.contentType = contentType;
    }

    public String getContentType() 
    {
        return contentType;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setContentMarkdown(String contentMarkdown) 
    {
        this.contentMarkdown = contentMarkdown;
    }

    public String getContentMarkdown() 
    {
        return contentMarkdown;
    }
    public void setTop(String top) 
    {
        this.top = top;
    }

    public String getTop() 
    {
        return top;
    }
    public void setViews(Long views) 
    {
        this.views = views;
    }

    public Long getViews() 
    {
        return views;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setBlogPicType(String blogPicType) 
    {
        this.blogPicType = blogPicType;
    }

    public String getBlogPicType() 
    {
        return blogPicType;
    }
    public void setBlogPic(String blogPic) 
    {
        this.blogPic = blogPic;
    }

    public String getBlogPic() 
    {
        return blogPic;
    }
    public void setBlogPicLink(String blogPicLink) 
    {
        this.blogPicLink = blogPicLink;
    }

    public String getBlogPicLink() 
    {
        return blogPicLink;
    }
    public void setBlogDesc(String blogDesc) 
    {
        this.blogDesc = blogDesc;
    }

    public String getBlogDesc() 
    {
        return blogDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("title", getTitle())
            .append("contentType", getContentType())
            .append("content", getContent())
            .append("contentMarkdown", getContentMarkdown())
            .append("top", getTop())
            .append("views", getViews())
            .append("status", getStatus())
            .append("blogPicType", getBlogPicType())
            .append("blogPic", getBlogPic())
            .append("blogPicLink", getBlogPicLink())
            .append("blogDesc", getBlogDesc())
            .toString();
    }
}
