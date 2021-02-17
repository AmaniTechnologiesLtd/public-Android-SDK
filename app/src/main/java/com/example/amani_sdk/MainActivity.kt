package com.example.amani_sdk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amani_ai.base.util.Amani
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Amani.getInstance().goToKycActivity(
                this,
                "ID",
                "EMAIL",
                "PASSWORD")

    }


    /**
     * callBack Function ( IF VERIFICATION IS COMPLETED/INCOMPLETED. )
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val verificationCompleted = Objects.requireNonNull(data)!!.getBooleanExtra("ON_SUCCESS", false)
        if (verificationCompleted) {

            //Do something!
        }
    }
}