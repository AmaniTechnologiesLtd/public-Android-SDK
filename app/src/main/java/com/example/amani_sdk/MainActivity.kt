package com.example.amani_sdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amani_ai.base.util.Amani
import com.amani_ai.base.util.Amani.Companion.instance


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance?.goToKycActivity(
                this,
                "ID",//ENTER TCIN NO HERE
               "EMAIl",//   ENTER  EMAIL ID HERE
               "PASSWORD"//ENTER PASSWORD HERE
        )
    }
}