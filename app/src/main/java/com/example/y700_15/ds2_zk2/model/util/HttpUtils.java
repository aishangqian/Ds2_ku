package com.example.y700_15.ds2_zk2.model.util;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpUtils<T> {

    private OkHttpClient okHttpClient;

    public static HttpUtils getHttpUtils(){
        return HttpUtilsInstance.httpUtils;
    }

    private static class HttpUtilsInstance{
        public static HttpUtils httpUtils = new HttpUtils();
    }

    /**
     * get方式
     */
    public void doGet(String url, final Class<T> tClass, CallBackData callBackData){
        this.callBackData = callBackData;

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .writeTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder().url(url)
                .get().build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();

                T t = new Gson().fromJson(result,tClass);

                Message message = handler.obtainMessage();
                message.obj = t;
                handler.sendMessage(message);
            }
        });

    }


    /**
     * post方式
     */
    public void doPost(String url, HashMap<String,String> params, final Class<T> tClass, CallBackData callBackData){
        this.callBackData = callBackData;

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .writeTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();

        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String,String> map : params.entrySet()){
            builder.add(map.getKey(),map.getValue());
        }

        RequestBody requestBody = builder.build();

        Request request = new Request.Builder().url(url)
                .post(requestBody).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                T t = new Gson().fromJson(result,tClass);

                Message message = handler.obtainMessage();
                message.obj = t;
                handler.sendMessage(message);
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            T t = (T) msg.obj;
            callBackData.onResponse(t);
        }
    };

    private CallBackData callBackData;

    public interface CallBackData<D>{
        void onResponse(D d);
        void onFailure(String msg);
    }
}
