package com.qsmx.zww.demo.hashcode;


import java.util.Objects;

/**
 * 使用java8新特性 重写对象的hashcode 和 equals 方法
 *
 *
 * 这个已经实现好了 以后可以直接这个样子去写去用，切记重写equals 方法时必须重写hashcode方法
 */
public class OverrideHashcode {

    private String name;
    private int id;

    public OverrideHashcode(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int hashCode() {
        //  开始重写 return super.hashCode();
        return Objects.hash(id, name);  //java8
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        OverrideHashcode objt = (OverrideHashcode) obj;
        return Objects.equals(id, objt.id) && Objects.equals(name, objt.name);
        //  开始重写 return super.equals(obj);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        OverrideHashcode hashcode1 = new OverrideHashcode("zhangsan", 1);
        OverrideHashcode hashcode2 = new OverrideHashcode("zhangsan", 2);
        System.out.println("如果不重写，object父类中使用的是== 比较的地址值而不知对象的值" + hashcode1.equals(hashcode2));
        System.out.println("hashcode1的hashcode:" + hashcode1.hashCode() + "========hashcode2的hashcode:" + hashcode2.hashCode());
    }
}
