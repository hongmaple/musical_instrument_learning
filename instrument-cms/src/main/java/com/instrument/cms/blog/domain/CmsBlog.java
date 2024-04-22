package com.instrument.cms.blog.domain;

import com.instrument.cms.tag.domain.CmsTag;
import com.instrument.cms.type.domain.CmsType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.instrument.common.annotation.Excel;
import com.instrument.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 文章管理对象 cms_blog
 *
 * @author ning
 * @date 2022-01-01
 */
public class CmsBlog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;

    /**
     * 类型 1文章 2随笔
     */
    @Excel(name = "类型")
    private String type;

    /**
     * 内容
     */
    @Excel(name = "内容")
    private String content;

    /**
     * 文本编辑器类型 1 Quill富文本编辑器 2 CherryMarkdown
     */
    @Excel(name = "文本编辑器类型")
    private String contentType;

    /**
     * Markdown格式内容
     */
    @Excel(name = "Markdown格式内容")
    private String contentMarkdown;

    /**
     * 置顶（0否 1是）
     */
    @Excel(name = "置顶", readConverterExp = "0=否,1=是")
    private String top;

    /**
     * 阅读
     */
    @Excel(name = "阅读")
    private Long views;

    /**
     * 状态（0暂存 1发布）
     */
    @Excel(name = "状态", readConverterExp = "0=暂存,1=发布")
    private String status;

    /**
     * 首页图片类型（0地址 1上传）
     */
    @Excel(name = "首页图片类型", readConverterExp = "0=地址,1=上传")
    private String blogPicType;

    /** 首页图片（ 1上传） */
    @Excel(name = "首页图片（ 1上传）")
    private String blogPic;

    /** 首页图片（ 0地址） */
    @Excel(name = "首页图片（ 0地址）")
    private String blogPicLink;

    /** 简介 */
    @Excel(name = "简介")
    private String blogDesc;

    /** 附件列表 */
    private String blogFiles;

    /**
     * 分类
     */
    private Long[] typeIds;

    /**
     * 分类
     */
    private Long[] tagIds;

    /** 角色对象 */
    private List<CmsTag> tags;

    /** 角色对象 */
    private List<CmsType> types;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getTop() {
        return top;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getViews() {
        return views;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Long[] getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(Long[] typeIds) {
        this.typeIds = typeIds;
    }

    public Long[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(Long[] tagIds) {
        this.tagIds = tagIds;
    }

    public List<CmsTag> getTags() {
        return tags;
    }

    public void setTags(List<CmsTag> tags) {
        this.tags = tags;
    }

    public List<CmsType> getTypes() {
        return types;
    }

    public void setTypes(List<CmsType> types) {
        this.types = types;
    }

    public String getBlogPic() {
        return blogPic;
    }

    public void setBlogPic(String blogPic) {
        this.blogPic = blogPic;
    }

    public String getBlogDesc() {
        return blogDesc;
    }

    public void setBlogDesc(String blogDesc) {
        this.blogDesc = blogDesc;
    }

    public String getBlogFiles() {
        return blogFiles;
    }

    public void setBlogFiles(String blogFiles) {
        this.blogFiles = blogFiles;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentMarkdown() {
        return contentMarkdown;
    }

    public void setContentMarkdown(String contentMarkdown) {
        this.contentMarkdown = contentMarkdown;
    }

    public String getBlogPicType() {
        return blogPicType;
    }

    public void setBlogPicType(String blogPicType) {
        this.blogPicType = blogPicType;
    }

    public String getBlogPicLink() {
        return blogPicLink;
    }

    public void setBlogPicLink(String blogPicLink) {
        this.blogPicLink = blogPicLink;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("title", getTitle())
                .append("type", getType())
                .append("content", getContent())
                .append("contentType", getContentType())
                .append("contentMarkdown", getContentMarkdown())
                .append("top", getTop())
                .append("views", getViews())
                .append("status", getStatus())
                .append("typeIds", getTypeIds())
                .append("tagIds", getTagIds())
                .append("tags", getTags())
                .append("types", getTypes())
                .append("blogPicType", getBlogPicType())
                .append("blogPic", getBlogPic())
                .append("blogPicLink", getBlogPicLink())
                .append("blogDesc", getBlogDesc())
                .append("blogFiles", getBlogFiles())
                .toString();
    }
}
