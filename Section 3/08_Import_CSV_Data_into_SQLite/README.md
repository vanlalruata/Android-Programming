## Import CSV Data into SQLite in Android

* Concepts: ```SQLite Database```, ```File Handling```, ```CSV Parsing```.
* Feature: Reads a CSV file from storage and inserts its data into an SQLite database.

### Add Permissions in AndroidManifest.xml

We need ```READ_EXTERNAL_STORAGE``` permission to read the CSV file.

```
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```

