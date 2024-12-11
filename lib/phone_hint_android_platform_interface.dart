import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'phone_hint_android_method_channel.dart';

abstract class PhoneHintAndroidPlatform extends PlatformInterface {
  /// Constructs a PhoneHintAndroidPlatform.
  PhoneHintAndroidPlatform() : super(token: _token);

  static final Object _token = Object();

  static PhoneHintAndroidPlatform _instance = MethodChannelPhoneHintAndroid();

  /// The default instance of [PhoneHintAndroidPlatform] to use.
  ///
  /// Defaults to [MethodChannelPhoneHintAndroid].
  static PhoneHintAndroidPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [PhoneHintAndroidPlatform] when
  /// they register themselves.
  static set instance(PhoneHintAndroidPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
