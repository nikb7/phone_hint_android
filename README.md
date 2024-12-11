# phone_hint_android

Flutter plugin for Phone Number Hint Api on Android, provides a frictionless way to show a user's (SIM-based) phone numbers as a hint.

![demo](https://github.com/user-attachments/assets/c6c132eb-85e5-4870-8f1b-9a57ee5a4d3a)

## Setup & Usage

 ```kotlin
import com.technikb.phone_hint_android.PhoneHintAndroidMethodCallHandler
import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.embedding.engine.FlutterEngine

class MainActivity : FlutterFragmentActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        PhoneHintAndroidMethodCallHandler.register(flutterEngine, this)
    }
}
```
1. Replace **FlutterActivity** with **FlutterFragmentActivity**
2. Add **PhoneHintAndroidMethodCallHandler.register(flutterEngine, this)** in _configureFlutterEngine_ method

Example usage:
```dart
final phoneNumber = await PhoneHintAndroid().getPhoneNumber();
```

### Benefits
- No additional permission requests are needed
- Eliminates the need for the user to manually type in the phone number
- No Google account is needed
- Not directly tied to sign in/up workflows
- Wider support for Android versions compared to Autofill

Utilised Google's [Phone Number Hint Api](https://developers.google.com/identity/smartlock-passwords/android/overview)


### Support phone_hint_android

You can support phone_hint_android by liking it on Pub and staring it on Github, sharing ideas on how we can enhance a certain functionality or by reporting any problems you encounter.
