## Implement Biometric Authentication for Secure Database Access

* Concepts: ```Biometric Authentication```, ```AndroidX Biometric API```, ```SQLite Security```.
* Feature: Users must authenticate using biometrics before accessing the encrypted SQLite database.

### Add Dependencies

Add the AndroidX Biometric API in ```build.gradle (Module: app)```:
```
    dependencies {
        implementation 'androidx.biometric:biometric:1.2.0-alpha05'
    }
```
Sync Gradle after adding dependencies.

### Add Biometric Permission in ```AndroidManifest.xml```
```
    <uses-permission android:name="android.permission.USE_BIOMETRIC"/>
    <uses-feature android:name="android.hardware.fingerprint" android:required="false"/>
```
This ensures that biometric authentication is enabled for devices that support it.

