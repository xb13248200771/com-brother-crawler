package com.tongji.httpclient.test;

import com.tongji.bean.Request;
import com.tongji.bean.Response;
import com.tongji.downloaders.SimpleDownloader;
import com.tongji.enums.RequestMethod;
import com.tongji.utils.JsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.experimental.categories.ExcludeCategories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by 13248 on 2016/11/10.
 */
public class HttpClientTest {
//
//    public void httpGet(){
//        HttpClient httpClient = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet("http://www.ctrip.com/?utm_source=baidu&utm_medium=cpc&utm_campaign=baidu81&campaign=CHNbaidu81&adid=index&gclid=&isctrip=T");
//        try{
//            HttpResponse loginResponse = httpClient.execute(httpGet);
//            HttpEntity entity = loginResponse.getEntity();
//            if (entity != null) {
//                long instream = entity.getContentLength();
//                BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(entity.getContent())));
//
//                StringBuilder stringBuilder = new StringBuilder();
//                String line = null;
//                while(null != (line = bufferedReader.readLine())) {
//                    stringBuilder.append(line);
//                }
//                System.out.println(instream);
//                System.out.println(stringBuilder.toString());
//            }
//        } catch (Exception e) {
//
//        }
//        System.out.println();
//    }

//    @Test
//    public void httpHandler() {
//        HttpClient cient = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet("http://www.ctrip.com/?utm_source=baidu&utm_medium=cpc&utm_campaign=baidu81&campaign=CHNbaidu81&adid=index&gclid=&isctrip=T");
//        ResponseHandler<byte[]> handler = new ResponseHandler<byte[]>() {
//            @Override
//            public byte[] handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
//                HttpEntity entity = httpResponse.getEntity();
//                try {
//                        return EntityUtils.toByteArray(entity);
//            } catch (Exception e) {
//                return new byte[0];
//            }
//            }
//        };
//        try {
//            byte[] response = cient.execute(httpGet, handler);
//            String line = new String(response, "utf-8");
//            System.out.println(line);
//        } catch (Exception e) {
//            System.out.println("exception happened in request duration");
//        }
//    }

    @Test
    public void testDownload(){
        for (int i=0; i<100; i++){
            final int priorty = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    Request request = new Request();
                    request.setUrl("http://www.baidu.com");
                    request.setMethod(RequestMethod.GET);
                    request.setPriority(priorty);
                    request.setJobName("BAIDU");
                    request.setTaskName("main_page");
                    Response response = SimpleDownloader.download(request);
                }
            };
            try {
                new Thread(run).start();
            } catch (Exception e) {
            }
        }
     }
}
