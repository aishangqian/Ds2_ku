package com.example.y700_15.ds2_zk2.presenter;

import android.widget.Toast;

import com.example.y700_15.ds2_zk2.model.api.UserApi;
import com.example.y700_15.ds2_zk2.model.bean.LoginBean;
import com.example.y700_15.ds2_zk2.model.util.HttpUtils;
import com.example.y700_15.ds2_zk2.view.interfaces.LoginView;

import java.util.HashMap;

public class LoginPresenter extends BasePresenter<LoginView<LoginBean>> {
    private HttpUtils httpUtils;

    public LoginPresenter(){
        httpUtils = HttpUtils.getHttpUtils();
    }


    public void getData(final HashMap<String,String> params){

//        String phone = params.get("phone");
//        String pwd = params.get("pwd");
//
//        if(phone.equals("")||pwd.equals("")){
//            if (getMview()!=null){
//                getMview().failure("账号和密码不可为空");
//            }
//        }

        httpUtils.doPost(UserApi.LOGIN_URL, params, LoginBean.class, new HttpUtils.CallBackData<LoginBean>() {


            @Override
            public void onResponse(LoginBean loginBean) {
                getMview().success(loginBean);
            }

            @Override
            public void onFailure(String msg) {

            }
        });

    }

}
