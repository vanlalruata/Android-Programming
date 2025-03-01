## Google Maps API Integration

* Concepts: ```Google Maps API```, ```SupportMapFragment```.
* Feature: Displays a Google Map with a marker.

### Get Google Maps API Key

    * Go to ```Google Cloud Console```.
    * Enable the Maps SDK for Android.
    * Generate an API Key and add it to ```AndroidManifest.xml```

### Add Dependencies

```
    dependencies {
        implementation 'com.google.android.gms:play-services-maps:18.1.0'
    }
```

Sync Gradle after adding dependencies.

