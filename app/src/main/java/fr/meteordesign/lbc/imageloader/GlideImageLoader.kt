package fr.meteordesign.lbc.imageloader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader(private val context: Context) : ImageLoader{

    private lateinit var url: String

    override fun load(url: String): GlideImageLoader {
        this.url = url
        return this
    }

    override fun into(imageView: ImageView) {
        Glide.with(context)
                .load(url)
                .into(imageView)
    }
}