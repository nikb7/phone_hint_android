import 'package:flutter_test/flutter_test.dart';
import 'package:phone_hint_android/phone_hint_android.dart';
import 'package:phone_hint_android/phone_hint_android_method_channel.dart';
import 'package:phone_hint_android/phone_hint_android_platform_interface.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockPhoneHintAndroidPlatform
    with MockPlatformInterfaceMixin
    implements PhoneHintAndroidPlatform {
  @override
  Future<String?> getPhoneNumber() => Future.value('42');
}

void main() {
  final PhoneHintAndroidPlatform initialPlatform =
      PhoneHintAndroidPlatform.instance;

  test('$MethodChannelPhoneHintAndroid is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelPhoneHintAndroid>());
  });

  test('getPlatformVersion', () async {
    PhoneHintAndroid phoneHintAndroidPlugin = PhoneHintAndroid();
    MockPhoneHintAndroidPlatform fakePlatform = MockPhoneHintAndroidPlatform();
    PhoneHintAndroidPlatform.instance = fakePlatform;

    expect(await phoneHintAndroidPlugin.getPhoneNumber(), '42');
  });
}
