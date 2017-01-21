package com.tongji.thread;

import com.tongji.dao.RequestTaskDao;
import com.tongji.dao.ResponseTaskDao;
import com.tongji.downloaders.SimpleDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 13248 on 2016/11/16.
 */

@Service
public class DownloadThread implements Runnable{

    @Autowired
    private SimpleDownloader simpleDownloader;

    @Autowired
    private RequestTaskDao requestTaskDao;


    @Override
    public void run() {

    }
}
