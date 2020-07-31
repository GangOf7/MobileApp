&nbsp;&nbsp;
<img style="align-content: left;" src="https://github.com/GangOf7/MobileApp/blob/master/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png">

## About
Black Pearl provides with a 360-view of the status of every installed device displays statistical data for monitoring and research purpose. It enables user to monitor device(s) in the remote loaction, showcasing the interactive overall status of all devices while options to select devices on the basis of nature. It also represents the individual device performance in form of graphical representations. 

## Youtube Video
https://youtu.be/CSEZSGFQ9V4

## Technology
&nbsp;&nbsp;<img src="https://img.icons8.com/android.png"/>  Android Studio <br>
&nbsp;&nbsp;<img src="https://img.icons8.com/color/15/000000/java-coffee-cup-logo.png"/>  Java <br>

## Implement Pre-requisites

Add following dependencies in app.gradle of the project
  ```java
  compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.0.2'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
    implementation 'com.android.support:recyclerview-v7:21.0.0-rc1'
    implementation 'com.android.support:support-v4:19.1.0'
}
```

## Permissions:-

Full Network Access.
View Network Connections.
Read and write access to external storage.

The network access permissions are made use of for downloading content. The external storage permission is used to cache article images for viewing offline. 


 






