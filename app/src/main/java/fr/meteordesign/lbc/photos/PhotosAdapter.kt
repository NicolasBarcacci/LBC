package fr.meteordesign.lbc.photos

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fr.meteordesign.domain.Photo
import fr.meteordesign.lbc.R

class PhotosAdapter(context: Context) : RecyclerView.Adapter<PhotoViewHolder>() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    var photos: List<Photo> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = photos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
            PhotoViewHolder(inflater.inflate(R.layout.list_item_photo, parent, false))

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }
}

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val photoTitle = itemView.findViewById<TextView>(R.id.text_view_photo_title)

    fun bind(photo: Photo) {
        photoTitle.text = photo.title
    }
}