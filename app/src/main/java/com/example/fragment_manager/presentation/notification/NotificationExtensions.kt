package com.example.fragment_manager.presentation.notification

import com.example.fragment_manager.R
import com.example.fragment_manager.presentation.notification.NotificationTypes.ExpandedWithImg
import com.example.fragment_manager.presentation.notification.NotificationTypes.NavigationToTab
import com.google.firebase.messaging.RemoteMessage.Notification

fun Notification.mapToNotificationType(): NotificationTypes {
    return when {
        this.title?.contains("tab") ?: false -> this.getNavigationToTab()
        this.title?.contains("fun") ?: false -> this.getExpandedWithImg()
        else -> this.getNavigationToTab()
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