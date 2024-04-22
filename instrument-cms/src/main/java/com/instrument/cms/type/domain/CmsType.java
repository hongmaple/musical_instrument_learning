package com.instrument.cms.type.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.instrument.common.annotation.Excel;
import com.instrument.common.core.domain.BaseEntity;

/**
 * 分类管理对象 cms_type
 * 
 * @author ning
 * @date 2022-01-02
 */
public class CmsType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID */
    private Long typeId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String typeName;

    /**
     * 分类图像类型（0地址 1上传）
     */
    @Excel(name = "分类图像类型（0地址 1上传）", readConverterExp = "0=地址,1=上传")
    private String typePicType;

    /** 分类图像（ 1上传） */
    @Excel(name = "分类图像（ 1上传）")
    private String typePic;

    /** 分类图像（ 0地址） */
    @Excel(name = "分类图像（ 0地址）")
    private String typePicLink;

    /** 博客数量 */
    private int blogNum;

    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setTypePic(String typePic) 
    {
        this.typePic = typePic;
    }

    public String getTypePic() 
    {
        return typePic;
    }

    public int getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(int blogNum) {
        this.blogNum = blogNum;
    }

    public String getTypePicType() {
        return typePicType;
    }

    public void setTypePicType(String typePicType) {
        this.typePicType = typePicType;
    }

    public String getTypePicLink() {
        return typePicLink;
    }

    public void setTypePicLink(String typePicLink) {
        this.typePicLink = typePicLink;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("typeId", getTypeId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("typeName", getTypeName())
            .append("typePic", getTypePic())
                .append("typePicType", getTypePicType())
                .append("typePicLink", getTypePicLink())
                .append("blogNum", getBlogNum())
            .toString();
    }
}
