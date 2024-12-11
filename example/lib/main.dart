import 'dart:async';

import 'package:flutter/material.dart';
import 'package:phone_hint_android/phone_hint_android.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final _phoneHintAndroidPlugin = PhoneHintAndroid();

  @override
  void initState() {
    super.initState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> _getPhoneHint() async {
    final phoneNumber = await _phoneHintAndroidPlugin.getPhoneNumber();
    if (phoneNumber != null && mounted) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Phone hint: $phoneNumber')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Phone hint example'),
        ),
        body: Center(
          child: ElevatedButton(
            onPressed: () {
              _getPhoneHint();
            },
            child: const Text('Get phone hint'),
          ),
        ),
      ),
    );
  }
}
