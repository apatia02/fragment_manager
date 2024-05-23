package com.example.fragment_manager.presentation.notification

import com.example.fragment_manager.R
import com.example.fragment_manager.presentation.constants.TAB_1_ID
import com.example.fragment_manager.presentation.constants.TAB_2_ID
import com.example.fragment_manager.presentation.constants.TAB_3_ID
import com.example.fragment_manager.presentation.constants.TAB_4_ID
import com.example.fragment_manager.presentation.notification.NotificationTypes.CustomWithReply
import com.example.fragment_manager.presentation.notification.NotificationTypes.ExpandedWithImg
import com.example.fragment_manager.presentation.notification.NotificationTypes.NotificationsWithNavigations.NavigationToTab
import com.example.fragment_manager.presentation.notification.NotificationTypes.NotificationsWithNavigations.SingleWithNavigation
import com.google.firebase.messaging.RemoteMessage.Notification

fun Notification.mapToNotificationType(): NotificationTypes {
    val notificationId = System.currentTimeMillis().toInt()
    val notification = when {
        this.title?.contains("tab") ?: false -> this.getNavigationToTab()
        this.title?.contains("fun") ?: false -> this.getExpandedWithImg()
        this.title?.contains("single") ?: false -> this.getSingleWithNavigation()
        this.title?.contains("reply") ?: false -> CustomWithReply(
            title = this.title.orEmpty(),
            body = this.body.orEmpty()
        )

        else -> this.getNavigationToTab()
    }
    return notification.setId(notificationId)
}

private fun NotificationTypes.setId(id: Int): NotificationTypes {
    return when (this) {
        is NavigationToTab -> this.copy(id = id)
        is ExpandedWithImg -> this.copy(id = id)
        is CustomWithReply -> this.copy(id = id)
        else -> this
    }
}

private fun Notification.getNavigationToTab(): NavigationToTab {
    val tabId = when (this.title) {
        "tab 1" -> R.id.tab_1
        "tab 2" -> R.id.tab_2
        "tab 3" -> R.id.tab_3
        else -> R.id.tab_4
    }
    return NavigationToTab(
        tabId = tabId, title = this.title.orEmpty(), body = this.body.orEmpty()
    )
}

private fun Notification.getSingleWithNavigation(): SingleWithNavigation {
    val tabId = when (this.body) {
        "fun 1" -> TAB_1_ID
        "fun 2" -> TAB_2_ID
        "fun 3" -> TAB_3_ID
        else -> TAB_4_ID
    }
    return SingleWithNavigation(title = title.orEmpty(), tabId = tabId, body = body.orEmpty())
}

private fun Notification.getExpandedWithImg(): ExpandedWithImg {
    val imgRes = when (this.title) {
        "fun 1" -> R.drawable.fun_1
        "fun 2" -> R.drawable.fun_2
        "fun 3" -> R.drawable.fun_3
        else -> R.drawable.fun_4
    }
    return ExpandedWithImg(
        imgRes = imgRes, title = this.title.orEmpty(), body = this.body.orEmpty()
    )
}