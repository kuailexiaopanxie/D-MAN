package com.wdkg.man.config;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @Author 樊凯
 * @Date 2024/10/15
 **/
public class OkHttpClientSingleton {

    private static OkHttpClient instance;

    private OkHttpClientSingleton() {
    }

    public static OkHttpClient getInstance() {
        if (instance == null) {
            synchronized (OkHttpClientSingleton.class) {
                if (instance == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();

                    // 设置连接超时
                    builder.connectTimeout(60, TimeUnit.SECONDS);
                    // 设置读取超时
                    builder.readTimeout(60, TimeUnit.SECONDS);
                    // 设置写入超时
                    builder.writeTimeout(60, TimeUnit.SECONDS);

                    // 构建 OkHttpClient 实例
                    instance = builder.build();
                }
            }
        }
        return instance;
    }
}
