package com.sunxiaoning.xiaoyuanfuwu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 事务
//@EnableTransactionManagement
// mapper 接口类扫描包配置
@MapperScan("com.sunxiaoning.xiaoyuanfuwu.api.dao")
public class XiaoyuanfuwuApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoyuanfuwuApplication.class, args);
    }

}
