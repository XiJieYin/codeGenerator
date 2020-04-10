package com.gx.dataI.hzdospro.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gx.dataI.hzdospro.model.OriInfoPrimary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 主表－原文信息表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-12-16
 */
@Mapper
public interface OriInfoPrimaryMapper extends BaseMapper<OriInfoPrimary> {
    /**
     * 获取主表已经录入完成的原文数量
     * @param jzbh 卷宗编号
     * @param state 状态
     * @return
     */
    int getCountOriNum(@Param("jzbh") String jzbh, @Param("state") Integer state);

    /**
     * 根据主表的GUID删除详情表
     * @param guid guid
     * @param table 表名
     * @return
     */
    int deleteOriInfoByGUID(@Param("guid") String guid, @Param("table") String table);

    /**
     * 根据卷宗编号查询原文主表
     * @param jzbh
     * @return
     */
    List<OriInfoPrimary> getByJzbh(@Param("jzbh") String jzbh);
    /**
     * 根据卷宗删除原文主表
     * @param jzbh
     * @return
     */
    int deleteByJzbh(@Param("jzbh") String jzbh);

    /**
     * 获取材料信息
     * @param materialId
     * @return
     */
    Map<String,Object> getDictMaterial(@Param("materialId") String materialId);

    /**
     * 获取与材料关联的表的字段列
     * @param materialId
     * @return
     */
    List<Map<String,Object>> getTableCol(@Param("materialId") String materialId);

    /**
     * 动态插入原文录入信息
     * @param insertSql
     * @return
     */
    int insertOriInfo(@Param("insertSql") String insertSql);
}
