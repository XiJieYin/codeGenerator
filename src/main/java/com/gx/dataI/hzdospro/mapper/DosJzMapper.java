package com.gx.dataI.hzdospro.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gx.dataI.hzdospro.model.DosJz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-12-16
 */
@Mapper
public interface DosJzMapper extends BaseMapper<DosJz> {

    List<Map<String,Object>> getWorkingPersons(@Param("jzbh") String jzbh);

    DosJz getDosJzByJzbh(@Param("jzbh") String jzbh);
}
