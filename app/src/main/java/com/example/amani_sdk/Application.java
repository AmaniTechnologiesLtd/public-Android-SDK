package com.example.amani_sdk;


import com.amani_ai.base.util.Amani;

public class Application extends android.app.Application {


    /**
     * Initialization of Amani SDK
     */

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * "server_url_" is must located like that.
         */
        Amani.init(this,"https://server_url_/api/v1/","/v1");

    }
}
