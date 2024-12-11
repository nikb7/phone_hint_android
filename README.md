# phone_hint_android

Android phone hint flutter plugin for getting phone number directly from the device.

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

### Support phone_hint_android

You can support phone_hint_android by liking it on Pub and staring it on Github, sharing ideas on how we can enhance a certain functionality or by reporting any problems you encounter.
