package com.wonderful.myfirstcode.chapter5.force_offline;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.base.BaseActivity;
import com.wonderful.myfirstcode.utils.IntentUtils;
import com.wonderful.myfirstcode.utils.PrefUtils;
import com.wonderful.myfirstcode.utils.ToastUtils;

/**
 * A login screen that offers login via account/password.
 */
public class LoginActivity extends BaseActivity {

    private EditText et_account, et_password;
    private CheckBox cb_remember_pass;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_remember_pass = (CheckBox) findViewById(R.id.cb_remember_pass);
        btn_login = (Button) findViewById(R.id.btn_login);

        Boolean isRemember = PrefUtils.getBoolean(this,"remember_pass",false);
        if (isRemember){
            // 将账号和密码都设置到文本框中
            String account = PrefUtils.getString(this,"account","");
            String password = PrefUtils.getString(this,"password","");
            et_account.setText(account);
            et_password.setText(password);
            cb_remember_pass.setChecked(true);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = et_account.getText().toString();
                String password = et_password.getText().toString();
                // 若账号是 wonderful 且密码是 123456，就认为登录成功
                if (account.equals("wonderful") && password.equals("123456")){
                    // 检查复选框是否被勾选
                    if (cb_remember_pass.isChecked()){
                        // 保存数据到SharePreference文件中
                        PrefUtils.setBoolean(LoginActivity.this,"remember_pass",true);
                        PrefUtils.setString(LoginActivity.this,"account",account);
                        PrefUtils.setString(LoginActivity.this,"password",password);
                    }else {
                        // 清除SharePreference文件中的数据
                        PrefUtils.clear(LoginActivity.this);
                    }
                    // 登录成功跳转到主界面
                    IntentUtils.myIntent(LoginActivity.this,ForceOfflineActivity.class);
                    finish();
                }else {
                    ToastUtils.showShort("账号或密码无效！");
                }
            }
        });

    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_login;
    }
}

