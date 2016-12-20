package com.wonderful.myfirstcode.chapter7.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.utils.ToastUtils;

public class CallPhoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);

        Button btn_call_phone = (Button) findViewById(R.id.btn_call_phone);
        btn_call_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 判断用户是否授权，借助 ContextCompat.checkSelfPermission() 方法
                // ContextCompat.checkSelfPermission() 方法接收两参数：context 和权限名
                if (ActivityCompat.checkSelfPermission(CallPhoneActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // 3. 若没授权，则调用 ActivityCompat.requestPermissions()方法来向用户授权
                    // 其三个参数：Activity实例、String数组（放权限名）、请求码（只要唯一就行了,这里传1）
                    ActivityCompat.requestPermissions(CallPhoneActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                    // 4. 调用完requestPermissions()方法后，会弹出一个权限申请对话框，供用户选择，
                    // 最后回调onRequestPermissionsResult()方法
                }else {
                    // 2. 若已授权，则直接执行拨打电话的逻辑
                    call();
                }
            }
        });
    }

    /**
     * 拨打电话方法
     */
    private void call() {
        try {
            // Intent.ACTION_CALL 是系统内置的打电话动作
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    /**
     * 无论用户是否同意权限申请，都会回调此方法
     * @param requestCode
     * @param permissions
     * @param grantResults 授权的结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                }else {
                    ToastUtils.showShort("你拒绝了权限请求");
                }
                break;

            default:
                break;
        }
    }
}
