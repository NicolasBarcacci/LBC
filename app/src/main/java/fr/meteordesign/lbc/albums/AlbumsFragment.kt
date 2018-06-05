package fr.meteordesign.lbc.albums

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import fr.meteordesign.domain.Album
import fr.meteordesign.lbc.R
import kotlinx.android.synthetic.main.fragment_albums.*

class AlbumsFragment: Fragment(), AlbumsAdapter.OnItemClickListener{
    private val model by lazy { ViewModelProviders.of(this, AlbumsViewModelProvider()) [AlbumsViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_albums, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val albumsAdapter = AlbumsAdapter(context!!)
        albumsAdapter.onItemClickListener = this
        recycler_view_albums_list.layoutManager =
                GridLayoutManager(context, resources.getInteger(R.integer.albums_column_count))
        recycler_view_albums_list.setHasFixedSize(true)
        recycler_view_albums_list.adapter = albumsAdapter

        model.albums.observe(this, Observer { albumsAdapter.albums = it!! })
    }

    override fun onItemClick(album: Album) {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_albumsFragment_to_songsFragment)
    }
}
