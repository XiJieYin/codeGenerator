package com.gx.dataI.hzdospro.model;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 卷宗图片信息表
 * </p>
 *
 * @author 
 * @since 2020-03-13
 */
@ApiModel(value = "TmTmtpxxb", description = "卷宗图片信息表")
@Data
@Accessors(chain = true)
public class TmTmtpxxb implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("")
    private Long id;
    /**
     * 图片编号
     */
    @ApiModelProperty("图片编号")
    private String tpbh;
    /**
     * 图片路径
     */
    @ApiModelProperty("图片路径")
    private String imgpath;
    /**
     * 关联的是哪一个案卷或卷宗
     */
    @ApiModelProperty("关联的是哪一个案卷或卷宗")
    private String bh;
    /**
     * 顺序码
     */
    @ApiModelProperty("顺序码")
    private Integer sxm;
    /**
     * 上传时间
     */
    @ApiModelProperty("上传时间")
    private Date uploadTime;
    /**
     * 上传人
     */
    @ApiModelProperty("上传人")
    private String uploadUser;
    /**
     * 编号类型 案卷－ 0 卷宗－1
     */
    @ApiModelProperty("编号类型 案卷－ 0 卷宗－1")
    private Integer lx;
    /**
     * 状态 0 取消 ，1:正常录用
     */
    @ApiModelProperty("状态 0 取消 ，1:正常录用")
    private Integer satae;
    /**
     * 是否录入，1:已录入
     */
    @ApiModelProperty("是否录入，1:已录入")
    private Integer isLr;
    /**
     * 0：不清晰 ，1：清晰
     */
    @ApiModelProperty("0：不清晰 ，1：清晰")
    private Integer cleanSatae;
    /**
     * 文件名
     */
    @ApiModelProperty("文件名")
    private String fileName;
    /**
     * 副页页码
     */
    @ApiModelProperty("副页页码")
    private String childNum;
    @ApiModelProperty("")
    private String guid;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;


}
