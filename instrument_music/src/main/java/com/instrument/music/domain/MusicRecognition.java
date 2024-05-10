package com.instrument.music.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.instrument.common.annotation.Excel;
import com.instrument.common.core.domain.BaseEntity;

/**
 * recognition对象 music_recognition
 * 
 * @author maple
 * @date 2024-05-04
 */
public class MusicRecognition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 乐器id */
    private Long id;

    /** 乐器id */
    @Excel(name = "乐器id")
    private Long instrumentId;

    /** 识别结果 */
    @Excel(name = "识别结果")
    private String result;

    /** 音频地址 */
    @Excel(name = "音频地址")
    private String url;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setInstrumentId(Long instrumentId) 
    {
        this.instrumentId = instrumentId;
    }

    public Long getInstrumentId() 
    {
        return instrumentId;
    }
    public void setResult(String result) 
    {
        this.result = result;
    }

    public String getResult() 
    {
        return result;
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
            .append("instrumentId", getInstrumentId())
            .append("result", getResult())
            .append("url", getUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
