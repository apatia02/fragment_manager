package com.example.fragment_manager.presentation.notification

import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.example.fragment_manager.App
import com.example.fragment_manager.R
import com.example.fragment_manager.domain.DEFAULT_INT
import com.example.fragment_manager.domain.EMPTY_STRING
import com.example.fragment_manager.presentation.constants.TAB_1_ID

sealed class NotificationTypes(
    open val id: Int = DEFAULT_INT,
    open val channel: String = EMPTY_STRING,
    open val title: String = EMPTY_STRING,
    open val body: String = EMPTY_STRING,
    open val group: String = EMPTY_STRING,
    open val groupId: Int = DEFAULT_INT,
    open val priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {

    data class ExpandedWithImg(
        @DrawableRes val imgRes: Int = R.drawable.fun_1,
        override val id: Int = DEFAULT_INT,
        override val channel: String = App.CHANNEL_FOR_FUNNY_FRAGMENT_ID,
        override val title: String = EMPTY_STRING,
        override val body: String = EMPTY_STRING,
        override val group: String = NotificationHelper.GROUP_IMG,
        override val groupId: Int = NotificationHelper.GROUP_IMG_ID,
        override val priority: Int = NotificationCompat.PRIORITY_HIGH
    ) : NotificationTypes()

    data class CustomWithReply(
        override val id: Int = DEFAULT_INT,
        override val channel: String = App.CHANNEL_FOR_FUNNY_FRAGMENT_ID,
        override val title: String = EMPTY_STRING,
        override val body: String = EMPTY_STRING,
        override val group: String = NotificationHelper.GROUP_REPLY,
        override val groupId: Int = NotificationHelper.GROUP_REPLY_ID,
        override val priority: Int = NotificationCompat.PRIORITY_HIGH
    ) : NotificationTypes()

    data class NonCancelable(
        override val id: Int = DEFAULT_INT,
        override val channel: String = App.CHANNEL_FOR_TAB_FRAGMENTS_ID,
        override val title: String = EMPTY_STRING,
        override val body: String = EMPTY_STRING,
        override val group: String = NotificationHelper.GROUP_CANCEL,
        override val groupId: Int = NotificationHelper.GROUP_CANCEL_ID,
        override val priority: Int = NotificationCompat.PRIORITY_HIGH
    ) : NotificationTypes()

    sealed class NotificationsWithNavigations : NotificationTypes() {

        data class NavigationToTab(
            val tabId: Int = R.id.tab_1,
            override val id: Int = DEFAULT_INT,
            override val channel: String = App.CHANNEL_FOR_TAB_FRAGMENTS_ID,
            override val title: String = EMPTY_STRING,
            override val body: String = EMPTY_STRING,
            override val group: String = NotificationHelper.GROUP_TAB,
            override val groupId: Int = NotificationHelper.GROUP_TAB_ID,
            override val priority: Int = NotificationCompat.PRIORITY_LOW
        ) : NotificationsWithNavigations()

        data class SingleWithNavigation(
            override val id: Int = NotificationHelper.SINGLE_NOTIFICATION_ID,
            val tabId: String = TAB_1_ID,
            override val channel: String = App.CHANNEL_FOR_FUNNY_FRAGMENT_ID,
            override val title: String = EMPTY_STRING,
            override val body: String = EMPTY_STRING,
            override val priority: Int = NotificationCompat.PRIORITY_HIGH
        ) : NotificationsWithNavigations()
    }
}