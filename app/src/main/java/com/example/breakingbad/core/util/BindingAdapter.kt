package com.example.breakingbad.core.util

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.breakingbad.R
import com.example.breakingbad.characters_feature.domain.model.CharacterModel
import jp.wasabeef.glide.transformations.*
import jp.wasabeef.glide.transformations.internal.Utils


@BindingAdapter("setAnimation")
fun setAnimation(view: View, anim: Animation) {
    view.apply {
        animation = anim
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setCharacterName")
fun setCharacterName(textView: TextView, characterModel: CharacterModel) {
    textView.text = "${characterModel.name} (${characterModel.nickname})"
}

@BindingAdapter("setOccupation")
fun setOccupation(textView: TextView, occupation: List<String>) {
    occupation.forEach {
        textView.append("${occupation.indexOf(it) + 1}- $it \n\n")
    }
}

@BindingAdapter("setAppearance")
fun setAppearance(textView: TextView, appearance: List<Int>) {
    appearance.forEach {
        textView.append("season- $it \n\n")
    }
}


@BindingAdapter("setStatus")
fun setStatus(textView: TextView, status: String) {
    textView.text = status
    if (status != "Alive") {
        textView.setTextColor(textView.context.getColor(R.color.read))
    } else {
        textView.setTextColor(textView.context.getColor(R.color.green))
    }
}

@SuppressLint("CheckResult")
@BindingAdapter("setImage")
fun setImage(image: ImageView, url: String) {
    val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
    Glide.with(image.context)
        .load(url)
        .apply(bitmapTransform(CropTransformation(1000, 1000, CropTransformation.CropType.TOP)))
        .placeholder(R.drawable.walter_logo)
        .transition(withCrossFade(factory))
        .into(image)
}


@SuppressLint("CheckResult")
@BindingAdapter("setProfilePicture")
fun setProfilePicture(image: ImageView, url: String) {
    val multi = MultiTransformation<Bitmap>(
        CropTransformation(300, 250, CropTransformation.CropType.TOP),
        CropCircleWithBorderTransformation(Utils.toDp(4), image.context.getColor(R.color.yellow)),
    )
    Glide.with(image.context).load(url)
        .apply(bitmapTransform(multi))
        .into(image)
}


@SuppressLint("CheckResult")
@BindingAdapter("setCoverPicture")
fun setCoverPicture(image: ImageView, url: String) {
    Glide.with(image.context)
        .load(url)
        .into(image)
}