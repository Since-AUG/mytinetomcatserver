package com.mytomcat;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

public class MyRequest {
    private String method;
    private String url;
    private String requestInfo;
    private Map<String, List<String>> parameters;
    private static final String CRLF = "\r\n";
    private static final String BLANK = " ";
    public String getUrl(){
        return url;
    }
    public MyRequest(){
        requestInfo = "";
        method = "";
        url = "";
        parameters = new HashMap<>();
    }
    public MyRequest(InputStream is){
        this();
        parseRequestInfo(is);
    }
    public void parseRequestInfo(InputStream is){

        byte[] buf = new byte[10240];
        int index = 0;
        try {
            index = is.read(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestInfo = new String(buf,0,index);
        String requestLine = requestInfo.substring(0,requestInfo.indexOf(CRLF)).trim();
        String[] requestlines = requestLine.split(BLANK);
        method = requestlines[0];
        String parameterLine = "";
        if(method.equalsIgnoreCase("get")){
            if(requestlines[1].contains("?")){
                url = requestlines[1].substring(0,requestlines[1].indexOf("?"));
                String[] tmp = requestlines[1].split("[?]");
                parameterLine = tmp[1].trim();
            }else{
                url = requestlines[1].trim();
            }
        }else{
            url = requestlines[1].trim();
            parameterLine = requestInfo.substring(requestInfo.lastIndexOf(CRLF)+1).trim();
        }
        parseParameter(parameterLine);

    }
    public void parseParameter(String parameterLine){
        String[] tokens=parameterLine.split("[&]");
        for(String token:tokens){
            String[] keyAndValue = token.split("[=]");
            if(keyAndValue.length==1){
                keyAndValue = Arrays.copyOf(keyAndValue,2);
                keyAndValue[1] = null;
            }
            String value = keyAndValue[1];
            try {
                value = keyAndValue[1]==null?null: URLDecoder.decode(keyAndValue[1],"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if(parameters.containsKey(keyAndValue[0])){
                parameters.get(keyAndValue[0]).add(value);
            }else{
                List<String> values = new ArrayList<>();
                values.add(value);
                parameters.put(keyAndValue[0],values);
            }
        }
        System.out.println(parameters);
    }

    public String getMethod() {
        return method;
    }

    public String getParameter(String name){
        if(parameters.containsKey(name)){
            List<String> list = parameters.get(name);
            if(list == null || list.size()==0){
                return null;
            }else{
                return list.get(0);
            }
        }
        return null;
    }
    public String[] getParameterValues(String name){
        if(parameters.containsKey(name)){
            List<String> list = parameters.get(name);
            if(list == null || list.size()==0){
                return null;
            }else{
                return list.toArray(new String[0]);
            }
        }
        return null;
    }
}
