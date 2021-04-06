package com.mytomcat.servlet;

import com.mytomcat.MyRequest;
import com.mytomcat.MyResponse;

public abstract class Servlet {
    public void service(MyRequest request, MyResponse response) throws Exception{
        if("get".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        }else{
            doPost(request,response);
        }
    }
    protected abstract void doGet(MyRequest request, MyResponse response) throws Exception;
    protected abstract void doPost(MyRequest request, MyResponse response) throws Exception;
}
