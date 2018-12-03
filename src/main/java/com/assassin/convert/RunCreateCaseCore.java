package com.assassin.convert;

import com.assassin.entity.TestCaseDataEntity;
import com.assassin.utility.Utility;
import com.assassin.entity.TestStepDataEntity;
import com.assassin.entity.TestRequestDataEntity;
import com.assassin.utility.DeployTools;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.HarReaderMode;
import de.sstoehr.harreader.model.*;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Peng.Zhao on 2017/8/18.
 */
public class RunCreateCaseCore {
    private String jarFolderPath;
    private List<File> harList;

    public RunCreateCaseCore() {
        // IDE运行
        this.harList = Utility.readHarFile("har");
    }

    public RunCreateCaseCore(String jarFolderPath) {
        this.jarFolderPath = jarFolderPath;
        // JAR包运行
        // this.harList = Utility.readHarFile(jarFolderPath + "har");
    }

    public List<File> createAllCaseFile() {
        List<File> caseJsonFileList = new ArrayList<>();
        File caseJsonFile = null;
        if (this.harList != null) {
            for (File harFile: this.harList) {
                TestCaseDataEntity tcDataEntity = this.createSingleCaseFile(harFile);
                // 将请求的HAR转换为JSON文件
                if (this.jarFolderPath != null) {
                    caseJsonFile = Utility.convertObjectToCaseFile(tcDataEntity, harFile, jarFolderPath);
                } else {
                    caseJsonFile = Utility.convertObjectToCaseFile(tcDataEntity, harFile, "case");
                }
                caseJsonFileList.add(caseJsonFile);
            }
        }
        return caseJsonFileList;
    }

    private TestCaseDataEntity createSingleCaseFile(File harFile) {
        TestCaseDataEntity tcDataEntity = new TestCaseDataEntity();
        HarReader harReader = new HarReader();
        Har har = null;
        File caseJsonFile = null;

        // 去掉文件后缀
        String fileSimpleName = FilenameUtils.removeExtension(harFile.getName());
        // 给实体添加businessName
        tcDataEntity.setBusinessName(fileSimpleName);

        try {
            // 读取HAR文件
            // *注意!这里必须使用HarReaderMode.LAX模式,如果使用HarReaderMode.STRICT模式会因为Jackson不识别response中的expires而出现HarReaderException异常
            har = harReader.readFromFile(harFile, HarReaderMode.LAX);
            // 获取HAR文件中每个请求的实体
            List<HarEntry> harEntryList = har.getLog().getEntries();
            // 定义测试步骤列表
            List<TestStepDataEntity> tsDataEntityList = new ArrayList<>();
            // 按照顺序遍历每个请求
            for (HarEntry harEntry: harEntryList) {
                // 定义单个测试步骤实体
                TestStepDataEntity tsDataEntity = new TestStepDataEntity();
                // 定义单个请求实体
                TestRequestDataEntity trDataEntity = new TestRequestDataEntity();
                // 从HAR文件中读取每个请求数据
                ReadHarComponent.setProtocol(harEntry, trDataEntity);
                ReadHarComponent.setUrl(harEntry, trDataEntity);
                ReadHarComponent.setMethod(harEntry, trDataEntity);
                ReadHarComponent.setCookies(harEntry, trDataEntity);
                ReadHarComponent.setHeaders(harEntry, trDataEntity);
                ReadHarComponent.setGetParams(harEntry, trDataEntity);
                ReadHarComponent.setPostParams(harEntry, trDataEntity);
                ReadHarComponent.setJsonParams(harEntry, trDataEntity);
                // 将单个请求实体放置到测试步骤实体当中
                tsDataEntity.setRequestData(trDataEntity);
                // 将测试用例步骤放置到步骤列表当中
                tsDataEntityList.add(tsDataEntity);
                // 将步骤列表放置到测试用例当中
                tcDataEntity.setTestStep(tsDataEntityList);
            }
        } catch (HarReaderException e) {
            e.printStackTrace();
        }
        return tcDataEntity;
    }


    public static void main(String[] args) {
        DeployTools deployTools = new DeployTools();
        deployTools.getJarFolderPath();
        String jarFolderPath = deployTools.createFolder();
        // JAR包运行
//        RunCreateCaseCore runCreateCaseCore = new RunCreateCaseCore(jarFolderPath);
        // IDE运行
        RunCreateCaseCore runCreateCaseCore = new RunCreateCaseCore();
        runCreateCaseCore.createAllCaseFile();
    }
}
