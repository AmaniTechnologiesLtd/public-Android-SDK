package com.example.amani_sdk

import android.app.Application
import com.amani_ai.base.util.Amani
import com.example.amani_sdk.CredProperties

class Application : Application() {
    /**
     * Initialization of Amani SDK
     */
    override fun onCreate() {
        super.onCreate()
        /**
         * "server_url_" is must located like that.
         * Example: "https://server_url_/api/v1/"
         */
        Amani.init(this, CredProperties.SERVER_URL, CredProperties.SHARED_SECRET)
    }
}