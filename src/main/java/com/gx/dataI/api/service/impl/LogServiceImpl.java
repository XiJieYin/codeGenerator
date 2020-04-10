package com.gx.dataI.api.service.impl;

import com.gx.dataI.api.es.entity.Log;
import com.gx.dataI.api.es.entity.LogDA;
import com.gx.dataI.api.es.repository.LogDARepository;
import com.gx.dataI.api.es.repository.LogRepository;
import com.gx.dataI.api.service.ILogService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>日志管理 Service</p>
 *
 */
@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    LogRepository logRepository;
    @Autowired
    LogDARepository logDARepository;


    public NativeSearchQueryBuilder getQueryBuilder(String userName, Integer operation, Date operateTimeStart, Date operateTimeEnd){
        //构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(userName!=null){
            boolQueryBuilder.must(QueryBuilders.termQuery("userName",userName));
        }
        if(operation!=null){
            boolQueryBuilder.must(QueryBuilders.termQuery("operation",operation));
        }
        if(operateTimeStart!=null){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("operateTime").gt(operateTimeStart.getTime()));
        }
        if(operateTimeEnd!=null){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("operateTime").lt(operateTimeEnd.getTime()));
        }
        queryBuilder.withQuery(boolQueryBuilder);
        return queryBuilder;
    }

    @Override
    public Page<Log> listPage(Integer page, Integer size, String userName, Integer operation, Date operateTimeStart, Date operateTimeEnd) {
        NativeSearchQueryBuilder queryBuilder = getQueryBuilder(userName,operation,operateTimeStart,operateTimeEnd);

        queryBuilder.withPageable(PageRequest.of(page,size));
        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("operateTime").order(SortOrder.DESC));

        Page<Log> logs = logRepository.search(queryBuilder.build());
        // 总条数
        long total = logs.getTotalElements();
        System.out.println("总条数 = " + total);
        // 总页数
        System.out.println("总页数 = " + logs.getTotalPages());
        // 当前页
        System.out.println("当前页：" + logs.getNumber());
        // 每页大小
        System.out.println("每页大小：" + logs.getSize());
        return logs;
    }

    @Override
    public Page<LogDA> listDAPage(Integer page, Integer size, Integer operation, Date operateTimeStart, Date operateTimeEnd) {
        NativeSearchQueryBuilder queryBuilder = getQueryBuilder(null,operation,operateTimeStart,operateTimeEnd);

        queryBuilder.withPageable(PageRequest.of(page,size));
        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("operateTime").order(SortOrder.DESC));

        Page<LogDA> logs = logDARepository.search(queryBuilder.build());
        // 总条数
        long total = logs.getTotalElements();
        System.out.println("总条数 = " + total);
        // 总页数
        System.out.println("总页数 = " + logs.getTotalPages());
        // 当前页
        System.out.println("当前页：" + logs.getNumber());
        // 每页大小
        System.out.println("每页大小：" + logs.getSize());
        return logs;
    }

    @Override
    public List<Log> list(String userName, Integer operation, Date operateTimeStart, Date operateTimeEnd) {
        NativeSearchQueryBuilder queryBuilder = getQueryBuilder(userName,operation,operateTimeStart,operateTimeEnd);
        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("operateTime").order(SortOrder.DESC));
        List<Log> logList = new ArrayList<>();
        Iterable<Log> logs = logRepository.search(queryBuilder.build());
        for (Log log:logs) {
            logList.add(log);
        }
        return logList;
    }

    @Override
    public List<LogDA> listDA(Integer operation, Date operateTimeStart, Date operateTimeEnd) {
        NativeSearchQueryBuilder queryBuilder = getQueryBuilder(null,operation,operateTimeStart,operateTimeEnd);
        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("operateTime").order(SortOrder.DESC));
        List<LogDA> logList = new ArrayList<>();
        Iterable<LogDA> logs = logDARepository.search(queryBuilder.build());
        for (LogDA log:logs) {
            logList.add(log);
        }
        return logList;
    }

    @Override
    public int delete(String userName) {
        try{
            if (userName!=null){
                logRepository.deleteByUserName(userName);
            }else{
                logRepository.deleteAll();
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int deleteDA() {
        try{
            logDARepository.deleteAll();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }


}
