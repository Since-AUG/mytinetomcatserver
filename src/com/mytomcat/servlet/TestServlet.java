package com.mytomcat.servlet;

import org.dom4j.DocumentException;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestServlet {
    @Test
    public void testFile() throws IOException, ClassNotFoundException, DocumentException {
        File file = new File("content/demo.html");
        System.out.println(file.exists());
        FileInputStream inputStream = new FileInputStream(file);
        byte[] buf = new byte[10240];
        int len = inputStream.read(buf);
        System.out.println(new String(buf,0,len));
    }
}
