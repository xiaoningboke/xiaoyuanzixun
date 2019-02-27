package com.sunxiaoning.xiaoyuanfuwu.tools;


import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileTools {


    /**
     * 获取项目根目录
     *
     * @return 根路径
     */
    public static String projectRootPath() {
        File directory = new File("");// 参数为空
        try {
            return directory.getCanonicalPath();
        } catch (IOException e) {
//            e.printStackTrace();
            return null;
        }
    }

//    public static String saveBase64Image(String base64str) {
//
//    }


    /**
     * 获取base64对应图片的内容
     *
     * @param base64str
     * @return 图片内容
     */
    public static String base64imageContent(String base64str) {
        if (base64str.indexOf(",") > 0) {
            return base64str.split(",")[1];
        }
        return base64str;
    }


    /**
     * 获取base64对应图片的类型，默认png
     *
     * @param base64str
     * @return 图片类型
     */
    public static String base64imageType(String base64str) {
        if (base64str.indexOf(",") > 0) {
            return base64str.split(",")[0]
                    .split(";")[0]
                    .split("/")[1];
        }
        String defaultType = PropertiesTools.applicationProperty("app.assets.defaultType");
        return defaultType != null && !defaultType.equals("") ? defaultType : "png";
    }


    /**
     * 保存图片
     *
     * @param base64image 图片base64字符串
     * @return 文件名
     */
    public static String saveBase64Image(String base64image) {

        Date date = new Date();
        String dataForm = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String filePath = PropertiesTools.applicationProperty("app.image.path") + dataForm + '/';
        File file = new File(filePath);
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }

        String fileType = FileTools.base64imageType(base64image);// "a.png";
        String fileName = FileTools.randomFileName(null);
        String fileContent = FileTools.base64imageContent(base64image);
        String imageFullName = fileName + "." + fileType;

        FileTools.saveBase64Images(fileContent, filePath + imageFullName);
        return dataForm + '/' + imageFullName;
    }

    /**
     * 保存图片
     *
     * @param base64str 图片base64字符串
     * @param filePath  完整的文件路径（包含文件名）
     * @return 文件地址
     */
    public static String saveBase64Images(String base64str, String filePath) {

        // 参数校验
        if (base64str == null || filePath == null)
            return null;

        // 检查目录是否存在，同时生成目录
        String fileDirectory = FileTools.directoryFromPath(filePath);
        if (!FileTools.generateDirectory(fileDirectory)) {
            return null;
        }

        // 存储文件
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(base64str);
            for (int i = 0; i < b.length; ++i) {
                //调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            //生成jpeg图片
            OutputStream out = new FileOutputStream(filePath);
            out.write(b);
            out.flush();
            out.close();

            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 处理图片数组
     * @param images
     * @return
     */
    public static ArrayList saveBase64Images(ArrayList images){
        ArrayList<String> imgs = new ArrayList<String>();
        for(int i = 0;i<images.size();i++){
            String img = FileTools.saveBase64Image(images.get(i).toString());
            imgs.add(img);
        }
        return imgs;
    }

    /**
     * 从完整的路径中获取所在的文件夹目录
     *
     * @param filePath 文件完整路径
     * @return 文件地址
     */
    public static String directoryFromPath(String filePath) {
        String[] filePathComps = filePath.split("/");
        String[] fileDirectoryComps = new String[filePathComps.length - 1];
        System.arraycopy(filePathComps, 0, fileDirectoryComps, 0, filePathComps.length - 1);
        String fileDirectory = String.join("/", fileDirectoryComps);
        return fileDirectory;
    }

    /**
     * 创建目录
     *
     * @param dir 文件夹目录
     * @return 是否创建成功
     */
    public static boolean generateDirectory(String dir) {
        try {
            File directory = new File(dir);
            if (directory.exists())
                return true;
            return directory.mkdirs();
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 获取随机文件名称
     *
     * @return
     */
    public static String randomFileName(String pre) {
        String preName = pre != null && !pre.equals("") ? pre : "";
        return preName + System.currentTimeMillis() + "";
    }

    public static void main(String[] args) {
        System.out.println(FileTools.projectRootPath());
    }
}
