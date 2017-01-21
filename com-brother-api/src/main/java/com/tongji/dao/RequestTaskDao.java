package com.tongji.dao;

import com.tongji.bean.Request;
import com.tongji.enums.RequestTaskResult;

/**
 * Created by 13248 on 2016/11/16.
 */
public interface RequestTaskDao {

    public Request getRequestTask();

    public RequestTaskResult putRequestTask();
}
