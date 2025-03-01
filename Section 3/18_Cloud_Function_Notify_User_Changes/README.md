## Use Cloud Functions to Notify Users of Database Changes

* Concepts: ```Cloud Functions```, ```Firestore Triggers```, ```Firebase Cloud Messaging (FCM)```.
* Feature: Users receive real-time push notifications when data in Firestore changes.

### Set Up Firebase Cloud Messaging (FCM)

1. Go to Firebase Console
2. Select Your Project
3. Enable Firebase Cloud Messaging (FCM) under Project Settings > Cloud Messaging
4. Copy Your Server Key from Project Settings > Cloud Messaging > Cloud Messaging API (Legacy)

### Create Cloud Function in Firebase

You need Node.js and the Firebase CLI installed.

1. Install Firebase CLI (if not installed)
```
    npm install -g firebase-tools
```

2. Login to Firebase
```
    firebase login
```

3. Initialize Firebase Functions
```
firebase init functions
```
* Select Firestore Triggers
* Select Cloud Functions
* Choose JavaScript
* Enable ESLint (optional)

4. Navigate to functions/index.js and Replace It
5. Deploy the Function
```
    Deploy the Function
```

### Implement Firebase Cloud Messaging (FCM) in Android
Add Dependencies in ```build.gradle (Module: app)```
```
    dependencies {
        implementation 'com.google.firebase:firebase-messaging:23.2.1'
    }
```

Sync Gradle after adding dependencies.

### Modify AndroidManifest.xml to Handle Notifications
```
    <service
        android:name=".MyFirebaseMessagingService"
        android:exported="false">
        <intent-filter>
            <action android:name="com.google.firebase.MESSAGING_EVENT"/>
        </intent-filter>
    </service>

    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>
```
This allows the app to receive push notifications.

