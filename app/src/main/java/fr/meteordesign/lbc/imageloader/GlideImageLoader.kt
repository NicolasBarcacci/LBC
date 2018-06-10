package fr.meteordesign.lbc.imageloader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fr.meteordesign.lbc.R

class GlideImageLoader(private val context: Context) : ImageLoader {

    private lateinit var url: String

    override fun load(url: String): GlideImageLoader {
        this.url = url
        return this
    }

    override fun into(imageView: ImageView) {
        Glide.with(context)
                .load(url)
                .apply(requestOptions())
                .into(imageView)
    }

    private fun requestOptions(): RequestOptions = RequestOptions()
            .error(R.drawable.ic_photo_place_holder)
}