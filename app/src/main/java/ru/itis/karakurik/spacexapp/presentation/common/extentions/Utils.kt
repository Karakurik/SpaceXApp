package ru.itis.karakurik.spacexapp.presentation.common.extentions

import android.content.Context
import android.widget.Toast

internal fun Context.toastLong(message: String?) =
    message?.let {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

internal fun Context.toastShort(message: String?) =
    message?.let {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
