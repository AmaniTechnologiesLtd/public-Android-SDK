package com.example.amani_sdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amani_ai.base.util.Amani

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Amani.getInstance().goToKycActivity(
            this,
            "ID",//ENTER TCIN NO HERE
            "EMAIl",//   ENTER  EMAIL ID HERE
            "PASSWORD"//ENTER PASSWORD HERE
        )

    }
}