package com.example.simdetails.component

import android.content.res.ColorStateList
import androidx.annotation.DrawableRes
import com.example.simdetails.databinding.ServerComponentBinding

class ServerComponent(
    binding: ServerComponentBinding,
    titleText: String,
    @DrawableRes img: Int,
    backgroundColor: ColorStateList
) {
    init {
        binding.apply {
            tvTitle.text = titleText
            picPng.setImageResource(img)
            cvCard.setCardBackgroundColor(backgroundColor)
        }
    }
}