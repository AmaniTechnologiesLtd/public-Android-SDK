# Amani SDK #

This README would normally document whatever steps are necessary to get your application up and running.



## How do I get set up? ##

   * Dependencies:

   1. Add the following dependencies to your Module build.gradle file.
```groovy
implementation 'ai.amani.android:AmaniAi:1.2.0' 
```
### Example of usage: ###

```groovy
    dependencies { 
    
    implementation 'ai.amani.android:AmaniAi:1.2.0' // Add only this line
    
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
            android:theme="@style/Theme.AmaniSDk"
            tools:replace="android:theme">
             
            ...
        </application>
```

## Initialization ##
 
 * In the Application Class Initialize the SDk:    
 
```java   
    Amani.init(this,"server","version");
```

 * To Proceed for KYC Verification : 
        
        
```java    
    Amani.getInstance().goToKycActivity(
               this,
              "ENTER TCIN NO HERE",
              "ENTER  TOKEN HERE",
              "ENTER BIRTH DATE HERE",  // YYMMDD format. (For Example: 20 May 1990 is 900520).
              "ENTER EXPIRE DATE HERE", // YYMMDD format.
              "ENTER DOCUMENT NO HERE",  // Document number of your ID card.
              "ENTER GEOLOCATION PERMISSION", // Boolean value of your geoLocation value. (true/false)
              "ENTER LANGUAGE" // Language of your current application. (tr,en,zh etc.)   
    
           )
```
        
### Example of usage:

```java
    Amani.init(this,"server","version"); 
    
    Amani.getInstance().goToKycActivity(this,
        "ENTER TCIN NO HERE", 
        "ENTER  TOKEN HERE",
        "ENTER BIRTH DATE HERE",  //YYMMDD format. (For Example: 20 May 1990 is 900520).
        "ENTER EXPIRE DATE HERE", //YYMMDD format.
        "ENTER DOCUMENT NO HERE". //Document number of your ID card.
        "ENTER GEOLOCATION PERMISSION", // Boolean value of your geoLocation value. (true/false)
        "ENTER LANGUAGE" // Language of your current application. (tr,en,zh etc.) 
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
   
           //Getting list of KYC steps's information.
           var stepList: Map<String, String>? = null
           stepList = SessionManager.getRules()
   
   
           for (i in 0 until stepList.size) {
               Toast.makeText(this, stepList.toString(), Toast.LENGTH_LONG).show() // As an example of KYC step list.
           }
   
           if (verificationCompleted) // Verification is completed!
           else // Verification incompleted!     

           if (tokenExpired) {
               //Do something!
           }
   
       }
```    

## ProGuard Rule Usage ##
    
   * If you are using ProGuard in your application, you just need to add this line into your ProGuard Rules!
   
   ```java
    -keep class com.amani_ai.jniLibrary.CroppedResult { *; }
   ```     

