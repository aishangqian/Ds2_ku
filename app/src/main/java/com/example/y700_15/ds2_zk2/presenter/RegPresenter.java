package com.example.y700_15.ds2_zk2.presenter;

import com.example.y700_15.ds2_zk2.model.api.UserApi;
import com.example.y700_15.ds2_zk2.model.bean.RegBean;
import com.example.y700_15.ds2_zk2.model.util.HttpUtils;
import com.example.y700_15.ds2_zk2.view.interfaces.RegView;

import java.util.HashMap;

public class RegPresenter extends BasePresenter<RegView<RegBean>> {
    private HttpUtils httpUtils;

    public RegPresenter(){
        httpUtils = HttpUtils.getHttpUtils();
    }

    public void getData(HashMap<String,String> params){
        httpUtils.doPost(UserApi.REG_URL, params, RegBean.class, new HttpUtils.CallBackData<RegBean>() {
            @Override
            public void onResponse(RegBean regBean) {
                getMview().success(regBean);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
