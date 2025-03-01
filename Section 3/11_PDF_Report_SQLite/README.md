## Generate PDF Report from SQLite Data in Android

* Concepts: ```SQLite Database```, ```PDF Generation```, ```File Handling```.
* Feature: Fetches student data from SQLite and creates a downloadable PDF report.


### Add Dependencies

Add the iText PDF library in ```build.gradle (Module: app)```:

```
    dependencies {
        implementation 'com.itextpdf:itext7-core:7.2.2'
    }
```

Sync Gradle after adding dependencies.

### Add Storage Permission in ```AndroidManifest.xml```

```
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```

