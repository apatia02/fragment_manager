package com.example

import com.example.fragment_manager.R
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton

object TabScreen : Screen<TabScreen>() {
    val openFunnyFragmentBtn = KButton { withId(R.id.open_funny_fragment_btn) }
    val somethingEt = KEditText { withId(R.id.something_et) }
}