package com.example

import com.example.fragment_manager.R
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView

object FunnyScreen : Screen<FunnyScreen>() {
    val funnyImage = KImageView { withId(R.id.fun_image) }
    val receivedWord = KTextView { withId(R.id.received_text_tv) }
}