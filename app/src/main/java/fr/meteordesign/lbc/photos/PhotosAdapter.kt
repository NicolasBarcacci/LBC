package fr.meteordesign.lbc.photos

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import fr.meteordesign.domain.Photo
import fr.meteordesign.lbc.R
import fr.meteordesign.lbc.dagger
import fr.meteordesign.lbc.imageloader.ImageLoader
import javax.inject.Inject

class PhotosAdapter(context: Context) : RecyclerView.Adapter<PhotoViewHolder>() {

    @Inject
    lateinit var imageLoader: ImageLoader

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    var photos: List<Photo> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        dagger.inject(this)
    }

    override fun getItemCount(): Int = photos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
            PhotoViewHolder(imageLoader, inflater.inflate(R.layout.list_item_photo, parent, false))

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }
}

class PhotoViewHolder(private val imageLoader: ImageLoader, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val photoImageView = itemView.findViewById<ImageView>(R.id.image_view_photo_title)

    fun bind(photo: Photo) {
        imageLoader.load(photo.url)
                .into(photoImageView)
    }
}