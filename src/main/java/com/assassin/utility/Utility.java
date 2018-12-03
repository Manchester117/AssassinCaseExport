package com.assassin.utility;

import com.assassin.entity.TestCaseDataEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Peng.Zhao on 2017/8/30.
 */
public class Utility {
    // 从文件夹中读取HAR文件
    public static List<File> readHarFile(String folderPath) {
        File harFolder = new File(folderPath);
        File [] harArray =  harFolder.listFiles();
        List<File> harList = null;
        if (harArray != null) {
            harList = Arrays.asList(harArray);
        }
        return harList;
    }
    // 将实体转换为JSON
    public static File convertObjectToCaseFile(TestCaseDataEntity tcDataEntity, File harFile, String caseFolderPath)  {
        String harSimpleName = FilenameUtils.removeExtension(harFile.getName());
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(caseFolderPath + File.separator + harSimpleName + ".json");

        try {
            // String trDataJson = mapper.writeValueAsString(tcDataEntity);
            // 将实体转变为JSON并进行格式化后写入文件(Jackson格式化,并写入文件)
             mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, tcDataEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonFile;
    }
}
