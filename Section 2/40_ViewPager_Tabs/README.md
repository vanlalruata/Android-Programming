## ViewPager with Tabs

* Concepts: ```TabLayout```, ```ViewPager2```, ```FragmentStateAdapter```.
* Feature: Implements a swipeable interface with multiple tabs.

### Add Dependencies

Ensure these dependencies are in your ```build.gradle (Module: app)```:

```
    dependencies {
        implementation 'androidx.viewpager2:viewpager2:1.0.0'
        implementation 'com.google.android.material:material:1.10.0'
    }
```

Sync Gradle after adding dependencies.

### Features & How It Works

* ✅ Swipe left/right to navigate between tabs.
* ✅ Click on tabs to switch between pages.
* ✅ Modern ViewPager2 with FragmentStateAdapter (optimized).