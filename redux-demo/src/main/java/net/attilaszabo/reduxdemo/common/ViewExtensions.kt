package net.attilaszabo.reduxdemo.common

import android.view.View

fun View.setVisibility(isVisible: Boolean) {
    this.visibility = when (isVisible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}
