package com.example.breakingbad.util

import android.annotation.SuppressLint
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide
import com.example.breakingbad.R

@BindingAdapter("setAnimation")
fun setAnimation(view: View, anim: Animation) {
    view.apply {
        animation = anim
    }
}

@BindingAdapter("setImage")
fun setImage(image: ImageView, url: String) {
    image.load(url) {
        placeholder(R.drawable.walter_logo)
        crossfade(true)
        crossfade(400)
    }
}

/*
 Glide.with(requireContext())
                    .load(character.img).apply {
                        thumbnail(Glide.with(requireContext()).load(R.drawable.loading))
                        useAnimationPool
                        circleCrop()
                        into(binding.ivCharacterProfile)
                    }
                Glide.with(requireContext())
                    .load(character.img).apply {
                        thumbnail(Glide.with(requireContext()).load(R.drawable.loading))
                        useAnimationPool
                        centerCrop()
                        into(binding.ivCharacterCover)
                    }

 */


@SuppressLint("CheckResult")
@BindingAdapter("setProfilePicture")
fun setProfilePicture(image: ImageView, url: String) {
    Glide.with(image.context).load(url).apply {
        useAnimationPool
        circleCrop()
        into(image)
    }
}


@SuppressLint("CheckResult")
@BindingAdapter("setCoverPicture")
fun setCoverPicture(image: ImageView, url: String) {
    Glide.with(image.context)
        .load(url)
        .centerCrop()
}