# phone_hint_android

Android phone hint flutter plugin for getting phone number directly from the device.



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

### Support phone_hint_android

You can support phone_hint_android by liking it on Pub and staring it on Github, sharing ideas on how we can enhance a certain functionality or by reporting any problems you encounter.