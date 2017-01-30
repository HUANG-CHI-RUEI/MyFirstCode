package com.wonderful.myfirstcode.chapter13;

import java.io.Serializable;

/**
 * Function：
 * Author：kxwon on 2017/1/30 11:22
 * Email：kxwonder@163.com
 */

public class Person implements Serializable{

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
