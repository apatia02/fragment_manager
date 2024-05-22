package com.example.fragment_manager.presentation.notification

import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.example.fragment_manager.App
import com.example.fragment_manager.R
import com.example.fragment_manager.domain.EMPTY_STRING

sealed class NotificationTypes(
    open val channel: String = EMPTY_STRING,
    open val title: String = EMPTY_STRING,
    open val body: String = EMPTY_STRING,
    open val group: String = EMPTY_STRING,
    open val priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {

    data class NavigationToTab(
        val tabId: Int = R.id.tab_1,
        override val channel: String = App.CHANNEL_FOR_TAB_FRAGMENTS_ID,
        override val title: String = EMPTY_STRING,
        override val body: String = EMPTY_STRING,
        override val group: String = NotificationHelper.GROUP_TAB,
        override val priority: Int = NotificationCompat.PRIORITY_LOW
    ) : NotificationTypes()

    data class ExpandedWithImg(
        @DrawableRes val imgRes: Int = R.drawable.fun_1,
        override val channel: String = App.CHANNEL_FOR_FUNNY_FRAGMENT_ID,
        override val title: String = EMPTY_STRING,
        override val body: String = EMPTY_STRING,
        override val group: String = NotificationHelper.GROUP_IMG,
        override val priority: Int = NotificationCompat.PRIORITY_HIGH
    ) : NotificationTypes()
}