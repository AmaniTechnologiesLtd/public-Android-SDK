package com.example.amani_sdk;


import com.amani_ai.base.util.Amani;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Amani.init(this,"https://demo.amani.ai/api/v1/","/v1");

    }
}
