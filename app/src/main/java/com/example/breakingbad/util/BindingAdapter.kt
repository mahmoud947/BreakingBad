package com.example.breakingbad.util

import android.view.View
import android.view.animation.Animation
import androidx.databinding.BindingAdapter

@BindingAdapter("setAnimation")
fun setAnimation(view: View, anim: Animation) {
    view.apply {
        animation = anim
    }

}