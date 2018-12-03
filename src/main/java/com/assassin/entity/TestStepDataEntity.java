package com.assassin.entity;

import java.util.*;

/**
 * Created by Peng.Zhao on 2017/8/30.
 */
public class TestStepDataEntity {
    private String stepName;
    private TestRequestDataEntity requestData;
    private List<Map<String, String>> correlationParams;
    private List<Map<String, String>> verifyList;
    private List<Map<String, Object>> fetchDB;
//    private String isCaptureImage;

    public TestStepDataEntity() {
        this.stepName = "";
        this.requestData = null;
        this.correlationParams = new ArrayList<>();
        this.verifyList = new ArrayList<>();
        this.fetchDB = new ArrayList<>();
//        this.isCaptureImage = "false";

        Map<String, String> correlationItem = new LinkedHashMap<>();
        correlationItem.put("corrField", "");
        correlationItem.put("corrPattern", "");
        correlationItem.put("corrExpression", "");
        correlationItem.put("corrIndex", "");
        correlationItem.put("isUseForFetchDB", "");
        this.correlationParams.add(correlationItem);

        // 使用有序树创建验证点
        Map<String, String> verifyItem = new LinkedHashMap<>();
        verifyItem.put("verifyField", "");
        verifyItem.put("pattern", "");
        verifyItem.put("expression", "");
        verifyItem.put("index", "");
        verifyItem.put("expectValue", "");
        this.verifyList.add(verifyItem);

        // 创建DB-Fetch的初始模板
        Map<String, Object> fetchItem = new LinkedHashMap<>();
        fetchItem.put("sql", "");
        fetchItem.put("columnList", new ArrayList<>());
        this.fetchDB.add(fetchItem);
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public TestRequestDataEntity getRequestData() {
        return requestData;
    }

    public void setRequestData(TestRequestDataEntity requestData) {
        this.requestData = requestData;
    }

    public List<Map<String, String>> getCorrelationParams() {
        return correlationParams;
    }

    public void setCorrelationParams(List<Map<String, String>> correlationParams) {
        this.correlationParams = correlationParams;
    }

    public List<Map<String, String>> getVerifyList() {
        return verifyList;
    }

    public void setVerifyList(List<Map<String, String>> verifyList) {
        this.verifyList = verifyList;
    }

    public List<Map<String, Object>> getFetchDB() {
        return fetchDB;
    }

    public void setFetchDB(List<Map<String, Object>> fetchDB) {
        this.fetchDB = fetchDB;
    }

//    public String getIsCaptureImage() {
//        return isCaptureImage;
//    }
//
//    public void setIsCaptureImage(String isCaptureImage) {
//        this.isCaptureImage = isCaptureImage;
//    }

    @Override
    public String toString() {
        return "TestStepDataEntity{" +
                "stepName='" + stepName + '\'' +
                ", requestData=" + requestData +
                ", correlationParams=" + correlationParams +
                ", verifyList=" + verifyList +
                ", fetchDB=" + fetchDB +
                '}';
    }
}
