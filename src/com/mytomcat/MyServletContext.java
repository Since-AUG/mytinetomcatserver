package com.mytomcat;

import java.util.HashMap;
import java.util.Map;

public class MyServletContext {
    private Map<String,String> servlet;
    private Map<String,String> mapping;

    public MyServletContext(Map<String, String> servlet, Map<String, String> mapping) {
        this.servlet = servlet;
        this.mapping = mapping;
    }

    public MyServletContext() {
        servlet = new HashMap<>();
        mapping = new HashMap<>();
    }

    public String getServletClass(String name){
        return servlet.get(name);
    }

    public String getServletName(String urlPattern){
        return mapping.get(urlPattern);
    }

    public Map<String, String> getServlet() {
        return servlet;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

}
