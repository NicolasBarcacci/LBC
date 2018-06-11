package fr.meteordesign.lbc.features.albums

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import fr.meteordesign.domain.Album
import fr.meteordesign.lbc.R
import fr.meteordesign.lbc.dagger
import fr.meteordesign.lbc.imageloader.ImageLoader
import javax.inject.Inject

class AlbumsDiffUtil(private val oldAlbums: List<Album>, private val newAlbums: List<Album>): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldAlbums.size

    override fun getNewListSize(): Int = newAlbums.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldAlbums[oldItemPosition].id == newAlbums[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldAlbums[oldItemPosition] == newAlbums[newItemPosition]
}

class AlbumsAdapter(context: Context) : RecyclerView.Adapter<AlbumViewHolder>() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @Inject
    lateinit var imageLoader: ImageLoader

    var onItemClickListener: OnItemClickListener? = null

    init {
        dagger.inject(this)
    }

    var albums: List<Album> = ArrayList()
        set(value) {
            val result = DiffUtil.calculateDiff(AlbumsDiffUtil(field, value))
            field = value
            result.dispatchUpdatesTo(this)
        }

    override fun getItemCount(): Int = albums.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val holder = AlbumViewHolder(imageLoader,
                inflater.inflate(R.layout.list_item_album, parent, false))
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(getAlbum(holder.adapterPosition))
        }

        return holder
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getAlbum(position))
    }

    private fun getAlbum(position: Int): Album = albums[position]

    interface OnItemClickListener {
        fun onItemClick(album: Album)
    }
}

class AlbumViewHolder(private val imageLoader: ImageLoader, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val coverImageView = itemView.findViewById<ImageView>(R.id.image_view_albums_cover)
    private val titleTextView = itemView.findViewById<TextView>(R.id.text_view_albums_title)

    fun bind(album: Album) {
        imageLoader.load(album.coverUrl)
                .into(coverImageView)
        titleTextView.text = album.title.capitalize()
    }
}
