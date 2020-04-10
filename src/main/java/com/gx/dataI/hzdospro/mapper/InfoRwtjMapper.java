package com.gx.dataI.hzdospro.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gx.dataI.hzdospro.model.InfoRwtj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-12-20
 */
@Mapper
public interface InfoRwtjMapper extends BaseMapper<InfoRwtj> {
    InfoRwtj selectByUserIdAndRylxs(@Param("userId") Long UserId, @Param("rylx") Long rylx, @Param("rwId") Long rwId, @Param("cjsj") String cjsj);
}
