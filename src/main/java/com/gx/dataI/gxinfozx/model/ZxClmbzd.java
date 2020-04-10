package com.gx.dataI.gxinfozx.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 材料模板字典表
 * </p>
 *
 * @author 
 * @since 2019-12-16
 */

@Data
@Accessors(chain = true)
@TableName("ZX_CLMBZD")
@ApiModel(value = "ZxClmbzd", description = "材料模板字典表")
public class ZxClmbzd implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * GUID
     */
    @TableId(value = "GUID",type = IdType.UUID)
    @ApiModelProperty("GUID")
    private String guid;
    /**
     * 材料名称
     */
    @ApiModelProperty("材料名称")
    private String clmc;
    /**
     * 材料编码
     */
    @ApiModelProperty("材料编码")
    private String clbm;
    /**
     * 材料类型
     */
    @ApiModelProperty("材料类型")
    private String cllx;
    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String clms;
    /**
     * 版本
     */
    @ApiModelProperty("版本")
    private String clbb;
    /**
     * 附件
     */
    @ApiModelProperty("附件")
    private String clfj;
    /**
     * 依据文件
     */
    @ApiModelProperty("依据文件")
    private String clyjwj;
    /**
     * 依据描述
     */
    @ApiModelProperty("依据描述")
    private String clyjms;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date gxsj;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date cjsj;
    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String gxr;
    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String cjr;
    /**
     * 备用字段1
     */
    @ApiModelProperty("备用字段1")
    private String byzda;
    /**
     * 备用字段2
     */
    @ApiModelProperty("备用字段2")
    private String byzdb;
    /**
     * 备用字段3
     */
    @ApiModelProperty("备用字段3")
    private String byzdc;
    /**
     * 状态 1/正常 0/禁用
     */
    @ApiModelProperty("状态 1/正常 0/禁用")
    private String status;


}
