package com.example.fragment_manager.presentation.notification

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.fragment_manager.presentation.activity.MainActivity

class ReplyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val replyText = RemoteInput.getResultsFromIntent(intent)?.getCharSequence(KEY_TEXT_REPLY)
        val notificationId = intent.getIntExtra(EXTRA_NOTIFICATION_ID, -1)
        if (replyText != null) {
            val responseIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra(EXTRA_REPLY, replyText.toString())
            }
            context.startActivity(responseIntent)
        }
        if (notificationId != -1) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancel(notificationId)
        }
    }

    companion object {
        const val KEY_TEXT_REPLY = "key text reply"
        const val EXTRA_REPLY = "extra reply"
        const val EXTRA_NOTIFICATION_ID = "extra reply"
    }
}