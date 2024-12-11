package com.technikb.phone_hint_android_example


import com.technikb.phone_hint_android.PhoneHintAndroidMethodCallHandler
import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.embedding.engine.FlutterEngine

class MainActivity : FlutterFragmentActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        PhoneHintAndroidMethodCallHandler.register(flutterEngine, this)
    }
}
