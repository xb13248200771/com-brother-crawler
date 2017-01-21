package com.tongji.bean;

import com.tongji.enums.RequestMethod;
import com.tongji.utils.DateTimeUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by 13248200771 on 2016/11/10.
 *
 */
public class Request implements Serializable {

    private String signature;

    private String jobName;

    private String taskName;

    private HashMap<String,Object> headers = new HashMap<String,Object>();

    private HashMap<String,String> cookies = new HashMap<String, String>();

    private String encoding = "utf-8";

    private HashMap<String, String> data = new HashMap<String, String>();

    private HashMap<String, Object> extras = new HashMap<String, Object>();

    private String url;

    private long priority;

    private String crawlTime = DateTimeUtils.getNowDateTime();

    private RequestMethod method;

    private Integer retryTime;


    public Request(){

    }

    public Request (String url, String method, String encoding, long priority) {
        this.url = url;
        this.method = RequestMethod.getMehtodEnum(method);
        this.encoding = encoding;
        this.priority = priority;
    }

    public Integer getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(Integer retryTime) {
        this.retryTime = retryTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCrawlTime() {
        return crawlTime;
    }

    public void setCrawlTime(String crawlTime) {
        this.crawlTime = crawlTime;
    }

    public void putHeader(String key, Object value){
        if (key.equals("") || value.equals("")){
            return;
        }else if (key == null || value == null) {
            return;
        } else {
            this.headers.put(key, value);
        }
    }

    public void putData(String key, String value) {
        if (key.equals("") || value.equals("")){
            return;
        }else if (key == null || value == null) {
            return;
        } else {
            this.headers.put(key, value);
        }
    }

    public HashMap<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, Object> headers) {
        this.headers = headers;
    }

    public HashMap<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(HashMap<String, String> cookies) {
        this.cookies = cookies;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public HashMap<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(HashMap<String, Object> extras) {
        this.extras = extras;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    public void putExtra(String key, Object value) {
        this.extras.put(key, value);
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
