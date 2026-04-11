# Mobile Application Development Project Report

## 1. Title Page

- Project Title: Mobile Diagnostic Tool for Android (MDT Android)
- Course: Mobile Application Development (MAD)
- Student Name:
- Registration Number:
- Batch/Semester:
- Submission Date:

## 2. Abstract

This project presents a Java-based Android Mobile Diagnostic Tool designed to test and report the current health and technical status of a smartphone. The application includes software and hardware diagnostics, sensor testing, storage and memory checks, call log preview, and report generation features through a user-friendly and professional interface.

## 3. Objectives

- Build a professional Android diagnostic application using Java.
- Display mobile software and hardware information in structured format.
- Validate important sensor availability and behavior.
- Implement storage and memory checks.
- Generate and share a complete diagnostic report.

## 4. Requirement Analysis

### Functional Requirements

- Display device information (model, brand, Android version, kernel).
- Show battery and network status.
- Validate camera, sound, and display information.
- Monitor motion, position, and environment sensors.
- Perform storage diagnostics.
- Read call logs after permission grant.
- Generate and share report.

### Non-Functional Requirements

- Fast and responsive UI.
- Clear and minimal user flow.
- Runtime permission handling.
- Compatibility with Android 7.0+.

## 5. System Design

### Architecture

- Activity + Fragment architecture with ViewPager2 tabs.
- Utility classes for diagnostics logic.
- RecyclerView + card UI for scalable data display.

### UI Strategy

- Progressive disclosure through tab sections.
- Minimal content density and actionable information cards.
- Professional visual style with custom color palette.

## 6. Implementation Details

- Language: Java
- Platform: Android SDK
- IDE: Android Studio
- Core components:
  - MainActivity
  - Home, Info, Sensors, Storage fragments
  - DiagnosticAdapter
  - Utility classes (DeviceInfoUtil, HardwareInfoUtil, SensorMonitor, StorageUtil, ReportUtil)

## 7. Testing and Results

### Test Cases

- App launch and tab navigation
- Info refresh correctness
- Sensor live updates
- Storage values display
- Call log permission workflow
- Report generation and share intent

### Result Summary

All major syllabus modules were implemented and verified on emulator/device. Some restricted fields (e.g., IMEI) are limited by Android privacy policy and are handled with informative messages.

## 8. Challenges and Solutions

- IMEI access restrictions: replaced with policy-compliant message.
- Permission-sensitive call logs: implemented runtime request flow.
- Sensor variability across devices: dynamic detection of available sensors.

## 9. Conclusion

The MDT Android application satisfies the MAD syllabus requirements and demonstrates practical Java Android development skills including UI design, device diagnostics, sensor programming, storage testing, and report generation.

## 10. Future Enhancements

- PDF export with branded report layout.
- Historical diagnostics tracking using Room database.
- Cloud backup of diagnostic results.
- Device-to-device benchmark scoring.
