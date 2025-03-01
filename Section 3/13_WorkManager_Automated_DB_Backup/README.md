## Using WorkManager for Automated SQLite Database Backup

* Concepts: ```WorkManager```, ```SQLite Backup```, ```Background Tasks```.
* Feature: Automatically backs up SQLite database to Downloads folder every day.


### Add Dependencies

Add the WorkManager dependency in ```build.gradle (Module: app)```:

```
    dependencies {
        implementation 'androidx.work:work-runtime:2.8.1'
    }
```

Sync Gradle after adding dependencies.

