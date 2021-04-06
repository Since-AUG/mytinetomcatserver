package com.mytomcat;

import com.mytomcat.servlet.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyRequestDispatcher implements Runnable {
    private Socket socket;

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            MyRequest request = new MyRequest(inputStream);
            MyResponse response = new MyResponse(outputStream);
            String url = request.getUrl();
            Servlet servlet = WebApp.getServlet(url);
            int code = 200;
            if(servlet == null){
                code = 404;
                response.write("未找到您要访问的资源");
            }else {
                try {
                    servlet.service(request, response);
                } catch (Exception e) {
                    code = 500;
                    response.write(e.getMessage());
                }
            }
            response.response(code);
            socket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyRequestDispatcher(Socket socket){
        this.socket = socket;
    }
}
