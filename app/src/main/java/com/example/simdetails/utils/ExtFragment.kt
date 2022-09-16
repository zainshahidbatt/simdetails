package com.example.simdetails.utils

import android.content.res.ColorStateList
import androidx.fragment.app.Fragment

fun Fragment.colorList(id: Int): ColorStateList {
    return requireActivity().colorList(id)
}