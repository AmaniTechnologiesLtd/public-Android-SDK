# README #

This README would normally document whatever steps are necessary to get your application up and running.



## How do I get set up? ##

   * Dependencies:

   1. Add the following dependencies to your Module build.gradle file.

  ```    implementation 'ai.amani.android:AmaniAi:1.0.2' ```
  
### Example of usage: ###

    ```
    dependencies { 
    ...
    implementation 'ai.amani.android:AmaniAi:1.0.2' // Add only this line
    
                }  
    ```

   2. Enable DataBinding in the Module build.gradle by adding that line into code block of android {}:
   
   
``` dataBinding { enabled true  } ```

### Example of usage ###
     
```
android { 
        ...

dataBinding { enabled true } // Add only this line 
        ...
        }
```

  3. Add following codes in the Project build.gradle within in the buildscript->repositories and buildscript->allprojects.
  
  ```maven { url "https://jfrog.amani.ai/artifactory/amani-sdk"}```
  
### Example of usage:
  
       ```     
            allprojects {
            
        repositories {
            google()
            jcenter()
            
         maven {
             url "https://jfrog.amani.ai/artifactory/amani-sdk"
             }
    
            }
        }
       
4. Go to AndroidManifest.xml, add these lines into your application!
 
```
android:name=".Application"
tools:replace="android:theme" 
```


### Example of usage:
 
 ```
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
    
        Amani.init(this)


   * To Proceed for KYC Verification :
 ```
     Amani.getInstance().goToKycActivity(
                this,
               "ENTER TCIN NO HERE"
               "ENTER  EMAIL ID HERE"
               "ENTER PASSWORD HERE"
            )
```
### Example of usage:

    ```
    Amani.init(this); 
    
    Amani.getInstance().goToKycActivity(this,
        "TC No", 
        "demo@amani.ai", 
        "123456"
    );
    ```  

