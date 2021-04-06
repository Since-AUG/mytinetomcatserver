package com.mytomcat;

import java.io.*;

public class MyResponse {
    private static final String CRLF = "\r\n";
    private int length;
    private StringBuilder responseLine;
    private StringBuilder responseHeaders;
    private StringBuilder content;
    private BufferedWriter bufferedWriter;
    private int code;
    public void setCode(int code){
        this.code = code;
    }
    public MyResponse(){
        responseLine = new StringBuilder();
        responseHeaders = new StringBuilder();
        content = new StringBuilder();
        length = 0;
    }
    public MyResponse(OutputStream os){
        this();
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(os));
    }
    public void createResponseLine(int statusCode){
        responseLine.append("HTTP/1.1 ").append(statusCode).append(" ");
        switch (statusCode){
            case 200:
                responseLine.append("OK");
                break;
            case 404:
                responseLine.append("NOT FOUND");
                break;
            case 500:
                responseLine.append("INTERNAL SERVER ERROR");
                break;
            default:
                responseLine.append("OTHER");
        }
        responseLine.append(CRLF);
    }
    public void createResponseHeaders(){
        responseHeaders.append("Content-Type: text/html; charset=UTF-8").append(CRLF);
        responseHeaders.append("Content-Length: ").append(length).append(CRLF).append(CRLF);
    }
    public void write(String info){
        content.append(info);
        try {
            length += info.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void response(int code){
        createResponseLine(code);
        createResponseHeaders();
        StringBuilder info = new StringBuilder();
        info.append(responseLine).append(responseHeaders).append(content);
        try {
            bufferedWriter.write(info.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void response(){
        createResponseLine(this.code);
        createResponseHeaders();
        StringBuilder info = new StringBuilder();
        info.append(responseLine).append(responseHeaders).append(content);
        try {
            bufferedWriter.write(info.toString());
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
