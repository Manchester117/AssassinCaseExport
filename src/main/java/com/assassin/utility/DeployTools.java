package com.assassin.utility;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Peng.Zhao on 2017/8/17.
 */
public class DeployTools {
    private String jarFolder;

    /**
     * @description: 获取JAR文件当前存放文件夹
     */
    public void getJarFolderPath() {
        // 获取JAR包完全路径名
        String jarPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            // 路径中存在中文则必须对路径进行转码
            jarPath = URLDecoder.decode(jarPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 根据路径名获取存放目录
        this.jarFolder = new File(jarPath).getParentFile().getAbsolutePath();
    }

    /**
     * @description: 创建用例和报告的文件夹
     * @return 返回当前路径
     */
    public String createFolder() {
        boolean caseCreateFlag = false;
        boolean reportCreateFlag = false;
        String jarFolderPath = this.jarFolder + File.separator;

        if (this.jarFolder != null) {
            File caseFolder = new File(jarFolderPath + "case");
            if (!caseFolder.exists()) {
                caseCreateFlag = caseFolder.mkdirs();
            }

            File reportFolder = new File(jarFolderPath +  "har");
            if (!reportFolder.exists()) {
                reportCreateFlag = reportFolder.mkdirs();
            }
        }

        if (caseCreateFlag && reportCreateFlag) {
            System.out.println("Case转换目录创建成功");
        }

        return jarFolderPath;
    }
}
