# Botob Sample SDK

This repository houses the Botob Sample SDK for Android.

## About `app`

The Botob Sample SDK *app* module demonstrates the capabilities of the *libcore* library.

## About `libcore`

The Botob Sample SDK *libcore* library provides various features including: 

- Solving equations with 1 or more variables.

## Getting Started

You can run the *app* module on a device or emulator to see the current *libcore* features.

In order to put a test file, e.g. _control_equations.txt_ on the device, please feel free to use the following `adb` command:
```
adb push ~/Downloads/control_equations.txt /sdcard/Download/
```

## Status

- The current implementation uses recursion and may not scale too far.
- Other potential implementations include linear equation resolution [via inverse matrix method]([http://onlinemschool.com/math/assistance/equation/matr/]) or [via gaussian elimination](http://onlinemschool.com/math/assistance/equation/gaus/). 