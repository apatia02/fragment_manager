package com.example.fragment_manager.presentation.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.BigPictureStyle
import androidx.core.app.NotificationCompat.Builder
import com.example.fragment_manager.R
import com.example.fragment_manager.presentation.activity.MainActivity
import com.example.fragment_manager.presentation.notification.NotificationTypes.ExpandedWithImg
import com.example.fragment_manager.presentation.notification.NotificationTypes.NavigationToTab
import com.google.firebase.messaging.RemoteMessage

class NotificationHelper(private val context: Context) {

    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private val notificationsGroups = mutableMapOf<Int, Builder>()

    fun showNotification(remoteMessage: RemoteMessage) {
        val notificationType: NotificationTypes =
            remoteMessage.notification?.mapToNotificationType() ?: NavigationToTab()
        if (!checkIsChannelEnable(notificationType.channel)) {
            return
        }

        val notification = notificationType.buildNotificationBuilder()
        val notificationId = System.currentTimeMillis().toInt()
        notificationManager.notify(notificationId, notification.build())
        notificationType.handleSummaryNotification()
    }

    private fun NotificationTypes.handleSummaryNotification() {
        val groupId = when (this) {
            is NavigationToTab -> GROUP_TAB_ID
            is ExpandedWithImg -> GROUP_IMG_ID
        }
        notificationsGroups[groupId] ?: run {
            this.createBaseBuilder()
                .setGroupSummary(true)
                .setStyle(
                    NotificationCompat.InboxStyle()
                    .setSummaryText(group))
                .apply {
                    notificationsGroups[groupId] = this
                }
        }
        notificationsGroups.forEach {
            notificationManager.notify(it.key, it.value.build())
        }
    }

    private fun NotificationTypes.buildNotificationBuilder(): Builder {
        val notificationBuilder = this.createBaseBuilder()
        return when (this) {
            is NavigationToTab -> notificationBuilder.buildNotificationBuilder(this)
            is ExpandedWithImg -> notificationBuilder.buildNotificationBuilder(this)
        }
    }

    private fun NotificationTypes.createBaseBuilder(): Builder {
        return Builder(context, channel)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(priority)
            .setGroup(group)
            .setAutoCancel(true)
    }

    private fun Builder.buildNotificationBuilder(notificationType: NavigationToTab): Builder {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(TAB_ARGUMENT, notificationType.tabId)
        }
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        return this.setContentIntent(pendingIntent)
    }

    private fun Builder.buildNotificationBuilder(notificationType: ExpandedWithImg): Builder {
        val largeImg = BitmapFactory.decodeResource(context.resources, notificationType.imgRes)
        val bigStyle = BigPictureStyle()
            .bigPicture(largeImg)
            .bigLargeIcon(null)

        return this.setLargeIcon(largeImg)
            .setStyle(bigStyle)
    }

    private fun checkIsChannelEnable(channelId: String): Boolean {
        val channel = notificationManager.getNotificationChannel(channelId)
        return channel != null && channel.importance != NotificationManager.IMPORTANCE_NONE
    }

    companion object {
        const val TAB_ARGUMENT = "tab"
        const val GROUP_TAB = "notifications for tabs"
        const val GROUP_IMG = "notifications for image"
        const val GROUP_TAB_ID = 0
        const val GROUP_IMG_ID = 1
    }
}