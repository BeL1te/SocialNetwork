package ru.fillandroid.socialnetwork.common.extensions

import android.view.View
import androidx.core.view.isVisible

fun View.gone() {
    visibility = View.GONE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.toggleVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}