package com.gx.dataI.api.es.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
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
 * <p>日志管理</p>
 */
@ApiModel(value = "Log", description = "日志管理")
@Data
@Accessors(chain = true)
@Document(indexName = "datai_log",type = "docs", shards = 1, replicas = 0)
@ExcelTarget("Log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * GUID
     */
    @ApiModelProperty(value = "GUID",required = true)
    @Id
    private String guid;
    /**
     * 用户guid
     */
    @ApiModelProperty(value = "用户guid",required = true)
    @Field(type = FieldType.Keyword)
    @Excel(name = "用户guid", orderNum = "1",width=25)
    private String userGuid;
    /**
     * 用户登录名
     */
    @ApiModelProperty(value = "用户登录名",required = true)
    @Field(type = FieldType.Keyword)
    @Excel(name = "用户登录名", orderNum = "1",width=25)
    private String userName;
    /**
     *调用的IP
     */
    @ApiModelProperty(value = "调用的IP",required = true)
    @Field(type = FieldType.Keyword)
    @Excel(name = "调用的IP", orderNum = "2",width=25)
    private String ip;
    /**
     *操作类型
     */
    @ApiModelProperty(value = "操作类型",required = true)
    @Field(type = FieldType.Integer)
    @Excel(name = "操作类型", orderNum = "3",width=25)
    private Integer operation;
    /**
     *调用方法所有的毫秒
     */
    @ApiModelProperty(value = "调用方法所有的毫秒",required = true)
    @Field(type = FieldType.Long)
    @Excel(name = "调用方法所有的毫秒", orderNum = "4",width=25)
    private Long time;
    /**
     *调用的方法
     */
    @ApiModelProperty(value = "调用的方法",required = true)
    @Field(type = FieldType.Keyword)
    @Excel(name = "调用的方法", orderNum = "5",width=25)
    private String method;
    /**
     *传递的参数
     */
    @ApiModelProperty(value = "传递的参数",required = true)
    @Field(type = FieldType.Keyword)
    @Excel(name = "传递的参数", orderNum = "6",width=25)
    private String params;
    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间",required = true)
    @Field(type = FieldType.Date)
    @Excel(name = "操作时间", orderNum = "7",width=25)
    private Date operateTime;
    /**
     * 操作状态，成功与否
     */
    @ApiModelProperty(value = "操作状态，成功与否",required = true)
    @Field(type = FieldType.Integer)
    @Excel(name = "操作状态，成功与否", orderNum = "8",width=25)
    private Integer status;
    /**
     * 描述信息
     */
    @ApiModelProperty(value = "描述信息",required = true)
    @Field(type = FieldType.Text)
    @Excel(name = "描述信息", orderNum = "9",width=25)
    private String msg;

    public Log() {
    }

    public Log(String guid, String userGuid, String userName, String ip, Integer operation, Long time, String method, String params, Date operateTime, Integer status, String msg) {
        this.guid = guid;
        this.userGuid = userGuid;
        this.userName = userName;
        this.ip = ip;
        this.operation = operation;
        this.time = time;
        this.method = method;
        this.params = params;
        this.operateTime = operateTime;
        this.status = status;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Log{" +
                "guid='" + guid + '\'' +
                ", userGuid='" + userGuid + '\'' +
                ", userName='" + userName + '\'' +
                ", ip='" + ip + '\'' +
                ", operation=" + operation +
                ", time=" + time +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", operateTime=" + operateTime +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
