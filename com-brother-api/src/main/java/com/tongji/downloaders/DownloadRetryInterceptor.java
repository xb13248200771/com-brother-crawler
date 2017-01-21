package com.tongji.downloaders;

import com.tongji.utils.JsonUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * Created by 13248 on 2016/11/13.
 */
public class DownloadRetryInterceptor  implements HttpRequestRetryHandler{
    @Override
    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        if (executionCount > 5) {
            return false;
        }
        if (exception instanceof InterruptedIOException) {
            return true;
        }
        if (exception instanceof UnknownHostException) {
            return false;
        }
        if (exception instanceof ConnectTimeoutException) {
            return true;
        }
        if (exception instanceof SSLException) {
            return false;
        }
        if (exception instanceof SocketTimeoutException) {
            return true;
        }
        if (exception instanceof ClientProtocolException) {
            return true;
        }
        return false;
    }
}
