package com.gx.dataI.common.utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Result", description = "返回封装")
public class Result<T> {
    @ApiModelProperty("返回成功的代码")
    private String result;
    @ApiModelProperty("返回的数据体")
    private T data;
}
