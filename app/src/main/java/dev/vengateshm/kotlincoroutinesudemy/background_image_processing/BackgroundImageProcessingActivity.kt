package dev.vengateshm.kotlincoroutinesudemy.background_image_processing

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import dev.vengateshm.kotlincoroutinesudemy.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

class BackgroundImageProcessingActivity : AppCompatActivity() {

    private val IMAGE_URL =
        "https://raw.githubusercontent.com/DevTides/JetpackDogsApp/master/app/src/main/res/drawable/dog.png"

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_image_processing)

        coroutineScope.launch {
            val originalBitmapDeferred = coroutineScope.async(Dispatchers.IO) {
                getOriginalBitmap()
            }
            val originalBitmap = originalBitmapDeferred.await()
            val filteredBitmapDeferred = coroutineScope.async(Dispatchers.Default) {
                Filter.toGreyScale(originalBitmap)
            }
            val filteredBitmap = filteredBitmapDeferred.await()
            loadImage(filteredBitmap)
        }
    }

    private fun getOriginalBitmap() =
        URL(IMAGE_URL).openStream().use {
            BitmapFactory.decodeStream(it)
        }

    private fun loadImage(bitmap: Bitmap) {
        findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        findViewById<ImageView>(R.id.imageView)
            .apply {
                setImageBitmap(bitmap)
                visibility = View.VISIBLE
            }
    }
}