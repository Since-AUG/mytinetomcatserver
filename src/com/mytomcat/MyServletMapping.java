package com.mytomcat;

import java.util.ArrayList;
import java.util.List;

public class MyServletMapping {
    private String name;
    private List<String> urls;
    public MyServletMapping(){
         this.urls = new ArrayList<>();
    }

    public MyServletMapping(String name, List<String> urls) {
        this.name = name;
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "ServletMapping{" +
                "name='" + name + '\'' +
                ", urls=" + urls +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
