package com.example

import com.example.fragment_manager.R
import io.github.kakaocup.kakao.bottomnav.KBottomNavigationView
import io.github.kakaocup.kakao.screen.Screen

object MainScreen : Screen<MainScreen>() {
    val bottomNavigation = KBottomNavigationView { withId(R.id.bottom_navigation_view) }
}