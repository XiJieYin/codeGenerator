package com.gx.dataI.common.annotation;

import com.gx.dataI.common.enums.OperateSYS;
import com.gx.dataI.common.enums.OperateType;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    OperateType operate();
    String msg();
    OperateSYS operateSYS() default OperateSYS.ZX;
}
