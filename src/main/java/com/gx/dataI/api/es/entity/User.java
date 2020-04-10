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

/**
 * <p>
 * 用户，用于登录拿token
 * </p>
 *
 * @author xijie.yin
 * @since 2020-03-13
 */
@ApiModel(value = "User", description = "用户表")
@Data
@Accessors(chain = true)
@Document(indexName = "datai_user",type = "docs", shards = 1, replicas = 0)
public class User  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户GUID
     */
    @ApiModelProperty(value = "用户GUID",required = true)
    @Id
    private String guid;
    /**
     * 用户登录名
     */
    @ApiModelProperty(value = "用户登录名",required = true)
    @Field(type = FieldType.Keyword)
    private String userName;
    /**
     * 用户登录密码
     */
    @ApiModelProperty(value = "用户登录密码",required = true)
    @Field(type = FieldType.Keyword)
    private String password;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名",required = true)
    @Field(type = FieldType.Text)
    private String realName;
    /**
     * 公司
     */
    @ApiModelProperty("公司")
    @Field(type = FieldType.Text)
    private String company;
    /**
     * 电话
     */
    @ApiModelProperty("电话")
    @Field(type = FieldType.Text)
    private String phone;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @Field(type = FieldType.Text)
    private String email;
    /**
     * 最大上传大小 B
     */
    @ApiModelProperty(value = "最大上传大小 B",required = true)
    @Field(type = FieldType.Long)
    private Long maxUploadSize;
    /**
     * 总上传大小（限制）B
     */
    @ApiModelProperty(value = "总上传大小（限制）B",required = true)
    @Field(type = FieldType.Long)
    private Long totalUploadSize;
    /**
     * 当前已经上传大小
     */
    @ApiModelProperty(value = "当前已经上传大小 B",required = true)
    @Field(type = FieldType.Long)
    private Long currentUploadSize;
    /**
     * 状态（0、禁用 1、正常）
     */
    @ApiModelProperty(value = "状态（0、禁用 1、正常）",required = true)
    @Field(type = FieldType.Integer)
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @Field(type = FieldType.Text)
    private String remarks;

    /**
     * API 用户
     * @param guid              用户GUID
     * @param userName          用户登录名
     * @param password          用户登录密码
     * @param realName          真实姓名
     * @param company           公司
     * @param phone             电话
     * @param email             邮箱
     * @param maxUploadSize     最大上传大小
     * @param totalUploadSize   总上传大小（限制）
     * @param currentUploadSize 当前已经上传大小
     * @param status            状态（0、禁用 1、正常）
     * @param remarks           备注
     */
    public User(String guid, String userName, String password, String realName, String company, String phone, String email, Long maxUploadSize, Long totalUploadSize, Long currentUploadSize, Integer status, String remarks) {
        this.guid = guid;
        this.userName = userName;
        this.password = password;
        this.realName = realName;
        this.company = company;
        this.phone = phone;
        this.email = email;
        this.maxUploadSize = maxUploadSize;
        this.totalUploadSize = totalUploadSize;
        this.currentUploadSize = currentUploadSize;
        this.status = status;
        this.remarks = remarks;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "guid='" + guid + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", company='" + company + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", maxUploadSize=" + maxUploadSize +
                ", totalUploadSize=" + totalUploadSize +
                ", currentUploadSize=" + currentUploadSize +
                ", status=" + status +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
