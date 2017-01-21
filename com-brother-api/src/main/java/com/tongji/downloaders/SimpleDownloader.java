package com.tongji.downloaders;

import com.tongji.bean.Request;
import com.tongji.bean.Response;
import com.tongji.config.HttpClientConfig;
import com.tongji.enums.RequestMethod;
import com.tongji.utils.JsonUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 13248 on 2016/11/12.
 */


@Service
public class SimpleDownloader {

    /**
     * 当配备连接池管理器时，比如PoolingClientConnectionManager， HttpClient 可以被用使用
     * 多线程来同时执行多个请求。
     * PoolingClientConnectionManager 将会基于它的配置来分配连接。如果对于给定路由的所有连
     * 接都被使用了，那么连接的请求将会阻塞，直到一个连接被释放回连接池。它
     * 可以通过设置'http.conn-manager.timeout'为一个正数来保证连接管理器不会在连接请求执行
     * 时无限期的被阻塞。如果连接请求不能在给定的时间内被响应，将会抛出异常
     * 一个HttpClient 实例是线程安全的，并且能够在多个执行线程直接共享，强烈建议每个线程
     * 保持一个它自己专有的HttpContext 实例
     */

    private static CloseableHttpClient closeableHttpClient = null;

    private static RequestConfig requestConfig = null;

    private static DownloadRetryInterceptor downloadRetryInterceptor = new DownloadRetryInterceptor();

    private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();

    static {

        requestConfig = RequestConfig
                .custom()
                .setSocketTimeout(com.tongji.config.RequestConfig.socketTimeout)
                .setConnectionRequestTimeout(com.tongji.config.RequestConfig.connectionRequestTimeout)
                .build();
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(HttpClientConfig.DefaultMaxPerRoute);
        poolingHttpClientConnectionManager.setMaxTotal(HttpClientConfig.MaxTotal);
        closeableHttpClient = HttpClients.custom()
                .setUserAgent(HttpClientConfig.UserAgent)
                .setRetryHandler(downloadRetryInterceptor)
                .setConnectionManager(poolingHttpClientConnectionManager).build();
    }

    public SimpleDownloader() {

    }

    public static Response downloadGET(Request request) {
        HttpGet get = new HttpGet(request.getUrl());
        get.setConfig(requestConfig);
        setHeaders(get, request.getHeaders());
        Response response = null;
        try {
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(get);
            response = generateResponse(request, closeableHttpResponse);
            closeableHttpResponse.close();
        } catch (Exception e) {
            response = downloadGET(request);
        }
        if (response == null) {
            return downloadGET(request);
        }
        return response;
    }

    public static Response downloadPOST(Request request) {
        HttpPost post = new HttpPost(request.getUrl());
        post.setConfig(requestConfig);
        Response response = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(generateFormPost(request.getData())));
            setHeaders(post, request.getHeaders());
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(post);
            response = generateResponse(request, closeableHttpResponse);
            closeableHttpResponse.close();
        } catch (Exception e) {

        }
        return null;
    }

    public static Response downloadBinary(Request request) {
        return null;
    }

    public void close() {
        try {
            this.closeableHttpClient.close();
        } catch (Exception e) {

        }
    }


    public static Response download(Request request) {
        if (request.getMethod() == RequestMethod.GET) {
            return downloadGET(request);
        } else if (request.getMethod() == RequestMethod.POST) {
            return downloadPOST(request);
        } else if (request.getMethod() == RequestMethod.BINARY) {
            return downloadBinary(request);
        } else {
            return null;
        }
    }

    public static String encodeContent(byte[] responseByte, String encoding) {
        String encode = encoding.toLowerCase();
        String content = null;
        try {
            content = new String(responseByte, encode);
        } catch (Exception e) {

        }
        return content;
    }

    public static void setHeaders(HttpRequestBase httpRequestBase, HashMap<String, Object> headers) {
        for (HashMap.Entry<String, Object> entry : headers.entrySet()) {
            httpRequestBase.setHeader(entry.getKey(), entry.getValue().toString());
        }
    }

    public static Response generateResponse(Request request, CloseableHttpResponse closeableHttpResponse) {
        Response response = new Response();
        String responseContent = null;
        try {
            responseContent = EntityUtils.toString(closeableHttpResponse.getEntity(), request.getEncoding());
        } catch (Exception e) {
            return null;
        }
        response.setContent(responseContent);
        response.setJobName(request.getJobName());
        response.setRequest(JsonUtils.toString(request));
        response.setTaskName(request.getTaskName());
        response.setResponseUrl(request.getUrl());
        response.setStatusCode(closeableHttpResponse.getStatusLine().getStatusCode());
        response.setStatusLine(closeableHttpResponse.getStatusLine().toString());
        response.setExtras(request.getExtras());
        List<Header> listHeaders = new ArrayList<Header>();
        for (Header header : closeableHttpResponse.getAllHeaders()) {
            listHeaders.add(header);
        }
        response.setHeaders(listHeaders);
        return response;
    }

    public static List<NameValuePair> generateFormPost(HashMap<String, String> data) {
        List<NameValuePair> form = new LinkedList<NameValuePair>();
        for (HashMap.Entry<String, String> entry : data.entrySet()) {
            form.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return form;
    }

}
