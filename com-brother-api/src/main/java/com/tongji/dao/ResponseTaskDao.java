package com.tongji.dao;

import com.tongji.bean.Response;
import com.tongji.enums.RequestTaskResult;

/**
 * Created by 13248 on 2016/11/16.
 */
public interface ResponseTaskDao {

    public RequestTaskResult saveResponse(Response response);
}
