package com.instrument.music.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.instrument.common.annotation.Excel;
import com.instrument.common.core.domain.BaseEntity;

/**
 * 乐器对象 music_instrument
 *
 * @author maple
 * @date 2024-05-14
 */
public class MusicInstrument extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 乐器名 */
    @Excel(name = "乐器名")
    private String name;

    /** 介绍内容 */
    @Excel(name = "介绍内容")
    private String content;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String url;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long categoryId;

    private InstrumentCategory instrumentCategory;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }

    public InstrumentCategory getInstrumentCategory() {
        return instrumentCategory;
    }

    public void setInstrumentCategory(InstrumentCategory instrumentCategory) {
        this.instrumentCategory = instrumentCategory;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("name", getName())
                .append("content", getContent())
                .append("url", getUrl())
                .append("categoryId", getCategoryId())
                .toString();
    }
}
