## MVVM with Retrofit API Integration

* Concepts: ```Retrofit```, ```LiveData```, ```ViewModel```, ```RecyclerView```.
* Feature: Fetches posts from a REST API and displays them in a RecyclerView dynamically.

### Add Dependencies

Add these dependencies in ```build.gradle (Module: app)```:

```
    dependencies {
        implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2'
        implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.2'
        implementation 'androidx.recyclerview:recyclerview:1.2.1'
        implementation 'com.squareup.retrofit2:retrofit:2.9.0'
        implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
        implementation 'com.google.code.gson:gson:2.8.9'
    }
```

Sync Gradle after adding dependencies.


