# MDT Android - Mobile Diagnostic Tool

A professional Java Android application for mobile diagnostics aligned with MAD syllabus requirements.

## Implemented Modules

1. MDT-UI Design

- Clean professional gradient UI
- Progressive disclosure with tab-based sections
- Card-based data presentation

2. MDT-Mobile Info and Spec

- Device details (brand, model, hardware, kernel)
- Android software details
- Battery status
- Network state
- Camera, sound, and display checks

3. MDT-Sensors Test

- Motion sensors: accelerometer, gyroscope, rotation vector, gravity
- Position sensors: proximity, orientation, magnetic field
- Environment sensors: light, temperature, pressure, humidity

4. MDT-Data Storage and Memory

- Internal and external storage diagnostics
- Call log preview with runtime permission
- Report generation and share

## How to Run in Android Studio

1. Install Android Studio (latest stable).
2. Open Android Studio.
3. Click Open and select this project folder.
4. Let Gradle sync complete.
5. If prompted for Gradle wrapper, choose Android Studio default Gradle setup or create a new Android project once and copy this source over.
6. Create an emulator (API 30+ recommended) or connect an Android phone with USB debugging.
7. Click Run.

## Permissions Notes

- Call logs require runtime permission and may be unavailable on some devices.
- IMEI is restricted on modern Android due to privacy rules.

## Deliverables for Course

- App APK/Run demo
- Screenshots of each tab
- Generated diagnostic report text
- Final project report document
