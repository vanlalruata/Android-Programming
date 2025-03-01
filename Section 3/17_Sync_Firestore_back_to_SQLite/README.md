## Sync Firestore Updates Back to SQLite

* Concepts: ```Firestore Listeners```, ```SQLite Database```, ```Offline Sync```.
* Feature: Firestore real-time listeners update the local SQLite database automatically whenever changes occur in the cloud.

### Add Dependencies

Ensure that you have Firestore added in ```build.gradle (Module: app)```:

```
    dependencies {
        implementation 'com.google.firebase:firebase-firestore:24.8.1'
    }
```

Sync Gradle after adding dependencies.


