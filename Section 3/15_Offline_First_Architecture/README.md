## Offline-First Architecture with SQLite & Firebase

* Concepts: ```SQLite Database```, ```Firebase Firestore```, ```Offline Sync```.
* Feature: Users can add and update student records offline, and data syncs with Firestore when connected to the internet.

### Add Dependencies

Add Firestore and WorkManager dependencies in ```build.gradle (Module: app)```:

```
    dependencies {
        implementation 'com.google.firebase:firebase-firestore:24.8.1'
        implementation 'androidx.work:work-runtime:2.8.1'
    }
```

Sync Gradle after adding dependencies.

### Enable Offline Support in Firestore
Modify your Application class (MyApp.java) to enable Firestore offline persistence.