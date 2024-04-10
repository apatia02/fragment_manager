package com.example.fragment_manager

import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

object TabScreen : Screen<TabScreen>() {
    val openFunnyFragmentBtn = KButton { withId(R.id.open_funny_fragment_btn) }
}