package com.wonderful.myfirstcode.http;

/**
 * Function：针对 HttpURLConnection 的回调接口
 * Author：kxwon on 2017/1/17 16:09
 * Email：kxwonder@163.com
 */

public interface HttpCallbackListener {
    void onFinish(String response);// 请求成功时调用
    void onError(Exception e);// 请求失败时调用
}
