package com.mytomcat.servlet;

import com.mytomcat.MyRequest;
import com.mytomcat.MyResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DefaultServlet extends Servlet {

    @Override
    protected void doGet(MyRequest request, MyResponse response) throws Exception{
        String url = request.getUrl();
        System.out.println();
        url = url.replaceFirst("[/]mytomcat[/]","");
        File file = new File("E:/SourceFile/JAVA/javaweb_tutorial/MyTomcat/"+url);
        if(!file.exists()){
            response.write("您访问的资源"+url+ "不存在！");
            response.setCode(404);
            return;
        }
        FileInputStream inputStream = new FileInputStream(file);
        byte[] buf = new byte[10240];
        int len = 0;
        while((len=inputStream.read(buf))!= -1){
            response.write(new String(buf,0,len));
        }
        response.setCode(200);
    }

    @Override
    protected void doPost(MyRequest request, MyResponse response) throws Exception{
        doGet(request,response);
    }
}
