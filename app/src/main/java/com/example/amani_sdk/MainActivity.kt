package com.example.amani_sdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //to move for Kyc verification

       /* Amani.getInstance().goToKycActivity(
                this,
                "ENTER TCIN NO HERE",
               "ENTER  EMAIL ID HERE",
               "ENTER PASSWORD HERE"
        )*/
    }
}