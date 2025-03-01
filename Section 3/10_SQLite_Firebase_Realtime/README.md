## Sync SQLite with Firebase Realtime Database

* Concepts: ```SQLite Database```, ```Firebase Realtime Database```, ```Data Synchronization```.
* Feature: Adds students to SQLite and syncs them to Firebase automatically.

### Add Dependencies

Add Firebase Realtime Database dependency in ```build.gradle (Module: app)```:

```
    dependencies {
        implementation 'com.google.firebase:firebase-database:20.2.2'
    }
```

Sync Gradle after adding dependencies.

### Configure Firebase

1. Go to Firebase Console
2. Create a new project
3. Enable Realtime Database in Rules:

```
    {
        "rules": {
            ".read": "auth != null",
            ".write": "auth != null"
        }
    }
```

4. Download ```google-services.json``` and place it in app/ folder.