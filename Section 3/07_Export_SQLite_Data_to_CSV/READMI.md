## Export SQLite Data to CSV in Android

* Concepts: ```SQLite Database```, ```File Handling```, ```CSV Export```.
* Feature: Exports Student Database to a CSV file stored in external storage.


### Add Permissions in ```AndroidManifest.xml```

We need ```WRITE_EXTERNAL_STORAGE``` permission to save the CSV file.

```
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```


