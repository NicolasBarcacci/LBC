package fr.meteordesign.lbc.albums

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.meteordesign.lbc.R

class AlbumsFragment: Fragment() {

    val model by lazy { ViewModelProviders.of(this, AlbumsViewModelProvider()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_albums, container, false)
}