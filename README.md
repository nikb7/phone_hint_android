# phone_hint_android

Flutter plugin for Phone Number Hint Api on Android, provides a frictionless way to show a user's (SIM-based) phone numbers as a hint.

![demo](https://github.com/user-attachments/assets/c6c132eb-85e5-4870-8f1b-9a57ee5a4d3a)

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

Utilised Google's [Phone Number Hint Api](https://developers.google.com/identity/phone-number-hint/android)


### Support phone_hint_android

You can support phone_hint_android by liking it on Pub and staring it on Github, sharing ideas on how we can enhance a certain functionality or by reporting any problems you encounter.
