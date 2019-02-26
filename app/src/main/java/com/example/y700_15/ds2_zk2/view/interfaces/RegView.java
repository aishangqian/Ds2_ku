package com.example.y700_15.ds2_zk2.view.interfaces;

public interface RegView<T> extends BaseView {
    void success(T t);
    void failure(String msg);
}
