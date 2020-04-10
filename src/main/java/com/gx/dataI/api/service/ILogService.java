package com.gx.dataI.api.service;

import com.gx.dataI.api.es.entity.Log;
import com.gx.dataI.api.es.entity.LogDA;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface ILogService {

    /**
     * 分页查询
     * @param page 页码 从0开始
     * @param size  分页大小
     * @param userName 操作用户
     * @param operation 操作类型
     * @param operateTimeStart 操作开始时间
     * @param operateTimeEnd 操作结束时间
     * @return
     */
    Page<Log> listPage(Integer page, Integer size, String userName, Integer operation, Date operateTimeStart, Date operateTimeEnd);

    /**
     * 分页查询(档案)
     * @param page 页码 从0开始
     * @param size  分页大小
     * @param operation 操作类型
     * @param operateTimeStart 操作开始时间
     * @param operateTimeEnd 操作结束时间
     * @return
     */
    Page<LogDA> listDAPage(Integer page, Integer size, Integer operation, Date operateTimeStart, Date operateTimeEnd);

    /**
     * 查询所有
     * @param userName 操作用户
     * @param operation 操作类型
     * @param operateTimeStart 操作开始时间
     * @param operateTimeEnd 操作结束时间
     * @return
     */
    List<Log> list(String userName, Integer operation, Date operateTimeStart, Date operateTimeEnd);

    /**
     * 查询所有（档案）
     * @param operation 操作类型
     * @param operateTimeStart 操作开始时间
     * @param operateTimeEnd 操作结束时间
     * @return
     */
    List<LogDA> listDA(Integer operation, Date operateTimeStart, Date operateTimeEnd);

    /**
     * 删除日志
     * @param userName 操作用户 如果不传入就删除所有
     * @return
     */
    int delete(String userName);

    /**
     * 删除日志（档案）
     * @return
     */
    int deleteDA();
}
