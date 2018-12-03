package com.assassin.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by Peng.Zhao on 2017/7/24.
 * @description 测试步骤实体
 */
public class TestRequestDataEntity {
    private String url;
    private String protocol;
    private String method;
    private Map<String, String> cookies;
    private Map<String, String> headers;
    private List<Map<String, String>> getParams;
    private List<Map<String, String>> postParams;
    private String jsonParams;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public List<Map<String, String>> getGetParams() {
        return getParams;
    }

    public void setGetParams(List<Map<String, String>> getParams) {
        this.getParams = getParams;
    }

    public List<Map<String, String>> getPostParams() {
        return postParams;
    }

    public void setPostParams(List<Map<String, String>> postParams) {
        this.postParams = postParams;
    }

    public String getJsonParams() {
        return jsonParams;
    }

    public void setJsonParams(String jsonParams) {
        this.jsonParams = jsonParams;
    }


    @Override
    public String toString() {
        return "TestRequestDataEntity{" +
                ", url='" + url + '\'' +
                ", protocol='" + protocol + '\'' +
                ", method='" + method + '\'' +
                ", cookies=" + cookies +
                ", headers=" + headers +
                ", getParams=" + getParams +
                ", postParams=" + postParams +
                ", jsonParams='" + jsonParams + '\'' +
                '}';
    }
}
