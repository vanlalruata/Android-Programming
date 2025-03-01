## Coroutines & Flow with MVVM (API Integration)

* Concepts: ```Kotlin Coroutines```, ```Flow```, ```Retrofit```, ```MVVM```.
* Feature: Fetches posts from a REST API asynchronously using Coroutines & Flow.

### Add Dependencies

Add these dependencies to ```build.gradle (Module: app)```:

```
    dependencies {
        implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2'
        implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.2'
        implementation 'androidx.recyclerview:recyclerview:1.2.1'
        implementation 'com.squareup.retrofit2:retrofit:2.9.0'
        implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
        implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4'
        implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
    }
```

Sync Gradle after adding dependencies.

