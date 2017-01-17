package com.wonderful.myfirstcode.chapter8;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.chapter7.permission.CallPhoneActivity;
import com.wonderful.myfirstcode.utils.ToastUtils;

import java.io.File;
import java.io.IOException;

public class MediaPlayActivity extends AppCompatActivity implements View.OnClickListener {

    private Button music_play,music_pause,music_stop;// 音频播放、暂停、停止
    private Button video_play,video_pause,video_replay;// 视频播放、暂停、重新播放

    // 音频播放 创建MediaPlayer实例
    private MediaPlayer mediaPlayer = new MediaPlayer();

    // 视频播放显示
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_play);

        initMediaPlayerView();

        if (ActivityCompat.checkSelfPermission(MediaPlayActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MediaPlayActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else {
            initMediaPlayer(); // 初始化MediaPlayer
            initVideoPath(); // 初始化MediaPlayer
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                    initVideoPath();
                }else {
                    ToastUtils.showShort("你拒绝了权限请求");
                }
                break;

            default:
                break;
        }
    }

    /**
     * 初始化音频播放器
     */
    private void initMediaPlayer() {
        File file = new File(Environment.getExternalStorageDirectory(),"music.mp3");//事先放好的音频文件
        try {
            mediaPlayer.setDataSource(file.getPath()); // 指定音频文件的路径
            mediaPlayer.prepare();// 让 MediaPlayer 进入到准备状态
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化视频播放器
     */
    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(),"movie.mp4");//事先放好的视频文件
        videoView.setVideoPath(file.getPath());   // 指定视频文件的路径
    }


    private void initMediaPlayerView() {
        music_play = (Button) findViewById(R.id.music_play);
        music_pause = (Button) findViewById(R.id.music_pause);
        music_stop = (Button) findViewById(R.id.music_stop);

        music_play.setOnClickListener(this);
        music_pause.setOnClickListener(this);
        music_stop.setOnClickListener(this);

        videoView = (VideoView) findViewById(R.id.video_view);
        video_play = (Button) findViewById(R.id.video_play);
        video_pause = (Button) findViewById(R.id.video_pause);
        video_replay = (Button) findViewById(R.id.video_replay);

        video_play.setOnClickListener(this);
        video_pause.setOnClickListener(this);
        video_replay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 音频播放
            case R.id.music_play:
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.start(); // 开始播放
                }
                break;
            case R.id.music_pause:
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.pause(); // 暂停播放
                }
                break;
            case R.id.music_stop:
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.stop(); // 停止播放
                }
                break;

            // 视频播放
            case R.id.video_play:
                if (!videoView.isPlaying()){
                    videoView.start(); // 开始播放
                }
                break;
            case R.id.video_pause:
                if (!videoView.isPlaying()){
                    videoView.pause(); // 暂停播放
                }
                break;
            case R.id.video_replay:
                if (!videoView.isPlaying()){
                    videoView.resume(); // 重新播放
                }
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();// 释放资源
        }
        if (videoView != null){
            videoView.suspend();  // 释放资源
        }
    }
}
