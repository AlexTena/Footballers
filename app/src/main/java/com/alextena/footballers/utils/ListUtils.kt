@file:Suppress("DEPRECATION")

package com.alextena.footballers.utils

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.alextena.footballers.R
import com.alextena.footballers.utils.ExtensionUtils.hide
import com.alextena.footballers.utils.ExtensionUtils.show

object ListUtils {

    fun loadingList(
        recyclerView: RecyclerView,
        progressBar: ProgressBar,
        imageView: ImageView
    ) {
        recyclerView.hide()
        progressBar.show()
        imageView.hide()
    }

    fun showOrHideList(
        recyclerView: RecyclerView,
        progressBar: ProgressBar,
        imageView: ImageView,
        size: Int
    ) {
        progressBar.hide()
        if (size > 0) {
            recyclerView.show()
            imageView.hide()
        } else {
            recyclerView.hide()
            imageView.show()
        }
    }

//    fun initProgressBar(progressBar: ProgressBar) {
//        progressBar.indeterminateDrawable.setColorFilter(
//            App.getContext().resources.getColor(R.color.primary_color),
//            android.graphics.PorterDuff.Mode.SRC_IN
//        )
//    }
}