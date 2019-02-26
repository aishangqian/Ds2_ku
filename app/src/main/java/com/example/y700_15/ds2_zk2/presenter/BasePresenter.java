package com.example.y700_15.ds2_zk2.presenter;

public class BasePresenter<V> {
    private V mview;

    public V getMview() {
        return mview;
    }

    public void setMview(V mview) {
        this.mview = mview;
    }

    public void detachView(){
        this.mview = null;
    }
}
