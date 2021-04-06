package com.mytomcat;


import com.mytomcat.servlet.DefaultServlet;
import com.mytomcat.servlet.Servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebApp {
    private static MyServletContext servletContext;
    static {
        WebXML webXML = new WebXML();
        webXML.parse();
        List<MyServletEntity> servletEntities = webXML.getEntityList();
        List<MyServletMapping> servletMappings = webXML.getMappingList();
        Map<String,String> servlet = new HashMap<>();
        for(MyServletEntity servletEntity:servletEntities){
            servlet.put(servletEntity.getName(),servletEntity.getClazz());
        }

        Map<String,String> mapping = new HashMap<>();
        for(MyServletMapping servletMapping:servletMappings){
            List<String> urls = servletMapping.getUrls();
            for(String url:urls){
                mapping.put(url,servletMapping.getName());
            }
        }
        servletContext = new MyServletContext(servlet,mapping);
    }

    public static Servlet getServlet(String urlPattern){
        if(urlPattern == null || urlPattern.equals("")){
            return null;
        }
        String name = servletContext.getServletName(urlPattern);
        if(name == null){
            if(urlPattern.contains("."))
                return new DefaultServlet();
            else {
                return null;
            }
        }
        String clazz = servletContext.getServletClass(name);
        try {
            Class servletClazz = Class.forName(clazz);
            return (Servlet)servletClazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
