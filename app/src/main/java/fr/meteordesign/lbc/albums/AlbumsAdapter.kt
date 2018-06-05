package fr.meteordesign.lbc.albums

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import fr.meteordesign.domain.Album
import fr.meteordesign.lbc.R
import fr.meteordesign.lbc.dagger
import fr.meteordesign.lbc.imageloader.ImageLoader
import javax.inject.Inject

class AlbumsAdapter(context: Context) : RecyclerView.Adapter<AlbumViewHolder>() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @Inject
    lateinit var imageLoader: ImageLoader

    init {
        dagger.inject(this)
    }

    var albums: List<Album> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = albums.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
            AlbumViewHolder(imageLoader,
                    inflater.inflate(R.layout.list_item_album, parent, false))

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albums[position])
    }
}

class AlbumViewHolder(private val imageLoader: ImageLoader, itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val cover = itemView.findViewById<ImageView>(R.id.image_view_albums_cover)

    fun bind(album: Album) {
        imageLoader.load(album.coverUrl)
                .into(cover)
    }
}
