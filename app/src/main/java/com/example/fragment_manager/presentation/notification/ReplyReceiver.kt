package com.example.fragment_manager.presentation.notification

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.fragment_manager.domain.DEFAULT_INT
import com.example.fragment_manager.presentation.activity.MainActivity

class ReplyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val replyText = RemoteInput.getResultsFromIntent(intent)?.getCharSequence(KEY_TEXT_REPLY)
        val notificationId = intent.getIntExtra(EXTRA_NOTIFICATION_ID, DEFAULT_INT)
        val groupId = intent.getIntExtra(EXTRA_GROUP_ID, DEFAULT_INT)
        if (replyText != null) {
            val responseIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra(EXTRA_REPLY, replyText.toString())
            }
            context.startActivity(responseIntent)
        }
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (notificationId != DEFAULT_INT) {
            notificationManager.cancel(notificationId)
        }
        if (groupId != DEFAULT_INT && checkIsCancelablePushLast(notificationManager)) {
            notificationManager.cancel(groupId)
        }
    }

    private fun checkIsCancelablePushLast(notificationManager: NotificationManager): Boolean {
        val activePushes = notificationManager.activeNotifications.toList()
        val countCancelablePushes =
            activePushes.count { it.notification.group == NotificationHelper.GROUP_CANCEL }
        return countCancelablePushes == 1
    }

    companion object {
        const val KEY_TEXT_REPLY = "key text reply"
        const val EXTRA_REPLY = "extra reply"
        const val EXTRA_NOTIFICATION_ID = "extra notification id"
        const val EXTRA_GROUP_ID = "extra group id"
    }
}