package com.example.test_boot

import io.flutter.embedding.android.FlutterActivity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log

class MainActivity: FlutterActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var REQUEST_OVERLAY_PERMISSIONS = 100
        if (!Settings.canDrawOverlays(getApplicationContext())) {
            val myIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            val uri: Uri = Uri.fromParts("package", getPackageName(), null)
            myIntent.setData(uri)
            startActivityForResult(myIntent, REQUEST_OVERLAY_PERMISSIONS)
            return
        }
    }
}

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("BootCompletedReceiver", "onReceive called")
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            val activityIntent = Intent(context, MainActivity::class.java)
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(activityIntent)
            Log.d("BootCompletedReceiver", "MainActivity started")
        }
        else{
            Log.d("BootCompletedReceiver", "Received unexpected action: ${intent.action}")
        }
    }
}