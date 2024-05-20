package com.example.fragment_manager.firebase

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.fragment_manager.App
import com.example.fragment_manager.R
import com.google.firebase.messaging.RemoteMessage

class NotificationHelper(private val context: Context) {

    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun showNotification(remoteMessage: RemoteMessage) {
        val channel = App.CHANNEL_FOR_TAB_FRAGMENTS_ID
        if (!checkIsChannelEnable(channel)) {
            return
        }
        val notificationBuilder =
            NotificationCompat.Builder(context, channel)
                .setSmallIcon(R.drawable.fun_1)
                .setContentTitle(remoteMessage.notification?.title)
                .setContentText(remoteMessage.notification?.body)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)

        val notificationId = System.currentTimeMillis().toInt()
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private fun checkIsChannelEnable(channelId: String): Boolean {
        val channel = notificationManager.getNotificationChannel(channelId)
        return channel != null && channel.importance != NotificationManager.IMPORTANCE_NONE
    }
}