package com.gx.dataI.hzdospro.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 主表－原文信息表
 * </p>
 *
 * @author 
 * @since 2019-12-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("ORI_INFO_PRIMARY")
@ApiModel(value = "OriInfoPrimary", description = "原文")
public class OriInfoPrimary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.INPUT)
    private Long id;
    /**
     * 录入人员Id
     */
    private Long userId;
    /**
     * 录入人员名称
     */
    private String lrrymc;
    /**
     * 录入时间
     */
    private Date lrsj;
    /**
     * 状态 0、删除  1、新录入 2、待审核3、审核未通过4、审核通过
     */
    private Integer satae;
    /**
     * 原文图片编号
     */
    private String ywtpbh;
    /**
     * 类型
     */
    private String ywlx;
    /**
     * 录入状态 （-1：待录入 0 ：否，1:是）
     */
    private Integer needlr;
    /**
     * 审核问题
     */
    private String shwt;
    private String jzbh;
    /**
     * 条目编号
     */
    private String tmbh;
    /**
     * 是否一致 0：否 1：是
     */
    private Integer consistency;
    private Integer materialId;
    private String guid;
    /**
     * 更新时间
     */
    private Date updateTime;


}
