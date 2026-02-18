package com.example.practical_05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PhoneStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context.getApplicationContext(), "Changing state", Toast.LENGTH_SHORT).show();

        String triggeredState=intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(triggeredState.equals(TelephonyManager.EXTRA_STATE_IDLE))
            Toast.makeText(context, "Phone is Idle", Toast.LENGTH_SHORT).show();
        else if (triggeredState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            Toast.makeText(context, "Phone is of the hook", Toast.LENGTH_SHORT).show();            
        } else if (triggeredState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            Toast.makeText(context, "Phone is ringing", Toast.LENGTH_SHORT).show();
        }
    }
}