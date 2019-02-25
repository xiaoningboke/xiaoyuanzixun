package com.sunxiaoning.xiaoyuanfuwu.common.configuration;

import com.sunxiaoning.xiaoyuanfuwu.tools.FileTools;
import com.sunxiaoning.xiaoyuanfuwu.tools.PropertiesTools;

public class ApplicationConfig {

    public ApplicationConfig() {

    }

    /**
     * 工程初始化
     */
    public void init() {
        // 1. 创建assets文件夹
//        String root = FileTools.projectRootPath(); // 默认工程根目录作为assets的根目录
        this.generateAssetsPath();
    }


    /**
     * assets路径
     */
    public void generateAssetsPath() {
        String assets = PropertiesTools.applicationProperty("app.image.path");
        if (assets != null && !assets.equals("")) {
            FileTools.generateDirectory(assets);
            System.out.println("## 创建assets文件夹 ##  :=> 结束");
        }
    }
}
