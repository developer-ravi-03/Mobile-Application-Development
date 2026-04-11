package com.mdt.diagnostic.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.mdt.diagnostic.model.DiagnosticItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SensorMonitor implements SensorEventListener {

  public interface Listener {
    void onSensorData(List<DiagnosticItem> data);
  }

  private final SensorManager sensorManager;
  private final List<Sensor> trackedSensors = new ArrayList<>();
  private final Map<Integer, float[]> latestValues = new HashMap<>();
  private final Listener listener;

  public SensorMonitor(Context context, Listener listener) {
    this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    this.listener = listener;
    initSensors();
  }

  private void initSensors() {
    if (sensorManager == null) {
      return;
    }
    addIfExists(Sensor.TYPE_ACCELEROMETER);
    addIfExists(Sensor.TYPE_GYROSCOPE);
    addIfExists(Sensor.TYPE_ROTATION_VECTOR);
    addIfExists(Sensor.TYPE_GRAVITY);
    addIfExists(Sensor.TYPE_PROXIMITY);
    addIfExists(Sensor.TYPE_ORIENTATION);
    addIfExists(Sensor.TYPE_MAGNETIC_FIELD);
    addIfExists(Sensor.TYPE_LIGHT);
    addIfExists(Sensor.TYPE_AMBIENT_TEMPERATURE);
    addIfExists(Sensor.TYPE_PRESSURE);
    addIfExists(Sensor.TYPE_RELATIVE_HUMIDITY);
  }

  private void addIfExists(int sensorType) {
    Sensor sensor = sensorManager.getDefaultSensor(sensorType);
    if (sensor != null) {
      trackedSensors.add(sensor);
    }
  }

  public void start() {
    if (sensorManager == null) {
      notifyUnavailable();
      return;
    }
    for (Sensor sensor : trackedSensors) {
      sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    dispatch();
  }

  public void stop() {
    if (sensorManager != null) {
      sensorManager.unregisterListener(this);
    }
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    latestValues.put(event.sensor.getType(), event.values.clone());
    dispatch();
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {
    // No-op
  }

  private void dispatch() {
    if (listener == null) {
      return;
    }

    List<DiagnosticItem> data = new ArrayList<>();
    for (Sensor sensor : trackedSensors) {
      float[] values = latestValues.get(sensor.getType());
      String valueText;
      if (values == null) {
        valueText = "Waiting for data...";
      } else {
        valueText = formatValues(values);
      }
      data.add(new DiagnosticItem(sensor.getName(), valueText, "LIVE"));
    }

    if (data.isEmpty()) {
      data.add(new DiagnosticItem("Sensors", "No supported sensors detected", "WARN"));
    }

    listener.onSensorData(data);
  }

  private void notifyUnavailable() {
    List<DiagnosticItem> data = new ArrayList<>();
    data.add(new DiagnosticItem("Sensors", "Sensor manager unavailable", "ERROR"));
    listener.onSensorData(data);
  }

  private String formatValues(float[] values) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < values.length; i++) {
      if (i > 0) {
        sb.append(" | ");
      }
      sb.append(String.format(Locale.US, "%.2f", values[i]));
    }
    return sb.toString();
  }
}
