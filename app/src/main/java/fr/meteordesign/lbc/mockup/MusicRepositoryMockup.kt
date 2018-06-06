package fr.meteordesign.lbc.mockup

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.Track
import fr.meteordesign.domain.repository.MusicRepository

private const val COVER = "http://placehold.it/600/92c952"
private const val TRACK_NAME = "accusamus beatae ad facilis cum similique qui sunt"

class MusicRepositoryMockup : MusicRepository {

    private val albums = MutableLiveData<List<Album>>()
    private val tracks = MutableLiveData<List<Track>>()

    init {
        initAlbums()
        initTracks()
    }

    private fun initAlbums() {
        val albums = ArrayList<Album>()
        for (i in 0..10) {
            albums.add(Album(i.toLong(), COVER))
        }

        this.albums.value = albums
    }

    private fun initTracks() {
        val tracks = ArrayList<Track>()
        for (i in 0..10) {
            tracks.add(Track(i.toLong(), TRACK_NAME))
        }

        this.tracks.value = tracks
    }

    override fun albums(): LiveData<List<Album>> = albums

    override fun tracks(albumId: Long): LiveData<List<Track>> = tracks
}