package com.gx.dataI.api.service;

import com.alibaba.fastjson.JSONObject;

public interface IRecordService {

    /**
     * 记录操作接口
     * @param record 请求的JSON
     * 注：所有主键统一名称为"recId"，外键统一使用frecId；所有文件名称字段统一使用fname
     * 可以操作单个业务类型的记录，也可以带嵌套，删除记录，通过recId删除，如果需要嵌套删除，需要子业务类型，根据主记录recId作为条件删除。如果要删除图片，即根据fname记录的名称删除。
     *     记录状态控制是否能操作。
     * @return 成功返回1 失败返回0
     */
    int saveRecord(JSONObject record);
}
