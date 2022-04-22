package ru.itis.karakurik.spacexapp.presentation.common.extentions

import android.view.View

internal fun View.toGone() {
    this.visibility = View.GONE
}

internal fun View.toVisible() {
    this.visibility = View.VISIBLE
}

internal fun View.toInvisible() {
    this.visibility = View.INVISIBLE
}
