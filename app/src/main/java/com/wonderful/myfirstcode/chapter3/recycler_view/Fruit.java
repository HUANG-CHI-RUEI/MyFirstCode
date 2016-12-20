package com.wonderful.myfirstcode.chapter3.recycler_view;

/**
 * 水果类
 * Created by KXwon on 2016/12/11.
 */

public class Fruit {
    private String name; // 水果名
    private int imageId; // 水果图片id

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
