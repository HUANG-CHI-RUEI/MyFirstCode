package com.wonderful.myfirstcode.chapter10.inquiry_thread;

import android.os.AsyncTask;

import com.wonderful.myfirstcode.utils.ToastUtils;

/**
 * Function：
 * Author：kxwon on 2017/1/20 14:19
 * Email：kxwonder@163.com
 */

public class DownloadTask extends AsyncTask<Void,Integer,Boolean> {

    /**
     * 在后台任务执行前调用，用于一些界面上的初始化操作
     */
    @Override
    protected void onPreExecute() {
        //super.onPreExecute();
        //progressDialog.show(); // 显示进度对话框
    }

    /**
     * 在子线程中运行，在这处理所有耗时操作
     * 注意：不可进行 UI 操作，若需要可调用 publishProgress(Progress...)方法来完成
     * @param params
     * @return
     */
    @Override
    protected Boolean doInBackground(Void... params) {
//        try {
//            while (true){
//                int downloadPercent = doDownload();// 这是一个虚构的方法
//                publishProgress(downloadPercent);
//                if (downloadPercent >= 100){
//                    break;
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        return true;
    }

    /**
     * 当后台任务中调用了 publishProgress(Progress...)方法后调用
     * 返回的数据会作为参数传递到此方法中，可利用返回的数据进行一些 UI 操作
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        //super.onProgressUpdate(values);
        // 在这里更新下载速度
        //progressDialog.setMessage("Download " + values[0] + "%");
    }

    /**
     * 当后台任务执行完毕并通过 return 语句进行返回时调用
     * @param result
     */
    @Override
    protected void onPostExecute(Boolean result) {
        //super.onPostExecute(result);
        //progressDialog.dismiss();// 关闭进度对话框
        if (result){
            ToastUtils.showShort("下载成功");
        }else {
            ToastUtils.showShort("下载失败");
        }
    }
}
