package fr.meteordesign.lbc.tracks

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fr.meteordesign.domain.Track
import fr.meteordesign.lbc.R

class TracksAdapter(context: Context) : RecyclerView.Adapter<TrackViewHolder>() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    var tracks: List<Track> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = tracks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder =
            TrackViewHolder(inflater.inflate(R.layout.list_item_track, parent, false))

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(tracks[position])
    }
}

class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val trackTitle = itemView.findViewById<TextView>(R.id.text_view_track_title)

    fun bind(track: Track) {
        trackTitle.text = track.title
    }
}