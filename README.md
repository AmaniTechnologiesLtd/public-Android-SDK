# Amani SDK #

This README would normally document whatever steps are necessary to get your application up and running.



## How do I get set up? ##

   * Dependencies:

   1. Add the following dependencies to your Module build.gradle file.
```groovy
implementation 'ai.amani.android:AmaniAi:1.2.52'
```
### Example of usage: ###

```groovy
    dependencies { 
    
    implementation 'ai.amani.android:AmaniAi:1.2.52' // Add only this line
    
                }  
```

   2. Enable DataBinding in the Module build.gradle by adding this line into code block of android {}:
   
```groovy
dataBinding { enabled true  } 
```
### Example of usage ###
     
```groovy
android { 
            ...
    
    dataBinding { enabled true } // Add this line to enable data binding feature.
            ...
            }
```

  3. Add the following in the Project build.gradle within in buildscript within the buildscript->repositories and buildscript->allprojects.
```gradle  
    maven { url "https://jfrog.amani.ai/artifactory/amani-sdk"}
```
### Example of usage:
  
```groovy
allprojects {
            
        repositories {
            google()
            jcenter()
            
         maven {
             url "https://jfrog.amani.ai/artifactory/amani-sdk"
             }
    
            }
        }
```
4. Go to AndroidManifest.xml, apply these lines!
 
```xml
        android:name=".Application"
        tools:replace="android:theme" 
```


### Example of usage:
 
```xml
     <application
            ...
            
            android:name=".Application"
            tools:replace="android:theme">
             
            ...
        </application>
```

## Initialization ##
 
 * In the Application Class Initialize the SDk: 
 
```java   
    Amani.init(this,"server","SHARED_SECRET"); // WARNING! It must be called at least once before other functions are called.   
                                               // If you want to disable Signature, leave SHARED_SECRET as empty.
                                               // SHARED_SECRET will be provided from Amani if needed.
```

 * To Proceed for KYC Verification : 
        
        
```java    
    Amani.goToKycActivity(
               this,
              "ENTER TCIN NO HERE", //(Can not be null or empty)
              "ENTER  TOKEN HERE", //(Can not be null or empty)
              "ENTER BIRTH DATE HERE",  // YYMMDD format. (For Example: 20 May 1990 is 900520). (Can be empty if NFC is not used.)
              "ENTER EXPIRE DATE HERE", // YYMMDD format.(Can be empty if NFC is not used.)
              "ENTER DOCUMENT NO HERE",  // Document number of your ID card. (Can be empty if NFC is not used.)
              "ENTER GEOLOCATION PERMISSION", // Boolean value of your geoLocation value(true/false). (Default value: true)
              "ENTER LANGUAGE", // Language of your current application. (tr,en,zh etc.) (Default value: "tr")
              "ENTER USER's EMAIL", // Must be in EMAIL format! (@Nullable or empty if not needed.)
              "ENTER USER's PHONE NUMBER", // Phone number of user. (@Nullable or empty if not needed.)
              "ENTER FULL NAME OF USER" // Full name of user. (@Nullable or empty if not needed.)
    
           );
```
        
### Example of usage:

```java
    Amani.init(this,"server"); 
    
      Amani.goToKycActivity(
               this,
              "ENTER TCIN NO HERE", //(Can not be null or empty)
              "ENTER  TOKEN HERE",  //(Can not be null or empty)
              "810519",  // YYMMDD format. (Example: 19 May 1981 -> 810519 ). (Can be empty if NFC is not used.)
              "290120", // YYMMDD format. (Example: 20 Jan 2029 -> 290120 )(Can be empty if NFC is not used.)
              "ENTER DOCUMENT NO HERE",  // Document number of your ID card. (Can be empty if NFC is not used.)
              true, // Boolean value of your geoLocation value(true/false). (Default value: true)
              "tr", // Language of your current application. (tr,en,zh etc.) (Default value: "tr")
              "email@gmail.com", // Must be in EMAIL format! (@Nullable or empty if not needed.)
              "0(542) xxx xx xx", // Phone number of user. (@Nullable or empty if not needed.)
              "Name Surname" // Full name of user. (@Nullable or empty if not needed.)
    
           );
```
## Call Back Function Usage ##

   * A function that allows you to access the following information after the user exits from the application.;
   
   1. Result of the KYC process as completed / incomplete 
   2. Token is expired as true/false
   3. List of the KYC step's information.
   
```java
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
           super.onActivityResult(requestCode, resultCode, data)
   
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
              //...                //HTPS Error: apiException
                  //...            //HTPS Error: apiException
                      //...        //HTPS Error: apiException
   
       }
```    
## Event-Fire Callback ##
    
    Event fire mechanism returns you as a simple callback. This callback contains string value of; EventType, EventName, EventParams. 

### Event Types ###
We are currently supporting two types of event.
  * ButtonPressed
  * Upload
  
### Event Names ###
We are currently supporting different kind of names. 

  * ManualCrop
  * Upload  
  * TryAgain
  * TakePhoto
  * OpenCameraButton
  * ClearButton
  * UploadButton
  * DownloadButton
  
  ### Event Params ###
We are currently supporting different kind of params. EventParams might be response of upload as string ("OK","ERROR"), "BackSide", "FrontSide" of documents.

### Event Fire CallBack Function Sample Usage ###

```java
     
        EventFire eventFire = new EventFire();
        eventFire.setEventListener((type, name, parameter) ->
                Log.d("TAG", "onEvent: "
                        + "\ntype:" + type // EventType ()
                        + "\nname:" + name // Amani Event Name. If there is more than one document, it returns the type of that document group. If there is a document, it returns the type of the document (the document type returns to you according to Amani standards).
                        + "\nparams " + parameter // Parameter returns response of upload and doccument type, or action names(If action names performed).
                        )
        );
    
   ```   


    
    
    

## ProGuard Rule Usage ##
    
   * If you are using ProGuard in your application, you just need to add this line into your ProGuard Rules!
   
   ```java
-keep class com.amani_ml** {*;}
-dontwarn com.amani.ml**
-keep class datamanager.** {*;}
-dontwarn datamanager.**
-keep class networkmanager.** {*;}
-dontwarn networkmanager.**
-keep class com.amani_ai.jniLibrary.CroppedResult { *; }

-keep class org.jmrtd.** { *; }
-keep class net.sf.scuba.** {*;}
-keep class org.bouncycastle.** {*;}
-keep class org.spongycastle.** {*;}
-keep class org.ejbca.** {*;}

-dontwarn org.ejbca.**
-dontwarn org.bouncycastle.**
-dontwarn org.spongycastle.**
-dontwarn org.jmrtd.**
-dontwarn net.sf.scuba.**

-keep class org.tensorflow.lite**{ *; }
-dontwarn org.tensorflow.lite.**
-keep class org.tensorflow.lite.support**{ *; }
-dontwarn org.tensorflow.lite.support**
   ```     

