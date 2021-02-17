# README #

This README would normally document whatever steps are necessary to get your application up and running.



## How do I get set up? ##

   * Dependencies:

   1. Add the following dependencies to your Module build.gradle file.
```groovy
implementation 'ai.amani.android:AmaniAi:1.0.53' 
```
### Example of usage: ###

```groovy
    dependencies { 
    
    implementation 'ai.amani.android:AmaniAi:1.0.53' // Add only this line
    
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
    
    dataBinding { enabled true } // Add only this line 
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
              "ENTER TCIN NO HERE"
              "ENTER  EMAIL ID HERE"
              "ENTER PASSWORD HERE"
           )
```
        
### Example of usage:

```java
    Amani.init(this); 
    
    Amani.getInstance().goToKycActivity(this,
        "ENTER TCIN NO HERE", 
        "ENTER  EMAIL ID HERE", 
        "ENTER PASSWORD HERE"
    );
```
## Call Back Function Usage ##

   * A function that allows you to see the result of the KYC process as completed / incomplete:  
```java
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            val verificationCompleted = Objects.requireNonNull(data)!!.getBooleanExtra("ON_SUCCESS", false)
            if (verificationCompleted) {
    
                //Do something!
            }
        }
    }
```    

