package fr.meteordesign.lbc.tracks

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.meteordesign.domain.Album
import fr.meteordesign.lbc.ParcelableAlbum
import fr.meteordesign.lbc.R
import fr.meteordesign.lbc.dagger
import fr.meteordesign.lbc.imageloader.ImageLoader
import kotlinx.android.synthetic.main.fragment_tracks.*
import javax.inject.Inject

class TracksFragmentArgs(val album: Album) {

    companion object {
        private const val ALBUM = "ALBUM"

        fun deserialize(bundle: Bundle?): TracksFragmentArgs {
            if (bundle == null) {
                throw IllegalArgumentException("A bundle is needed. Provide one through TracksFragmentArgs")
            }

            val parcelableAlbum = bundle.getParcelable<ParcelableAlbum>(ALBUM)

            return TracksFragmentArgs(parcelableAlbum.toAlbum())
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(ALBUM, ParcelableAlbum(album))
        return bundle
    }
}

class TracksFragment : Fragment() {

    private val model by lazy {
        val args = TracksFragmentArgs.deserialize(arguments)
        ViewModelProviders.of(this, TracksViewModelProvider(args.album))[TracksViewModel::class.java]
    }

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dagger.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_tracks, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageLoader.load(model.album.coverUrl)
                .into(image_view_tracks_cover)

        val adapter = TracksAdapter(context!!)
        recycler_view_tracks_list.layoutManager = LinearLayoutManager(context)
        recycler_view_tracks_list.setHasFixedSize(true)
        recycler_view_tracks_list.adapter = adapter

        model.tracks.observe(this, Observer { adapter.tracks = it!! })
    }
}