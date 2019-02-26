package com.example.y700_15.ds2_zk2.model.bean;

public class LoginBean {
    public String message;
    public String status;
    public ResultBean result;

    public class ResultBean{
        public String headPic;
        public String nickName;
        public String phone;
        public String sessionId;
        public String sex;
        public String userId;
    }
}
