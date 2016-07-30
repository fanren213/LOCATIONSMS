#README
Bonan Cao
Nov. 21. 2015

This directory holds the the Android project for Mini 2 Assignment 4 of 18-641, the class diagram and the outputs of project. This is an app used for checking current position and sending it to a fixed number. The details are as follows.

There are two packages in the project:
UI: the UI of the project including the main activiti;
Util: the operation of getting current position.

For the permission of sending sms and getting current position, the androidmanifest is set, and the targetSdkVersion in the gradle of app has to be lower than 23.

Test:
Import the project into the Android Studio and use the emulator to run the LOCATIONSMS. Set the latitude and longitude in android device monitor in the emulator control tab. Click the submit button and the latitude and longitude will be displayed and sent to a number. And a toast message will appear. If the permission is denied or other exception is thrown, a toast message about it will be thrown and the exception will be stored.

Test outputs:
test_output.png: The result of clicking the button.

The file list:
        ▾ LOCATIONSMS/
          ▾ app/
            ▸ build/
            ▸ libs/
            ▾ src/
              ▸ androidTest/
              ▾ main/
                ▾ java/
                  ▾ bonanc/
                    ▾ locationsms/
                      ▾ ui/
                          MainActivity.java
                      ▾ util/
                          FindLocation.java
                ▾ res/
                  ▸ drawable/
                  ▾ layout/
                      activity_main.xml
                  ▸ menu/
                  ▸ mipmap-hdpi/
                  ▸ mipmap-mdpi/
                  ▸ mipmap-xhdpi/
                  ▸ mipmap-xxhdpi/
                  ▸ values-v21/
                  ▸ values-w820dp/
                  ▸ values/
                  AndroidManifest.xml
              app.iml
              build.gradle
              proguard-rules.pro
          ▸ build/
          ▸ gradle/
            build.gradle
            gradle.properties
            gradlew*
            gradlew.bat
            local.properties
            LOCATIONSMS.iml
            settings.gradle
          class_diagram.png
          readme.txt
          test_output.png
