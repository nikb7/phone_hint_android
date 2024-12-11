import 'phone_hint_android_platform_interface.dart';

class PhoneHintAndroid {
  Future<String?> getPhoneNumber() {
    return PhoneHintAndroidPlatform.instance.getPhoneNumber();
  }
}
