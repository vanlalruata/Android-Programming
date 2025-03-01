## Backup & Restore SQLite Database in Android

* Concepts: ```File Handling```, ```SQLite Database```, ```Backup & Restore```.
* Feature: Allows users to export (backup) SQLite database as a file and import (restore) it when needed.

### Add Permissions in AndroidManifest.xml

We need ```WRITE_EXTERNAL_STORAGE``` and ```READ_EXTERNAL_STORAGE``` permissions.

```
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```

