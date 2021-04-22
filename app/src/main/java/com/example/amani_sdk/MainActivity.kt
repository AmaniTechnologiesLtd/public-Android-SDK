package com.example.amani_sdk

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amani_ai.base.util.Amani
import com.amani_ai.base.util.SessionManager
import java.util.*


class MainActivity : AppCompatActivity() {

    /**
     * Initialization of Amani SDK
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //"TOKEN" , "BIRTHDATE" and "DOCUMENT NO" is required for NFC Only step. They could be null.
        Amani.getInstance().goToKycActivity(this,"ID OF CUSTOMER","TOKEN","BIRTH DATE","EXPIRE DATE","DOCUMENT NO")
    }

    /**
     * CALL BACK FUNCTION
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Getting verification status true/false as boolean return.
        val verificationCompleted = Objects.requireNonNull(data)!!.getBooleanExtra("ON_SUCCESS", false)

        //Getting token status true/false as boolean return.
        val tokenExpired = Objects.requireNonNull(data)!!.getBooleanExtra("TOKEN_EXPIRED", false)

        //Getting list of KYC steps's information.
        var stepList: Map<String, String>? = null
        stepList = SessionManager.getRules()
        

        if (verificationCompleted) {
            //Do something!
        }

        if (tokenExpired) {
            //Do something!
        }

    }
}