package com.gx.dataI.api.es.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>图片关联映射（档案系统）</p>
 */
@ApiModel(value = "ImgDA", description = "图片关联映射（档案系统）")
@Data
@Accessors(chain = true)
@Document(indexName = "datai_img_da",type = "docs", shards = 1, replicas = 0)
public class ImgDA implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件名（外）
     */
    @ApiModelProperty(value = "文件名（外）",required = true)
    @Id
    private String fileName;
    /**
     * 组
     */
    @ApiModelProperty(value = "组",required = true)
    @Field(type = FieldType.Keyword)
    private String group;
    /**
     * FastDFS文件路径
     */
    @ApiModelProperty(value = "FastDFS文件路径",required = true)
    @Field(type = FieldType.Keyword)
    private String path;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间",required = true)
    @Field(type = FieldType.Date)
    private Date updateTime;

    public ImgDA() {
    }

    public ImgDA(String fileName, String group, String path, Date updateTime) {
        this.fileName = fileName;
        this.group = group;
        this.path = path;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ImgDA{" +
                "fileName='" + fileName + '\'' +
                ", group='" + group + '\'' +
                ", path='" + path + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
