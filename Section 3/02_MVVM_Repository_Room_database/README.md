## MVVM with Repository & Room Database

* Concepts: ```Room Database```, ```Repository```, ```LiveData```, ```ViewModel```.
* Feature: A simple Notes App where users can add and delete notes, with data stored locally using Room.

### Add Dependencies

Add these dependencies in ```build.gradle (Module: app)```:

```
    dependencies {
        implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2'
        implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.2'
        implementation 'androidx.room:room-runtime:2.6.1'
        annotationProcessor 'androidx.room:room-compiler:2.6.1'
    }
```

Sync Gradle after adding dependencies.


### Create Room Database (Entities, DAO, Database)
1. Create ```Note.java``` (Entity)
2. Create ```NoteDao.java``` (Data Access Object)
3. Create ```NoteDatabase.java``` (Room Database)
4. Create Repository (```NoteRepository.java```)
5. Create ViewModel (```NoteViewModel.java```)