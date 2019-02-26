package com.example.y700_15.ds2_zk2.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.y700_15.ds2_zk2.R;
import com.example.y700_15.ds2_zk2.model.bean.LoginBean;
import com.example.y700_15.ds2_zk2.presenter.LoginPresenter;
import com.example.y700_15.ds2_zk2.view.interfaces.LoginView;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements LoginView<LoginBean> {
    private EditText edit_name,edit_pwd;
    private CheckBox check_jz;
    private TextView text_reg;
    private Button but_login;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private LoginPresenter loginPresenter;
    private HashMap<String,String> params;
    private String urername,password;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        edit_name = findViewById(R.id.edit_name);
        edit_pwd = findViewById(R.id.edit_pwd);
        check_jz = findViewById(R.id.check_jz);
        text_reg = findViewById(R.id.text_reg);
        but_login = findViewById(R.id.but_login);
    }

    @Override
    protected void initData() {


        loginPresenter = new LoginPresenter();
        loginPresenter.setMview(this);

        sp = getSharedPreferences("cofig",MODE_PRIVATE);
        if (sp.getBoolean("isRem",true)){
            check_jz.setChecked(true);
            edit_name.setText(sp.getString("phone",""));
            edit_pwd.setText(sp.getString("pwd",""));
        }

        check_jz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    check_jz.setChecked(true);
                }
            }
        });


        but_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                params = new HashMap<>();
                params.put("phone",edit_name.getText().toString().trim());
                params.put("pwd",edit_pwd.getText().toString().trim());
                loginPresenter.getData(params);

                if (check_jz.isChecked()){
                    editor = sp.edit();
                    editor.putString("phone",edit_name.getText().toString().trim());
                    editor.putString("pwd",edit_pwd.getText().toString().trim());
                    editor.putBoolean("isRem",true);
                    editor.commit();
                }else {
                    editor = sp.edit();
                    editor.putString("phone","");
                    editor.putString("pwd","");
                    editor.putBoolean("isRem",false);
                    editor.commit();
                }

            }
        });

        text_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void success(LoginBean loginBean) {
        Toast.makeText(MainActivity.this,loginBean.message,Toast.LENGTH_SHORT).show();
        if (loginBean.message.equals("登录成功")){
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
            finish();
        }
        return;
    }

    @Override
    public void failure(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }
}
