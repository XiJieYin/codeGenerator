package com.gx.dataI.api.es.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
 * <p>图片关联映射（外部系统）</p>
 */
@ApiModel(value = "ImgOut", description = "图片关联映射（外部系统）")
@Data
@Accessors(chain = true)
@Document(indexName = "datai_img_out",type = "docs", shards = 1, replicas = 0)
public class ImgOut implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 文件名（生成的组名和路径经过base64安全加密后字符）
     */
    @ApiModelProperty(value = "文件名（生成的组名和路径经过base64安全加密后字符）",required = true)
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
    /**
     * 用户登录名
     */
    @ApiModelProperty(value = "用户登录名",required = true)
    @Field(type = FieldType.Keyword)
    private String userName;
    /**
     * 用户guid
     */
    @ApiModelProperty(value = "用户guid",required = true)
    @Field(type = FieldType.Keyword)
    @Excel(name = "用户guid", orderNum = "1",width=25)
    private String userGuid;
    /**
     * 关联状态 默认0
     */
    @ApiModelProperty(value = "关联状态 默认0",required = true)
    @Field(type = FieldType.Integer)
    private Integer status;

    public ImgOut() {
    }

    public ImgOut(String fileName, String group, String path, Date updateTime, String userName, String userGuid, Integer status) {
        this.fileName = fileName;
        this.group = group;
        this.path = path;
        this.updateTime = updateTime;
        this.userName = userName;
        this.userGuid = userGuid;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ImgOut{" +
                "fileName='" + fileName + '\'' +
                ", group='" + group + '\'' +
                ", path='" + path + '\'' +
                ", updateTime=" + updateTime +
                ", userName='" + userName + '\'' +
                ", userGuid='" + userGuid + '\'' +
                ", status=" + status +
                '}';
    }
}
