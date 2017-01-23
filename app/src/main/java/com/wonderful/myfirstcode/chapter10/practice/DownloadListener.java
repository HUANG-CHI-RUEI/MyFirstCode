package com.wonderful.myfirstcode.chapter10.practice;

/**
 * Function：回调接口，对下载过程中的各种状态进行监听和回调
 * Author：kxwon on 2017/1/22 19:09
 * Email：kxwonder@163.com
 */

public interface DownloadListener {

    void onProgress(int progress);   // 下载进度

    void onSuccess();     // 下载成功

    void onFailed();     // 下载失败

    void onPaused();     // 下载暂停

    void onCanceled();   // 下载取消
}
