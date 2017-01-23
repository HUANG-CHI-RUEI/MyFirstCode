package com.wonderful.myfirstcode.chapter10.practice;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Function：下载功能
 * Author：kxwon on 2017/1/22 19:16
 * Email：kxwonder@163.com
 */

public class DownloadTask extends AsyncTask<String,Integer,Integer>{

    public static final int TYPE_SUCCESS = 0;  // 下载成功
    public static final int TYPE_FAILED = 1;   // 下载失败
    public static final int TYPE_PAUSED = 2;   // 下载暂停
    public static final int TYPE_CANCELED = 3; // 下载取消

    private DownloadListener listener;

    private boolean isCanceled = false;

    private boolean isPaused = false;

    private int lastProgress;//上一次的下载进度

    public DownloadTask(DownloadListener listener){
        this.listener = listener;
    }

    /**
     * 用于在后台执行具体的下载逻辑
     * @param params
     * @return
     */
    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try{
            long downloadedLength = 0;// 记录已下载的文件长度
            String downloadUrl = params[0];//获取到下载的URL地址
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));//下载的文件名
            String directory = Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_DOWNLOADS).getPath();//将文件下载到指定目录
            file = new File(directory + fileName);
            if (file.exists()){
                downloadedLength = file.length();
            }
            long contentLength = getContentLength(downloadUrl);//获取待下载文件的总长度
            if (contentLength == 0){
                return TYPE_FAILED;
            }else if (contentLength == downloadedLength){
                // 已下载字节和文件总字节相等，说明已经下载完成了
                return TYPE_SUCCESS;
            }
            // 发送网络请求，从网络读取数据写入到本地，直到文件下载完
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    // 断点下载，指定从哪个字节开始下载
                    .addHeader("RANGE","bytes=" + downloadedLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null){
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file,"rw");
                savedFile.seek(downloadedLength);//跳过已下载的字节
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1){
                    if (isCanceled){
                        return TYPE_CANCELED;
                    }else if (isPaused){
                        return TYPE_PAUSED;
                    }else {
                        total += len;
                        savedFile.write(b,0,len);
                        // 计算已下载的百分比
                        int progress = (int)((total + downloadedLength) * 100/contentLength);
                        publishProgress(progress);//通知当前的下载进度
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
                if (savedFile != null){
                    savedFile.close();
                }
                if (isCanceled && file != null){
                    file.delete();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    /**
     * 用于在界面上更新当前的下载速度
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];//当前的下载进度
        if (progress > lastProgress){
            listener.onProgress(progress);//通知下载进度更新
            lastProgress = progress;
        }
    }

    /**
     * 用于通知最终的下载结果
     * @param integer
     */
    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case TYPE_SUCCESS:
                listener.onSuccess();//下载成功
                break;
            case TYPE_FAILED:
                listener.onFailed();//下载失败
                break;
            case TYPE_PAUSED:
                listener.onPaused();//下载暂停
                break;
            case TYPE_CANCELED:
                listener.onCanceled();//下载取消
                break;
            default:
                break;
        }
    }

    /**
     * 暂停
     */
    public void pauseDownload(){
        isPaused = true;
    }

    /**
     * 取消
     */
    public void cancelDownload(){
        isCanceled = true;
    }

    /**
     * 获取待下载文件的总长度
     * @param downloadUrl
     * @return
     */
    private long getContentLength(String downloadUrl) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()){
            long contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }
}
