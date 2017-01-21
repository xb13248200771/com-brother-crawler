package com.tongji.enums;

/**
 * Created by 13248 on 2016/11/11.
 */
public enum RequestMethod {

    GET(1,"GET"),

    POST(2, "POST"),

    BINARY(3, "BINARY");

    private int  id;

    private String methodName;

    RequestMethod(int id, String name){
        this.id = id;
        this.methodName = name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String toString(){
        return this.getMethodName();
    }

    public static RequestMethod getMehtodEnum (String method) {
        method = method.toLowerCase();
        if (method.equals("post")) {
            return POST;
        } else if (method.equals("binary")){
            return BINARY;
        } else {
            return GET;
        }
    }

}
