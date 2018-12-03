package com.assassin.entity;

import java.util.List;

/**
 * Created by Peng.Zhao on 2017/8/30.
 * @description 测试用例(流程)实体
 */
public class TestCaseDataEntity {
    private String businessName;
    private List<TestStepDataEntity> testStep;

    public TestCaseDataEntity() {
        this.businessName = "";
        this.testStep = null;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List<TestStepDataEntity> getTestStep() {
        return testStep;
    }

    public void setTestStep(List<TestStepDataEntity> testStep) {
        this.testStep = testStep;
    }

    @Override
    public String toString() {
        return "TestCaseDataEntity{" +
                "businessName='" + businessName + '\'' +
                ", testStep=" + testStep +
                '}';
    }
}
