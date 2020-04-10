package com.gx.dataI.common.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


public class Generator {

        public static void main(String[] args) {
            // 包名
            String packageName = "com.gx.dataI.hzdospro";
            boolean serviceNameStartWithI = true;//auth -> UserService, 设置成true: auth -> IUserService
            //把需要自动生成的表 放在这里!!
//            generateByTables(serviceNameStartWithI, packageName, "", "GXINFOZX", new String[]{
//                    "ZX_CLMBZD",
//                    "ZX_YWLXZD"
//            });
            generateByTables(serviceNameStartWithI, packageName, "", "HZDOSPRO","HZDOS", new String[]{
                    "TM_TMTPXXB"
            });
            System.out.println("completed...");
        }

        /**
         * @param serviceNameStartWithI
         * @param packageName   包名
         * @param author  作者
         * @param username  数据库用户名
         * @param tableNames 表名
         */
        public static void generateByTables(boolean serviceNameStartWithI, String packageName, String author, String username,String password, String... tableNames) {
            GlobalConfig config = new GlobalConfig();
            String dbUrl = "jdbc:oracle:thin:@192.168.18.254:1521:orcl";
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            dataSourceConfig.setDbType(DbType.ORACLE)
                    .setUrl(dbUrl)
                    .setUsername(username)
                    .setPassword(password)
                    .setDriverName("oracle.jdbc.OracleDriver");
            StrategyConfig strategyConfig = new StrategyConfig();
            strategyConfig
                    .setCapitalMode(true)
                    .setEntityLombokModel(true)
                    .setDbColumnUnderline(true)
                    .setNaming(NamingStrategy.underline_to_camel)
                    .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
            config.setActiveRecord(false)
                    .setAuthor(author)
                    .setOutputDir("d:\\codeGen")
                    .setFileOverride(true)
                    .setEnableCache(false);
            if (!serviceNameStartWithI) {
                config.setServiceName("%sService");
            }
            AutoGenerator ag = new AutoGenerator().setGlobalConfig(config)
                    .setDataSource(dataSourceConfig)
                    .setStrategy(strategyConfig)
                    .setPackageInfo(
                            new PackageConfig()
                                    .setParent(packageName)
                                    .setController("controller")
                                    .setEntity("model")
                                    .setMapper("mapper")
                                    .setService("service")
                                    .setServiceImpl("service.impl")
                                    .setXml("mappers")
                    );
            ag.setTemplate(selftTemplateConfig());
            ag.execute();
        }

        public static TemplateConfig selftTemplateConfig(){
            TemplateConfig templateConfig = new TemplateConfig();
            //这些 ConstVal 是默认的
            String entity = ConstVal.TEMPLATE_ENTITY_JAVA;
            String service = ConstVal.TEMPLATE_SERVICE;
            String serviceImpl = ConstVal.TEMPLATE_SERVICEIMPL;
            String mapper = ConstVal.TEMPLATE_MAPPER;
            String xml = ConstVal.TEMPLATE_XML;
            String controller = ConstVal.TEMPLATE_CONTROLLER;

            entity = "/templates/generator/entity.java";
            mapper = "/templates/generator/mapper.java";
            xml = "/templates/generator/mapper.xml";
            service = "/templates/generator/service.java";
            serviceImpl = "/templates/generator/serviceImpl.java";
            controller = "/templates/generator/controller.java";

            templateConfig.setController(controller);
            templateConfig.setEntity(entity);
            templateConfig.setMapper(mapper);
            templateConfig.setService(service);
            templateConfig.setServiceImpl(serviceImpl);
            templateConfig.setXml(xml);

            return templateConfig;
        }

}
