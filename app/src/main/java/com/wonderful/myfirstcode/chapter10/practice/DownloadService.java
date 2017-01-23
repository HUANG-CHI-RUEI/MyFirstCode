package com.wonderful.myfirstcode.chapter10.practice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.utils.ToastUtils;

import java.io.File;

public class DownloadService extends Service {

    private DownloadTask downloadTask;

    private String downloadUrl;

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,getNotification("Downloading ...",progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            // 下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Success",-1));
            ToastUtils.showShort("下载成功");
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            // 下载失败时将前台服务通知关闭，并创建一个下载失败的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed",-1));
            ToastUtils.showShort("下载失败");
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            ToastUtils.showShort("下载暂停");
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            ToastUtils.showShort("下载取消");
        }
    };

    public DownloadService() {
    }

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder{
        /**
         * 开始下载
         * @param url
         */
        public void startDownload(String url){
            if (downloadTask == null){
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(1,getNotification("Downloading ...",0));
                ToastUtils.showShort("Downloading ...");
            }
        }

        /**
         * 暂停下载
         */
        public void pauseDownload(){
            if (downloadTask != null){
                downloadTask.pauseDownload();;
            }
        }

        /**
         * 取消下载
         */
        public void cancelDownload(){
            if (downloadTask != null){
                downloadTask.cancelDownload();
            }else {
                if (downloadUrl != null){
                    // 取消下载时需要将文件删除，并且通知关闭
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory
                            (Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()){
                        file.delete();
                    }

                }
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    /**
     * 构建通知方法
     */
    private Notification getNotification(String title,int progress){
        Intent intent = new Intent(this,DownloadActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress > 0){
            // 当progress大于或等于0时才需要显示下载进度
            builder.setContentText(progress + "%");
            // 三个参数：通知的最大进度、通知的当前进度、是否使用模糊进度条
            builder.setProgress(100,progress,false);
        }
        return builder.build();
    }
}
