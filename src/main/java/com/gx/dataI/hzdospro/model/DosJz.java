package com.gx.dataI.hzdospro.model;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @author
 * @since 2020-03-13
 */
@ApiModel(value = "DosJz", description = "")
@Data
@Accessors(chain = true)
public class DosJz implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("")
    @TableId(value = "ID",type = IdType.INPUT)
    private Integer id;
    /**
     * 关联案卷号
     */
    @ApiModelProperty("关联案卷号")
    private Integer ajId;
    /**
     * 卷宗类型
     */
    @ApiModelProperty("卷宗类型")
    private String jzlx;
    /**
     * 组织机构代码
     */
    @ApiModelProperty("组织机构代码")
    private String orgCode;
    /**
     * 组织机构名称
     */
    @ApiModelProperty("组织机构名称")
    private String orgName;
    /**
     * 保存期限
     */
    @ApiModelProperty("保存期限")
    private String bcqx;
    /**
     * 卷宗件数（条目数量）
     */
    @ApiModelProperty("卷宗件数（条目数量）")
    private Integer jzjs;
    /**
     * 卷宗页数（图片数量）
     */
    @ApiModelProperty("卷宗页数（图片数量）")
    private Integer jzys;
    /**
     * 立卷单位
     */
    @ApiModelProperty("立卷单位")
    private String ljdw;
    /**
     * 全宗号
     */
    @ApiModelProperty("全宗号")
    private String qzh;
    /**
     * 类别号
     */
    @ApiModelProperty("类别号")
    private String lbh;
    /**
     * 案卷号
     */
    @ApiModelProperty("案卷号")
    private String ajh;
    /**
     * 起止时间
     */
    @ApiModelProperty("起止时间")
    private String qzsj;
    /**
     * 录入人ID
     */
    @ApiModelProperty("录入人ID")
    private Integer userId;
    /**
     * 录入时间
     */
    @ApiModelProperty("录入时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updateTime;
    /**
     * 题名
     */
    @ApiModelProperty("题名")
    private String jztm;
    /**
     * 状态（0.删除 1.未开始 2.进行中 3.已完成）
     */
    @ApiModelProperty("状态（0.删除 1.未开始 2.进行中 3.已完成）")
    private Integer satae;
    /**
     * 顺序码
     */
    @ApiModelProperty("顺序码")
    private Integer sxm;
    /**
     * 卷宗编号
     */
    @ApiModelProperty("卷宗编号")
    private String jzbh;
    /**
     * 条形码编号
     */
    @ApiModelProperty("条形码编号")
    private String txmbh;
    /**
     * 目录号
     */
    @ApiModelProperty("目录号")
    private String mlh;
    /**
     * 存放地点
     */
    @ApiModelProperty("存放地点")
    private String cfdd;
    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date kssj;
    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date jssj;
    /**
     * 修改人id
     */
    @ApiModelProperty("修改人id")
    private String xgrid;
    /**
     * 修改人名称
     */
    @ApiModelProperty("修改人名称")
    private String xgrmc;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String bz;
    /**
     * 存放位置
     */
    @ApiModelProperty("存放位置")
    private Long ddId;
    @ApiModelProperty("")
    private String ddmc;
    /**
     * 1.未审核 2.审核未通过 3.已审核
     */
    @ApiModelProperty("1.未审核 2.审核未通过 3.已审核")
    private Integer shzt;
    /**
     * 1.未审核 2.审核未通过 3.已审核
     */
    @ApiModelProperty("1.未审核 2.审核未通过 3.已审核")
    private Integer tmshzt;
    @ApiModelProperty("")
    private String jzlxCode;
    /**
     * 扫描完成(0.未开始,1完成 2.进行中)
     */
    @ApiModelProperty("扫描完成(0.未开始,1完成 2.进行中)")
    private Integer smzt;
    /**
     * 录入完成(0.未开始 1.完成 2.进行中)
     */
    @ApiModelProperty("录入完成(0.未开始 1.完成 2.进行中)")
    private Integer lrzt;
    /**
     * 条目录入完成(0.未开始 1.完成 2.进行中)
     */
    @ApiModelProperty("条目录入完成(0.未开始 1.完成 2.进行中)")
    private Integer tmzt;
    /**
     * 案卷编号
     */
    @ApiModelProperty("案卷编号")
    private String ajbh;
    /**
     * 录入人名称
     */
    @ApiModelProperty("录入人名称")
    private String recordPerson;
    /**
     * 年度
     */
    @ApiModelProperty("年度")
    private String nd;
    /**
     * 条目类型(1.业务 2统计)
     */
    @ApiModelProperty("条目类型(1.业务 2统计)")
    private Integer tmlxs;
    /**
     * 任务中的卷宗进度审核状态(0.未开始 1.完成 2.进行中)
     */
    @ApiModelProperty("任务中的卷宗进度审核状态(0.未开始 1.完成 2.进行中)")
    private Integer rwjzshzt;
    /**
     * 异常情况(0.没出现过异常 1.出现异常)
     */
    @ApiModelProperty("异常情况(0.没出现过异常 1.出现异常)")
    private Integer yczt;
    /**
     * 图片实际上传页数
     */
    @ApiModelProperty("图片实际上传页数")
    private Double sjscys;
    @ApiModelProperty("")
    private String guid;
    /**
     * 同步数据状态，0或者空表示正常，1表示等审核验收，2表示验收不通过，3表示验收出错，4表示抽查不过,5表示抽查通过
     */
    @ApiModelProperty("同步数据状态，0或者空表示正常，1表示等审核验收，2表示验收不通过，3表示验收出错，4表示抽查不过,5表示抽查通过")
    private Long syncflag;
    /**
     * 是否已经上传到FDFS了（0或者空就是没上传，1就是上传了）
     */
    @ApiModelProperty("是否已经上传到FDFS了（0或者空就是没上传，1就是上传了）")
    private Integer fdfsupload;
    /**
     * 备注1
     */
    @ApiModelProperty("备注1")
    private String bz1;
    /**
     * 备注2
     */
    @ApiModelProperty("备注2")
    private String bz2;


}
