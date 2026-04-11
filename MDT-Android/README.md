# MDT Android - Mobile Diagnostic Tool

A professional Java Android application for mobile diagnostics aligned with MAD syllabus requirements.

## Tech Stack

- Language: Java
- Platform: Android
- IDE: Android Studio
- Build System: Gradle
- UI: Material Components, ConstraintLayout, RecyclerView, ViewPager2, TabLayout
- Permissions and Sharing: Android runtime permissions, FileProvider
- Minimum SDK: 24
- Target SDK: 35

## Project Folder Structure

```text
MDT-Android/
├── app/
│   ├── src/main/
│   │   ├── java/com/mdt/diagnostic/
│   │   │   ├── MainActivity.java
│   │   │   ├── model/
│   │   │   │   └── DiagnosticItem.java
│   │   │   ├── ui/common/
│   │   │   │   └── DiagnosticAdapter.java
│   │   │   ├── ui/sections/
│   │   │   │   ├── HomeFragment.java
│   │   │   │   ├── InfoFragment.java
│   │   │   │   ├── SensorsFragment.java
│   │   │   │   └── StorageFragment.java
│   │   │   └── util/
│   │   │       ├── DeviceInfoUtil.java
│   │   │       ├── HardwareInfoUtil.java
│   │   │       ├── ReportUtil.java
│   │   │       ├── SensorMonitor.java
│   │   │       └── StorageUtil.java
│   │   └── res/
│   │       ├── drawable/
│   │       ├── layout/
│   │       ├── values/
│   │       ├── values-night/
│   │       └── xml/
│   └── build.gradle
├── build.gradle
├── settings.gradle
├── gradle.properties
├── README.md
└── PROJECT_REPORT_TEMPLATE.md
```

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
2. Clone the repository if you have not done so already:

```bash
git clone https://github.com/developer-ravi-03/Mobile-Application-Development.git
```

3. Open Android Studio.
4. Click Open and select the `MDT-Android` folder.
5. Let Gradle sync complete.
6. If prompted for SDK components, install Android SDK Platform 35 and the required Build Tools.
7. Create an emulator (API 30+ recommended) or connect an Android phone with USB debugging.
8. Click Run.

## Screenshots

Add your final app screenshots here before submission. Recommended screenshots:

1. Home dashboard screen
2. Mobile Info screen
3. Sensors screen
4. Storage and report screen
5. Call log permission prompt or report share result

You can store them in a folder like `screenshots/` inside the project and link them in this section if required by your teacher.

## Permissions Notes

- Call logs require runtime permission and may be unavailable on some devices.
- IMEI is restricted on modern Android due to privacy rules.

## Deliverables for Course

- App APK/Run demo
- Screenshots of each tab
- Generated diagnostic report text
- Final project report document

## Repository

- GitHub: https://github.com/developer-ravi-03/Mobile-Application-Development/tree/main/MDT-Android

## 👨‍💻Author

- Ravi Kumar
- Computer Science Student
- Full Stack Developer
