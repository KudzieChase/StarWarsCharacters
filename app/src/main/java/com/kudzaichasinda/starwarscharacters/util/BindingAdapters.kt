package com.kudzaichasinda.starwarscharacters.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.kudzaichasinda.starwarscharacters.R

@BindingAdapter("isTextLoading")
fun TextView.isLoading(isLoading: Boolean) {
    text = if (isLoading) resources.getText(R.string.animation_searching_text) else
        resources.getText(R.string.animation_paused_text)
}

@BindingAdapter("isAnimationPlaying")
fun LottieAnimationView.isAnimating(isLoading: Boolean) {
    if (isLoading)
        playAnimation()
    else
        pauseAnimation()
}