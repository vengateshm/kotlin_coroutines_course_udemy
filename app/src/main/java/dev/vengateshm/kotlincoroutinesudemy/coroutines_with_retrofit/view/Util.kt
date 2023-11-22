package dev.vengateshm.kotlincoroutinesudemy.coroutines_with_retrofit.view

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.vengateshm.kotlincoroutinesudemy.R

fun ImageView.loadImage(url: String?) {
    val options = RequestOptions()
        .error(R.mipmap.ic_launcher_round)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}