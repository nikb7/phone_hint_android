package com.technikb.phone_hint_android

import android.app.PendingIntent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler

/** PhoneHintAndroidPlugin */
class PhoneHintAndroidMethodCallHandler(private val activity: ComponentActivity): MethodCallHandler {

  private lateinit var channelResult: MethodChannel.Result

  private val request: GetPhoneNumberHintIntentRequest =
    GetPhoneNumberHintIntentRequest.builder().build()


  private val phoneNumberHintIntentResultLauncher = activity.registerForActivityResult(
    ActivityResultContracts.StartIntentSenderForResult()
  ) { result ->
    try {
      val phoneNumber = Identity.getSignInClient(activity).getPhoneNumberFromIntent(result.data)
      channelResult.success(phoneNumber)
    } catch (e: Exception) {
      Log.e(TAG, "Phone Number Hint failed", e)
      channelResult.error("PHONE_HINT_FAILURE", "Failed to handle result", null)
    }
  }

  private fun requestPhoneHint() {
    Identity.getSignInClient(activity)
      .getPhoneNumberHintIntent(request)
      .addOnSuccessListener { result: PendingIntent ->
        try {
          phoneNumberHintIntentResultLauncher.launch(
            IntentSenderRequest.Builder(result).build()
          )
        } catch (e: Exception) {
          Log.e(TAG, "Launching the PendingIntent failed")
          channelResult.error("PHONE_HINT_FAILURE", "Launching the PendingIntent failed", null)
        }
      }
      .addOnFailureListener {
        Log.e(TAG, "Phone Number Hint failed ${it.message}")
        channelResult.error("PHONE_HINT_FAILURE", "Phone Number Hint failed ${it.message}", null)
      }
  }

  override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
    channelResult = result
    when (call.method) {
      "getPlatformVersion" -> {
        requestPhoneHint()
      }
      else -> {
        channelResult.notImplemented()
      }
    }
  }
  
  companion object {
    private const val TAG = "PhoneHintMethodChannel"
    fun register(flutterEngine: FlutterEngine, activity: ComponentActivity) {
      val handler = PhoneHintAndroidMethodCallHandler(activity)

      MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "phone_hint_android")
        .setMethodCallHandler(handler)
    }
  }

}
