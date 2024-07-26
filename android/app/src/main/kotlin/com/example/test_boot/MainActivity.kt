package com.example.test_boot

import io.flutter.embedding.android.FlutterActivity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.Settings

class MainActivity : FlutterActivity() {
    companion object {
        const val REQUEST_OVERLAY_PERMISSIONS = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!Settings.canDrawOverlays(this)) {
            val myIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            val uri: Uri = Uri.fromParts("package", packageName, null)
            myIntent.data = uri
            startActivityForResult(myIntent, REQUEST_OVERLAY_PERMISSIONS)
            return
        }
    }
}

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val handler = Handler()
        handler.post {
            val dialogIntent = Intent(context, MainActivity::class.java)
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(dialogIntent)
        }
    }
}
