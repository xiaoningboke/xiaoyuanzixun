package com.sunxiaoning.xiaoyuanfuwu.tools;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesTools {


    /**
     * 获取application.properties中的值
     * @param key 键
     * @return property值
     */
    public static String applicationProperty(String key) {
        if (key == null || key.equals(""))
            return null;

        // 创建依赖文件夹
        Properties prop = null;
        try {
            // 读取属性文件a.properties
            prop = PropertiesLoaderUtils.loadAllProperties("application.properties");
            // 获取
            return prop.getProperty(key);

        } catch (IOException e) {
//            e.printStackTrace();
            return null;
        }
    }
}
