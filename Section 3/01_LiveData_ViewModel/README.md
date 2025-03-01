## LiveData with ViewModel (MVVM Architecture)

* Concepts: ```LiveData```, ```ViewModel```, ```Data Binding```, ```MVVM Architecture```.
* Feature: Implements a counter app where UI updates dynamically without lifecycle issues.

### Add Dependencies

Ensure the following dependencies are in ```build.gradle (Module: app)```:

```
    dependencies {
        implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2'
        implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.2'
    }
```

Sync Gradle after adding dependencies.


### Features & How It Works

* ViewModel preserves data across config changes (e.g., screen rotation).
* LiveData updates UI automatically when data changes.
* No memory leaks—LiveData removes observers when activity is destroyed.
* Proper separation of concerns with MVVM architecture.