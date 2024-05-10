package com.instrument.music.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.instrument.common.annotation.Excel;
import com.instrument.common.core.domain.BaseEntity;

/**
 * 课程练习对象 music_curriculum_practice
 * 
 * @author maple
 * @date 2024-04-28
 */
public class CurriculumPractice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 类型 0：乐器，1：乐理 */
    @Excel(name = "类型 0：乐器，1：乐理")
    private String type;

    /** 课程id */
    @Excel(name = "课程id")
    private Long curriculumId;

    /** 课程详情id */
    @Excel(name = "课程详情id")
    private Long curriculumDetailsId;

    /** 练习文件 */
    @Excel(name = "练习文件")
    private String url;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setCurriculumId(Long curriculumId) 
    {
        this.curriculumId = curriculumId;
    }

    public Long getCurriculumId() 
    {
        return curriculumId;
    }
    public void setCurriculumDetailsId(Long curriculumDetailsId) 
    {
        this.curriculumDetailsId = curriculumDetailsId;
    }

    public Long getCurriculumDetailsId() 
    {
        return curriculumDetailsId;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("curriculumId", getCurriculumId())
            .append("curriculumDetailsId", getCurriculumDetailsId())
            .append("url", getUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
