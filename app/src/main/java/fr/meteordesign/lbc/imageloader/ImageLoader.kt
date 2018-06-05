package fr.meteordesign.lbc.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun load(url: String): ImageLoader
    fun into(imageView: ImageView)
}