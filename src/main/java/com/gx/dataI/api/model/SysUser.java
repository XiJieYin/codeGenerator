package com.gx.dataI.api.model;

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
 * 
 * </p>
 *
 * @author XiJieYin
 * @since 2020-03-21
 */
@ApiModel(value = "SysUser", description = "")
@Data
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Integer id;
    /**
     * 登录用的用户名
     */
    @ApiModelProperty("登录用的用户名")
    private String loginId;
    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String userName;
    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    private String password;
    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phone;
    /**
     * 电子邮箱
     */
    @ApiModelProperty("电子邮箱")
    private String email;
    /**
     * 是否启用（0：否，1：是）
     */
    @ApiModelProperty("是否启用（0：否，1：是）")
    private String isEnable;
    /**
     * 组织机构代码
     */
    @ApiModelProperty("组织机构代码")
    private String orgCode;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 标记
     */
    @ApiModelProperty("标记")
    private String remark;
    /**
     * 性别（0：男，1：女）
     */
    @ApiModelProperty("性别（0：男，1：女）")
    private Integer xbdm;
    /**
     * 身份证号码
     */
    @ApiModelProperty("身份证号码")
    private String idCard;
    /**
     * 警号
     */
    @ApiModelProperty("警号")
    private String policeNum;
    /**
     * 最新更新时间
     */
    @ApiModelProperty("最新更新时间")
    private Date lastUpdateTime;
    /**
     * 人员编号
     */
    @ApiModelProperty("人员编号")
    private String userNum;
    /**
     * 用户拥有角色集合
     */
    @ApiModelProperty("用户拥有角色集合")
    private String roleids;
    @ApiModelProperty("")
    private String guid;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;


}
