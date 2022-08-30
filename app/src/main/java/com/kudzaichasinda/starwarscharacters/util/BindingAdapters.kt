package com.kudzaichasinda.starwarscharacters.util

import android.annotation.SuppressLint
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.kudzaichasinda.starwarscharacters.R

@BindingAdapter("isTextLoading")
fun TextView.isLoading(isLoading: Boolean) {
    text = if (isLoading) resources.getText(R.string.animation_searching_text) else {
        resources.getText(R.string.animation_paused_text)
    }
}

@BindingAdapter("isAnimationPlaying")
fun LottieAnimationView.isAnimating(isLoading: Boolean) {
    if (isLoading) {
        playAnimation()
    } else {
        pauseAnimation()
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("formattedHeight")
fun TextView.formattedHeight(height: String?) {
    // Formula for cm to feet reference -- https://byjus.com/maths/how-to-convert-cm-to-feet/
    height?.let {
        val heightInt = it.toInt()

        val totalInches = heightInt / 2.54
        val feet = (totalInches / 12).toInt()

        val inches = (totalInches - (12 * feet)).toInt()

        text = "${it}cm or ${feet}ft ${inches}in"
    }
}

@BindingAdapter("shouldGroupShow")
fun Group.shouldShow(shouldShow: Boolean) {
    visibility = if (shouldShow) {
        VISIBLE
    } else {
        GONE
    }
}

@BindingAdapter("isGroupVisible")
fun Group.isVisible(isLoading: Boolean) {
    visibility = if (isLoading) {
        GONE
    } else {
        VISIBLE
    }
}

@BindingAdapter("isShowing")
fun ProgressBar.isShowing(isLoading: Boolean) {
    visibility = if (isLoading) {
        VISIBLE
    } else {
        GONE
    }
}
