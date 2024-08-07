package com.example.fragment_manager.presentation.notification

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.Action
import androidx.core.app.NotificationCompat.BigPictureStyle
import androidx.core.app.NotificationCompat.Builder
import androidx.core.app.NotificationCompat.DecoratedCustomViewStyle
import androidx.core.app.NotificationCompat.InboxStyle
import androidx.core.app.RemoteInput
import androidx.core.app.RemoteInput.*
import com.example.fragment_manager.R
import com.example.fragment_manager.domain.DEFAULT_INT
import com.example.fragment_manager.presentation.activity.MainActivity
import com.example.fragment_manager.presentation.notification.NotificationTypes.CustomWithReply
import com.example.fragment_manager.presentation.notification.NotificationTypes.ExpandedWithImg
import com.example.fragment_manager.presentation.notification.NotificationTypes.NonCancelable
import com.example.fragment_manager.presentation.notification.NotificationTypes.NotificationsWithNavigations
import com.example.fragment_manager.presentation.notification.NotificationTypes.NotificationsWithNavigations.NavigationToTab
import com.example.fragment_manager.presentation.notification.NotificationTypes.NotificationsWithNavigations.SingleWithNavigation
import com.example.fragment_manager.presentation.notification.ReplyReceiver.Companion.EXTRA_GROUP_ID
import com.example.fragment_manager.presentation.notification.ReplyReceiver.Companion.EXTRA_NOTIFICATION_ID
import com.example.fragment_manager.presentation.notification.ReplyReceiver.Companion.KEY_TEXT_REPLY
import com.google.firebase.messaging.RemoteMessage
import androidx.appcompat.R as materialR
import com.google.android.material.R as googleMaterialR

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
        notificationManager.notify(notificationType.id, notification.build())
        notificationType.handleSummaryNotification()
    }

    private fun NotificationTypes.handleSummaryNotification() {
        if (groupId != DEFAULT_INT) {
            notificationsGroups[groupId] ?: run {
                this.createBaseBuilder()
                    .setGroupSummary(true)
                    .setStyle(
                        InboxStyle()
                            .setSummaryText(group)
                    )
                    .apply {
                        notificationsGroups[groupId] = this
                    }
            }
        }
        notificationsGroups.forEach {
            notificationManager.notify(it.key, it.value.build())
        }
    }

    private fun NotificationTypes.buildNotificationBuilder(): Builder {
        return when (this) {
            is NavigationToTab -> this.createBaseBuilder().buildNotificationBuilder(this)
            is SingleWithNavigation -> this.createBaseBuilder().buildNotificationBuilder(this)
            is ExpandedWithImg -> this.createBaseBuilder().buildNotificationBuilder(this)
            is CustomWithReply -> this.buildNotificationBuilder()
            is NonCancelable -> this.createBaseBuilder().buildNotificationBuilder(this)
        }
    }

    private fun CustomWithReply.buildNotificationBuilder(): Builder {
        val notificationLayout =
            RemoteViews(context.packageName, R.layout.layout_notification_custom)
        notificationLayout.setTextViewText(R.id.notification_title_tv, title)
        notificationLayout.setTextViewText(R.id.notification_body_tv, body)

        val replyLabel: String = context.getString(R.string.reply_label)
        val remoteInput: RemoteInput = Builder(KEY_TEXT_REPLY).run {
            setLabel(replyLabel)
            build()
        }

        val replyIntent = Intent(context, ReplyReceiver::class.java).apply {
            putExtra(EXTRA_NOTIFICATION_ID, id)
        }
        val replyPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(context, id, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val action: Action = Action.Builder(
            materialR.drawable.abc_ic_go_search_api_material,
            context.getString(R.string.reply_label),
            replyPendingIntent
        )
            .addRemoteInput(remoteInput)
            .build()

        return Builder(context, channel)
            .setPriority(priority)
            .setGroup(group)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setStyle(DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
            .addAction(action)
            .setAutoCancel(true)
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

    private fun Builder.buildNotificationBuilder(notificationType: NotificationsWithNavigations): Builder {
        var notification = this
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            when (notificationType) {
                is NavigationToTab -> {
                    putExtra(TAB_ARGUMENT, notificationType.tabId)
                    notification =
                        notification.setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                }

                is SingleWithNavigation -> putExtra(TAB_ARGUMENT, notificationType.tabId)
            }
        }
        val pendingIntent =
            PendingIntent.getActivity(
                context,
                notificationType.id,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        return notification.setContentIntent(pendingIntent)
    }

    private fun Builder.buildNotificationBuilder(notificationType: ExpandedWithImg): Builder {
        val largeImg = BitmapFactory.decodeResource(context.resources, notificationType.imgRes)
        val bigStyle = BigPictureStyle()
            .bigPicture(largeImg)
            .bigLargeIcon(null)

        return this.setLargeIcon(largeImg)
            .setStyle(bigStyle)
    }

    @SuppressLint("PrivateResource")
    private fun Builder.buildNotificationBuilder(notificationType: NonCancelable): Builder {

        val cancelIntent = Intent(context, ReplyReceiver::class.java).apply {
            putExtra(EXTRA_NOTIFICATION_ID, notificationType.id)
            putExtra(EXTRA_GROUP_ID, notificationType.groupId)
        }
        val cancelPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(
                context,
                notificationType.id,
                cancelIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        return this
            .addAction(
                googleMaterialR.drawable.mtrl_ic_cancel,
                context.getString(R.string.cancel),
                cancelPendingIntent
            )
            .setOngoing(true)
    }

    private fun checkIsChannelEnable(channelId: String): Boolean {
        val channel = notificationManager.getNotificationChannel(channelId)
        return channel != null && channel.importance != NotificationManager.IMPORTANCE_NONE
    }

    companion object {
        const val TAB_ARGUMENT = "tab"
        const val GROUP_TAB = "notifications for tabs"
        const val GROUP_IMG = "notifications for image"
        const val GROUP_REPLY = "notifications for reply"
        const val GROUP_CANCEL = "notifications for cancel"
        const val GROUP_TAB_ID = 0
        const val GROUP_IMG_ID = 1
        const val GROUP_REPLY_ID = 2
        const val GROUP_CANCEL_ID = 3
        const val SINGLE_NOTIFICATION_ID = 4
    }
}