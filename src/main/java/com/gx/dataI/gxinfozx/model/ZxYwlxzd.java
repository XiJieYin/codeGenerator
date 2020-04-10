package com.gx.dataI.gxinfozx.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 业务类型字典表
 * </p>
 *
 * @author 
 * @since 2019-12-16
 */
@Data
@Accessors(chain = true)
@TableName("ZX_YWLXZD")
public class ZxYwlxzd implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * GUID
     */
    @TableId(value = "GUID",type = IdType.UUID)
    private String guid;
    /**
     * 业务名称
     */
    private String ywmc;
    /**
     * 业务编码
     */
    private String ywbm;
    /**
     * 业务类型
     */
    private String ywlx;
    /**
     * 描述
     */
    private String ywms;
    /**
     * 版本
     */
    private String ywbb;
    /**
     * 附件
     */
    private String ywfj;
    /**
     * 所属单位
     */
    private String ssdw;
    /**
     * 父业务类型
     */
    private String fywlx;
    /**
     * 依据文件
     */
    private String ywyjwj;
    /**
     * 依据描述
     */
    private String ywyjms;
    /**
     * 更新时间
     */
    private Date gxsj;
    /**
     * 创建时间
     */
    private Date cjsj;
    /**
     * 更新人
     */
    private String gxr;
    /**
     * 创建人
     */
    private String cjr;
    /**
     * 备用字段1
     */
    private String byzda;
    /**
     * 备用字段2
     */
    private String byzdb;
    /**
     * 备用字段3
     */
    private String byzdc;
    /**
     * 状态 1/正常 0/禁用
     */
    private String status;
    /**
     * 点和线
     */
    private String nodeAndLine;


}
