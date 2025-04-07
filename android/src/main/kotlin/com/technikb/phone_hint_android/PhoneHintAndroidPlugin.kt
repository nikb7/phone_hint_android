package com.technikb.phone_hint_android

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.PluginRegistry

/** PhoneHintAndroidPlugin */
class PhoneHintAndroidPlugin : FlutterPlugin,
    MethodCallHandler,
    ActivityAware,
    PluginRegistry.ActivityResultListener {

    private var channelResult: MethodChannel.Result? = null
    private var channel: MethodChannel? = null
    private var activity: Activity? = null
    private var bindingReference: ActivityPluginBinding? = null

    private val request: GetPhoneNumberHintIntentRequest =
        GetPhoneNumberHintIntentRequest.builder().build()


    private fun requestPhoneHint() {
        if (activity == null) {
            channelResult?.error(
                "ACTIVITY_REF_NOT_FOUND",
                "Activity not initialised",
                null,
            )
            return
        }
        Identity.getSignInClient(activity!!)
            .getPhoneNumberHintIntent(request)
            .addOnSuccessListener { result: PendingIntent ->
                try {
                    val intentSenderReq = IntentSenderRequest.Builder(result).build()
                    activity?.startIntentSenderForResult(
                        intentSenderReq.intentSender,
                        PHONE_REQUEST,
                        null,
                        intentSenderReq.flagsMask,
                        intentSenderReq.flagsValues,
                        0,
                    )

                } catch (e: Exception) {
                    Log.e(TAG, "Launching the PendingIntent failed")
                    channelResult?.error(
                        "PHONE_HINT_FAILURE",
                        "Launching the PendingIntent failed",
                        null
                    )
                }
            }
            .addOnFailureListener {
                Log.e(TAG, "Phone Number Hint failed ${it.message}")
                channelResult?.error(
                    "PHONE_HINT_FAILURE",
                    "Phone Number Hint failed ${it.message}",
                    null
                )
            }
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        channelResult = result
        when (call.method) {
            "getPhoneNumber" -> {
                requestPhoneHint()
            }

            else -> {
                channelResult?.notImplemented()
            }
        }
    }

    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(
            binding.binaryMessenger,
            "com.technikb.phone_hint_android"
        )
        channel?.setMethodCallHandler(this)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel?.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        attachToActivity(binding)
    }

    override fun onDetachedFromActivityForConfigChanges() {
        detachFromActivity()
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        attachToActivity(binding)
    }

    override fun onDetachedFromActivity() {
        detachFromActivity()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        when (requestCode) {
            PHONE_REQUEST -> {
                if (data != null && resultCode == Activity.RESULT_OK && activity != null) {
                    val phoneNumber =
                        Identity.getSignInClient(activity!!).getPhoneNumberFromIntent(data)
                    channelResult?.success(phoneNumber)
                } else {
                    channelResult?.error(
                        "PHONE_HINT_FAILURE",
                        "User dismissed phone hint",
                        null,
                    )
                }
            }
        }
        return true
    }

    private fun attachToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
        bindingReference = binding
        binding.addActivityResultListener(this)
    }

    private fun detachFromActivity() {
        bindingReference?.removeActivityResultListener(this)
        bindingReference = null
        activity = null
    }

    companion object {
        private const val TAG = "PhoneHintMethodChannel"
        private const val PHONE_REQUEST = 11101
    }

}
