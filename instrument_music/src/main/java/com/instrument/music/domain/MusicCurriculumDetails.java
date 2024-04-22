package com.instrument.music.domain;


import com.instrument.common.annotation.Excel;
import com.instrument.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 课程详情对象 music_curriculum_details
 * 
 * @author maple
 * @date 2024-04-20
 */
public class MusicCurriculumDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID")
    private Long id;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long cId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 介绍内容 */
    @Excel(name = "介绍内容")
    private String content;

    /** 状态（0暂存 1发布） */
    @Excel(name = "状态", readConverterExp = "0=暂存,1=发布")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setcId(Long cId) 
    {
        this.cId = cId;
    }

    public Long getcId() 
    {
        return cId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("cId", getcId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("status", getStatus())
            .toString();
    }
}
