package dev.vengateshm.kotlincoroutinesudemy.background_image_processing

import android.graphics.Bitmap
import android.graphics.Color

object Filter {
    fun toGreyScale(source: Bitmap): Bitmap {
        val width = source.width
        val height = source.height
        val pixels = IntArray(width * height)
        source.getPixels(pixels, 0, width, 0, 0, width, height)

        var r: Int
        var g: Int
        var b: Int
        var index: Int
        for (y in 0 until height) {
            for (x in 0 until width) {
                // Get current index in 2D matrix
                index = y * width + x
                // Get color
                r = Color.red(pixels[index])
                g = Color.green(pixels[index])
                b = Color.blue(pixels[index])
                val grey = (r + g + b) / 3
                pixels[index] = Color.rgb(grey, grey, grey)
            }
        }

        // Output
        val bitmapOut = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        bitmapOut.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmapOut
    }
}