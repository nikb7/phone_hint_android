import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'phone_hint_android_platform_interface.dart';

/// An implementation of [PhoneHintAndroidPlatform] that uses method channels.
class MethodChannelPhoneHintAndroid extends PhoneHintAndroidPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('com.technikb.phone_hint_android');

  @override
  Future<String?> getPhoneNumber() async {
    final version = await methodChannel.invokeMethod<String>('getPhoneNumber');
    return version;
  }
}
