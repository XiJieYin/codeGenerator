package com.gx.dataI;

import com.gx.dataI.common.filter.LoginFilter;
import com.gx.dataI.common.listener.CloseListener;
import com.gx.dataI.common.listener.StartListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Arrays;

@SpringBootApplication
public class HzdosDataInterfaceApplication {
    // war部署
// extends SpringBootServletInitializer

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        //解决ES 与 Redis冲突
//        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication sa = new SpringApplication(HzdosDataInterfaceApplication.class);
        sa.addListeners(new StartListener());
        sa.addListeners(new CloseListener());
        sa.run(args);
        long time = System.currentTimeMillis() - beginTime;
        System.out.println("\033[40;32;0m" + "启动成功！耗时 : " + time + "\t\t\t\033[0m");
        for(String s : args){
            if(s.startsWith("--datai.allow.swagger.address=")){
                if(s.indexOf("=")>0){
                    String ips = s.substring(s.indexOf("=")+1);
                    if(ips!=null){
                        System.out.println(ips);
                        String [] allowArr = ips.split(",");
                        LoginFilter.allowList = Arrays.asList(allowArr);

                    }
                }
            }
        }

    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(HzdosDataInterfaceApplication.class);
//    }
}
