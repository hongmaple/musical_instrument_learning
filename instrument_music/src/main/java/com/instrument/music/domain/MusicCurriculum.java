package com.instrument.music.domain;


import com.instrument.common.annotation.Excel;
import com.instrument.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 课程对象 music_curriculum
 * 
 * @author maple
 * @date 2024-04-20
 */
public class MusicCurriculum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 类型 0：乐器，1：乐理 */
    @Excel(name = "类型 0：乐器，1：乐理")
    private String type;

    /** 介绍内容 */
    @Excel(name = "介绍内容")
    private String content;

    /** 置顶（0否 1是） */
    @Excel(name = "置顶", readConverterExp = "0=否,1=是")
    private String top;

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

    /** 乐器id */
    @Excel(name = "乐器id")
    private Long instrumentId;

    private MusicInstrument musicInstrument;

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
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setTop(String top) 
    {
        this.top = top;
    }

    public String getTop() 
    {
        return top;
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
    public void setInstrumentId(Long instrumentId) 
    {
        this.instrumentId = instrumentId;
    }

    public Long getInstrumentId() 
    {
        return instrumentId;
    }

    public MusicInstrument getMusicInstrument() {
        return musicInstrument;
    }

    public void setMusicInstrument(MusicInstrument musicInstrument) {
        this.musicInstrument = musicInstrument;
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
            .append("top", getTop())
            .append("status", getStatus())
            .append("blogPicType", getBlogPicType())
            .append("blogPic", getBlogPic())
            .append("blogPicLink", getBlogPicLink())
            .append("instrumentId", getInstrumentId())
            .toString();
    }
}
