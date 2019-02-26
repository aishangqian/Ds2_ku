package com.example.y700_15.ds2_zk2.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.y700_15.ds2_zk2.R;
import com.example.y700_15.ds2_zk2.model.bean.RegBean;
import com.example.y700_15.ds2_zk2.presenter.RegPresenter;
import com.example.y700_15.ds2_zk2.view.interfaces.RegView;

import java.util.HashMap;

public class RegActivity extends AppCompatActivity implements RegView<RegBean> {
    private EditText edit_names,edit_pwds;
    private Button button;
    private TextView textView;

    private RegPresenter regPresenter;
    private HashMap<String,String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        initView();
        initData();
    }

    private void initData() {

        regPresenter = new RegPresenter();
        regPresenter.setMview(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                params = new HashMap<>();
                params.put("phone",edit_names.getText().toString().trim());
                params.put("pwd",edit_pwds.getText().toString().trim());
                regPresenter.getData(params);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initView() {
        edit_names = findViewById(R.id.edit_names);
        edit_pwds = findViewById(R.id.edit_pwds);
        button = findViewById(R.id.but_reg);
        textView = findViewById(R.id.text_login);
    }

    @Override
    public void success(RegBean regBean) {
        Toast.makeText(this,regBean.message,Toast.LENGTH_SHORT).show();
        if (regBean.message.equals("注册成功")){
            Intent intent = new Intent(RegActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void failure(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
