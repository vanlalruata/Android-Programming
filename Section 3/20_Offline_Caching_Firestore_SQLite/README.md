## Offline Caching with Firestore & SQLite

* Concepts: ```Firestore Caching```, ```SQLite Database```, ```Offline Data Sync```.
* Feature: Firestore stores data offline and syncs with SQLite when online.


### Add Dependencies

Ensure that you have Firestore and Room for SQLite in ```build.gradle (Module: app)```:
```
    dependencies {
        implementation 'com.google.firebase:firebase-firestore:24.8.1'
        implementation 'androidx.room:room-runtime:2.6.1'
        annotationProcessor 'androidx.room:room-compiler:2.6.1'
    }
```

Sync Gradle after adding dependencies.

### Enable Firestore Offline Caching ```MyApp.java```