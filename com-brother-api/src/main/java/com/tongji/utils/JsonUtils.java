package com.tongji.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by 13248 on 2016/11/12.
 */
public class JsonUtils {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static String toString(Object obj) {
        try {
            return  objectMapper.writeValueAsString(obj);
        } catch (IOException ioe) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
