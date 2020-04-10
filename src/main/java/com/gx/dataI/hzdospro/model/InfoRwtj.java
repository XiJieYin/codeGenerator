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
 * 
 * </p>
 *
 * @author 
 * @since 2019-12-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("INFO_RWTJ")
@ApiModel(value = "InfoRwtj", description = "任务统计")
public class InfoRwtj implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "ID",type = IdType.INPUT)
    private Long id;
    /**
     * 当前用户ID
     */
    private Long userId;
    /**
     * 任务ID
     */
    private Long rwId;
    /**
     * 任务名称
     */
    private String rwmc;
    /**
     * 卷宗数量
     */
    private Long jzsl;
    /**
     * 案卷数量
     */
    private Long ajsl;
    /**
     * 材料数量
     */
    private Long clsl;
    /**
     * 扫描页数
     */
    private Long smys;
    /**
     * 录入页数
     */
    private Long lrys;
    /**
     * 审核页数
     */
    private Long shys;
    /**
     * 组织机构代码
     */
    private String orgCode;
    /**
     * 组织机构名称
     */
    private String orgName;
    /**
     * 计划开始时间
     */
    private Date jhkssj;
    /**
     * 计划结束时间
     */
    private Date jhjssj;
    /**
     * 人员类型(0.卷宗整理录入员 1.扫描员 2.条目管理员 3.录入员 4.审核员 5.负责人 6.图像质检员)
     */
    private Integer rylx;
    /**
     * 状态
     */
    private Integer zt;
    /**
     * 创建时间
     */
    private String cjsj;
    private String guid;
    /**
     * 更新时间
     */
    private Date updateTime;


}
