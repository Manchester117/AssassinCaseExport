package com.assassin.convert;

import com.assassin.entity.TestRequestDataEntity;
import de.sstoehr.harreader.model.*;

import java.util.*;

/**
 * Created by Peng.Zhao on 2017/8/30.
 */
public class ReadHarComponent {
    public static void setProtocol(HarEntry harEntry, TestRequestDataEntity trDataEntity) {
        // 获取协议类型
        String httpVersion = harEntry.getRequest().getHttpVersion();
        if ("HTTP/1.1".equals(httpVersion)) {
            trDataEntity.setProtocol("http");
        }
    }

    public static void setUrl(HarEntry harEntry, TestRequestDataEntity trDataEntity) {
        // 向实体当中添加URL
        String url = harEntry.getRequest().getUrl();
        trDataEntity.setUrl(url);
    }

    public static void setMethod(HarEntry harEntry, TestRequestDataEntity trDataEntity) {
        // 获取请求类型
        String method = harEntry.getRequest().getMethod().toString();
        trDataEntity.setMethod(method.toLowerCase());
    }

    public static void setCookies(HarEntry harEntry, TestRequestDataEntity trDataEntity) {
        // 获取Cookies
        List<HarCookie> harCookieList = harEntry.getRequest().getCookies();
        Map<String, String> cookieMap = new HashMap<>();
        for (HarCookie harCookie: harCookieList) {
            cookieMap.put(harCookie.getName(), harCookie.getValue());
        }
        trDataEntity.setCookies(cookieMap);
    }

    public static void setHeaders(HarEntry harEntry, TestRequestDataEntity trDataEntity) {
        // 获取Headers
        List<HarHeader> harHeaderList = harEntry.getRequest().getHeaders();
        Map<String, String> headerMap = new HashMap<>();
        for (HarHeader harHeader: harHeaderList) {
            if (!"Cookie".equals(harHeader.getName())) {
                headerMap.put(harHeader.getName(), harHeader.getValue());
            }
        }
        trDataEntity.setHeaders(headerMap);
    }

    public static void setGetParams(HarEntry harEntry, TestRequestDataEntity trDataEntity) {
        // 如果请求类型是GET,则从HAR中将GET参数放入实体
        if (trDataEntity.getMethod().equals("get")) {
            trDataEntity.setUrl(trDataEntity.getUrl().split("\\?")[0]);
            // 获取GET参数
            List<Map<String, String>> getParamList = new ArrayList<>();
            List<HarQueryParam> harQueryParamList = harEntry.getRequest().getQueryString();
            for (HarQueryParam harQueryParam: harQueryParamList) {
                Map<String, String> paramItem = new HashMap<>();
                paramItem.put(harQueryParam.getName(), harQueryParam.getValue());
                getParamList.add(paramItem);
//                System.out.println(harQueryParam.getName() + " = " + harQueryParam.getValue());
            }
            // 如果参数列表中没有任何值,则置为null
            if (!getParamList.isEmpty()) {
                trDataEntity.setGetParams(getParamList);
            } else {
                trDataEntity.setGetParams(null);
            }
        } else {
            trDataEntity.setGetParams(null);
        }
    }

    public static void setPostParams(HarEntry harEntry, TestRequestDataEntity trDataEntity) {
        // 判断是否是Post请求
        boolean isPost = trDataEntity.getMethod().equals("post");
        if (isPost) {
            // 判断是否是application/x-www-form-urlencoded
            String contentTypeVal = trDataEntity.getHeaders().get("Content-Type");
            boolean isContentTypeKeyValue = Objects.equals(contentTypeVal, "application/x-www-form-urlencoded");
            // 如果请求类型是POST,则从HAR中将POST参数放入实体
            if (isContentTypeKeyValue) {
                // 获取POST参数
                List<Map<String, String>> postParamList = new ArrayList<>();
                List<HarPostDataParam> harPostDataParamList = harEntry.getRequest().getPostData().getParams();
                // 如果无法获取参数则将Post参数列表置为空
                if (!harPostDataParamList.isEmpty()) {
                    for (HarPostDataParam harPostDataParam : harPostDataParamList) {
                        Map<String, String> paramItem = new HashMap<>();
                        paramItem.put(harPostDataParam.getName(), harPostDataParam.getValue());
                        postParamList.add(paramItem);
//                System.out.println(harPostDataParam.getName() + " = " + harPostDataParam.getValue());
                    }
                    trDataEntity.setPostParams(postParamList);
                } else {
                    trDataEntity.setPostParams(null);
                }
            }
        } else {
            trDataEntity.setPostParams(null);
        }
    }

    public static void setJsonParams(HarEntry harEntity, TestRequestDataEntity trDataEntity) {
        // 判断是否是Post请求
        boolean isPost = trDataEntity.getMethod().equals("post");
        if (isPost) {
            // 判断是否是application/json
            String contentTypeVal = trDataEntity.getHeaders().get("Content-Type");
            boolean isContentTypeJson = Objects.equals(contentTypeVal, "application/json");
            if (isContentTypeJson) {
                String jsonParams = harEntity.getRequest().getPostData().getText();
                if (!jsonParams.isEmpty()) {
                    trDataEntity.setJsonParams(jsonParams);
                } else {
                    trDataEntity.setJsonParams(null);
                }
//            System.out.println(jsonParams);
            }
        } else {
            trDataEntity.setJsonParams(null);
        }
    }
}
