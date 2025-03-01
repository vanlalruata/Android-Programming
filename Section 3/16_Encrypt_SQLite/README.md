## Encrypt SQLite Database for Security

* Concepts: ```SQLCipher```, ```Database Encryption```, ```Secure Data Storage```.
* Feature: Encrypts the SQLite database so that even if someone extracts it from the device, they cannot read its contents without the decryption key.


### Add SQLCipher Dependency

Add SQLCipher for Android in ```build.gradle (Module: app)```:

```
    dependencies {
        implementation 'net.zetetic:android-database-sqlcipher:4.5.0'
    }
```

Sync Gradle after adding dependencies.


