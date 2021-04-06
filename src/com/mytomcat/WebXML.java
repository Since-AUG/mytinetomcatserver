package com.mytomcat;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WebXML {
    private List<MyServletEntity> entityList;
    private List<MyServletMapping> mappingList;

//    public WebXML(List<ServletEntity> entityList, List<ServletMapping> mappingList) {
//        this.entityList = entityList;
//        this.mappingList = mappingList;
//    }

    public List<MyServletEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<MyServletEntity> entityList) {
        this.entityList = entityList;
    }

    public List<MyServletMapping> getMappingList() {
        return mappingList;
    }

    public void setMappingList(List<MyServletMapping> mappingList) {
        this.mappingList = mappingList;
    }

    public WebXML() {
        entityList = new ArrayList<>();
        mappingList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "WebXML{" +
                "entityList=" + entityList +
                ", mappingList=" + mappingList +
                '}';
    }

    public void parse(){
        Document document = null;
        try {
            document = new SAXReader().read(new File("E:\\SourceFile\\JAVA\\javaweb_tutorial\\MyTomcat\\src\\web.xml"));
            Element webApp = document.getRootElement();
            List<Element> servletElements =  webApp.elements("servlet");
            for(Element e:servletElements){
                String name = e.element("servlet-name").getText();
                String clazz = e.element("servlet-class").getText();
                entityList.add(new MyServletEntity(name,clazz));
            }
            Iterator<Element> servletMapping = webApp.elementIterator("servlet-mapping");
            while(servletMapping.hasNext()){
                Element e = servletMapping.next();
                String name = e.element("servlet-name").getText();
                Iterator<Element> urlpattern = e.elementIterator("url-pattern");
                List<String> urls = new ArrayList<>();
                while(urlpattern.hasNext()){
                    Element e2 = urlpattern.next();
                    urls.add(e2.getText());
                }
                MyServletMapping servletMapping1 = new MyServletMapping(name,urls);
                mappingList.add(servletMapping1);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}
