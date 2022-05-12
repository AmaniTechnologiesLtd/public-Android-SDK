package com.example.amani_sdk

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        //"TOKEN" , "BIRTHDATE" and "DOCUMENT NO" is required for NFC Only step. They could be null. geoLocation is up to you!
        Amani.goToKycActivity(this, "ID OF CUSTOMER", "TOKEN", "YYMMDD", "YYMMDD","DOCUMENT NO",true,"tr")
    }

    /**
     * CALL BACK FUNCTION
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(101, 101, data)

        //Getting verification status true/false as boolean return.
        val verificationCompleted = Objects.requireNonNull(data)!!.getBooleanExtra("ON_SUCCESS", false)

        //Getting token status true/false as boolean return.
        val tokenExpired = Objects.requireNonNull(data)!!.getBooleanExtra("TOKEN_EXPIRED", false)

        //Getting network connection true/false
        val networkError = Objects.requireNonNull(data)!!.getBooleanExtra("ON_NETWORK_ERROR", false)

        //Getting HTTPS's error codes if there is any API exception.
        val apiException = Objects.requireNonNull(data)!!.getIntExtra("ON_API_EXCEPTION", 10000)

        //Getting list of KYC steps's information.
        try {
            var stepList: Map<String, String>? = null
            stepList = SessionManager.getRules()
        }catch (e: Exception) {
            Log.d("TAG", "onActivityResult: stepList is null")}


        if (verificationCompleted) {
            //Do something!
        }

        if (tokenExpired) {
            //Your token is expired.
        }

        if (networkError){
            // Check your internet connection, please.
        }

        if (apiException == 401) // HTTPS Error: 401
        {
            //...                //HTPS Error: apiException
            //...            //HTPS Error: apiException
            //...        //HTPS Error: apiException
        }
    }
}
