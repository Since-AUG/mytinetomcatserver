package com.mytomcat;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Random;

public class Test {
    @org.junit.Test
    public void testFile() throws IOException, ClassNotFoundException, DocumentException {
        Document document = new SAXReader().read(new File("myserver/WEB_INF/web.xml"));

    }
}
