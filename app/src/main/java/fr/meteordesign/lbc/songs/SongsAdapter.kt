package fr.meteordesign.lbc.songs

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fr.meteordesign.domain.Song
import fr.meteordesign.lbc.R

class SongsAdapter(context: Context) : RecyclerView.Adapter<SongViewHolder>() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    var songs: List<Song> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = songs.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder =
            SongViewHolder(inflater.inflate(R.layout.list_item_song, parent, false))

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(songs[position])
    }
}

class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val songTitle = itemView.findViewById<TextView>(R.id.text_view_song_title)

    fun bind(song: Song) {
        songTitle.text = song.title
    }
}