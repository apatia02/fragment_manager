package com.example.fragment_manager

import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.screen.Screen

object FunnyScreen : Screen<FunnyScreen>() {
    val funnyImage = KImageView { withId(R.id.fun_image) }
}