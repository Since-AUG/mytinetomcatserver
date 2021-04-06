package com.mytomcat;

public class MyServletEntity {
    private String name;
    private String clazz;

    @Override
    public String toString() {
        return "ServletEntity{" +
                "name='" + name + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public MyServletEntity() {
    }

    public MyServletEntity(String name, String clazz) {
        this.name = name;
        this.clazz = clazz;
    }
}
