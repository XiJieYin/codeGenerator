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
 * 任务基本信息
 * </p>
 *
 * @author 
 * @since 2019-12-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("INFO_RWJBXX")
@ApiModel(value = "InfoRwjbxx", description = "任务基本信息")
public class InfoRwjbxx implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID",type = IdType.INPUT)
    private Long id;
    /**
     * 任务编号
     */
    private String rwbh;
    /**
     * 名称
     */
    private String mc;
    /**
     * 卷宗数量
     */
    private Long jzsl;
    /**
     * 计划开始时间
     */
    private Date jhkssj;
    /**
     * 计划结束时间
     */
    private Date jhjssj;
    /**
     * 状态(0、删除 1、未开始 2、进行中  3、已完成)
     */
    private Integer zt;
    /**
     * 完成数量
     */
    private Long wcsl;
    /**
     * 创建人
     */
    private String cjrmc;
    /**
     * 创建时间
     */
    private Date cjsj;
    /**
     * 备注
     */
    private String bz;
    /**
     * 所属单位名称
     */
    private String ssdwmc;
    /**
     * 所属单位代码
     */
    private String ssdwdm;
    /**
     * 组织机构代码
     */
    private String orgCode;
    /**
     * 负责人
     */
    private String fzrmc;
    /**
     * 创建人ID
     */
    private Long cjrId;
    /**
     * 负责人Id
     */
    private Long fzrId;
    /**
     * 完成时间
     */
    private Date wcsj;
    /**
     * 材料数量
     */
    private Long clsl;
    /**
     * 总页数
     */
    private Long zys;
    /**
     * 原文数量
     */
    private Long ywsl;
    /**
     * 规则Id
     */
    private Long ruleId;
    /**
     * 是否自动校验 0:否 ，1:是
     */
    private Integer ischeck;
    /**
     * 负责人角色id
     */
    private Long fzrRoleId;
    private String guid;
    /**
     * 更新时间
     */
    private Date updateTime;


}
