package com.wonderful.myfirstcode.chapter11;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.chapter7.permission.CallPhoneActivity;
import com.wonderful.myfirstcode.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    private LocationClient mLocationClient;

    private TextView tv_show_location;// 显示当前位置信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 构建 LocationClient 实例
        mLocationClient = new LocationClient(getApplicationContext());
        // 注册一个定位监听器
        mLocationClient.registerLocationListener(new MyLocationListener());
        setContentView(R.layout.activity_location);

        tv_show_location = (TextView) findViewById(R.id.tv_show_location);

        // 声明权限，将权限添加到list集合中再一次性申请
        List<String> permissionList = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(LocationActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ActivityCompat.checkSelfPermission(LocationActivity.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ActivityCompat.checkSelfPermission(LocationActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(LocationActivity.this,permissions,1);
        }else {
            requestLocation();
        }
    }

    /**
     * 开始地理位置定位
     */
    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);  //5秒钟更新下当前位置
        option.setIsNeedAddress(true);
        //option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        mLocationClient.setLocOption(option);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 ){
                    for (int result : grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            ToastUtils.showShort("必须同意所有权限才能使用本程序");
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else {
                    ToastUtils.showShort("发生未知错误");
                    finish();
                }
                break;

            default:
                break;
        }
    }

    // 监听器
    public class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            StringBuilder currentLocation = new StringBuilder();
            currentLocation.append("纬度：").append(bdLocation.getLatitude()).append("\n");
            currentLocation.append("经线：").append(bdLocation.getAltitude()).append("\n");
            currentLocation.append("国家：").append(bdLocation.getCountry()).append("\n");
            currentLocation.append("省：").append(bdLocation.getProvince()).append("\n");
            currentLocation.append("市：").append(bdLocation.getCity()).append("\n");
            currentLocation.append("区：").append(bdLocation.getDistrict()).append("\n");
            currentLocation.append("街道：").append(bdLocation.getStreet()).append("\n");
            currentLocation.append("定位方式：");
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation){
                currentLocation.append("GPS");
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation){
                currentLocation.append("网络");
            }
            tv_show_location.setText(currentLocation);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();//停止定位
    }
}
