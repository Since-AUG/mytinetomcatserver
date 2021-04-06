package com.mytomcat;

;
import com.mytomcat.servlet.Servlet;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    private ServerSocket serverSocket;
    public static void main(String[] args){
        new MyServer().start();
    }
    public void start(){
        try {
            serverSocket = new ServerSocket(8088);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("服务器已经启动，准备接受请求");
        while(true){
            try {
                Socket socket = serverSocket.accept();
                new Thread(new MyRequestDispatcher(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
