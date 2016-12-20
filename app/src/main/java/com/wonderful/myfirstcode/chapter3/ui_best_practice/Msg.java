package com.wonderful.myfirstcode.chapter3.ui_best_practice;

/**
 * 消息实体类
 * Created by KXwon on 2016/12/11.
 */

public class Msg {

    public static final int TYPE_RECEIVED = 0; // 收到的消息类别

    public static final int TYPE_SENT = 1;     // 发出的消息类别

    private String content; // 消息内容

    private int type;       // 消息类型

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
