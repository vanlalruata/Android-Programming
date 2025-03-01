## Sync SQLite with Firebase Firestore

* Concepts: ```SQLite Database```, ```Firebase Firestore```, ```Data Synchronization```.
* Feature: Saves student records to SQLite and syncs them to Firestore automatically.

### Add Dependencies

Add Firestore dependencies in ```build.gradle (Module: app)```:

```
    dependencies {
        implementation 'com.google.firebase:firebase-firestore:24.8.1'
    }
```

Sync Gradle after adding dependencies.

### Configure Firebase

1. Go to Firebase Console
2. Create a new project
3. Enable Firestore Database
4. Set Firestore Rules to allow public access (for testing):

```
    rules_version = '2';
    service cloud.firestore {
    match /databases/{database}/documents {
        match /students/{document=**} {
        allow read, write: if true;
        }
    }
    }
```

5. Download google-services.json and place it in app/ folder.

