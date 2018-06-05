package fr.meteordesign.lbc.songs

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
import kotlinx.android.synthetic.main.fragment_songs.*
import javax.inject.Inject

class SongsFragmentArgs(val album: Album) {

    companion object {
        private const val ALBUM = "ALBUM"

        fun deserialize(bundle: Bundle?): SongsFragmentArgs {
            if (bundle == null) {
                throw IllegalArgumentException("A bundle is needed. Provide one through SongsFragmentArgs")
            }

            val parcelableAlbum = bundle.getParcelable<ParcelableAlbum>(ALBUM)

            return SongsFragmentArgs(parcelableAlbum.toAlbum())
        }
    }

    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(ALBUM, ParcelableAlbum(album))
        return bundle
    }
}

class SongsFragment : Fragment() {

    private val model by lazy {
        val args = SongsFragmentArgs.deserialize(arguments)
        ViewModelProviders.of(this, SongsViewModelProvider(args.album))[SongsViewModel::class.java]
    }

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dagger.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_songs, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageLoader.load(model.album.coverUrl)
                .into(image_view_songs_cover)

        val adapter = SongsAdapter(context!!)
        recycler_view_songs_list.layoutManager = LinearLayoutManager(context)
        recycler_view_songs_list.setHasFixedSize(true)
        recycler_view_songs_list.adapter = adapter

        model.songs.observe(this, Observer { adapter.songs = it!! })
    }
}