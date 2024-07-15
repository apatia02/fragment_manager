package com.example.fragment_manager

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.example.fragment_manager.di.AppComponent

class App : Application() {
    val appComponent = AppComponent()

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        val channelTabFragments = NotificationChannel(
            CHANNEL_FOR_TAB_FRAGMENTS_ID,
            getString(R.string.channel_for_tab_fragments),
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = getString(R.string.channel_for_tab_fragments_description)
            setShowBadge(true)
        }

        val channelFunnyFragment = NotificationChannel(
            CHANNEL_FOR_FUNNY_FRAGMENT_ID,
            getString(R.string.channel_for_funny_fragment),
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = getString(R.string.channel_for_funny_fragment_description)
            setShowBadge(false)
        }

        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channelTabFragments)
        manager.createNotificationChannel(channelFunnyFragment)
    }

    companion object {
        const val CHANNEL_FOR_TAB_FRAGMENTS_ID = "channel_for_tab_fragments_id"
        const val CHANNEL_FOR_FUNNY_FRAGMENT_ID = "channel_for_funny_fragment_id"
    }
}