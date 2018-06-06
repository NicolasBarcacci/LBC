package fr.meteordesign.data.repository.musicstorage

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import fr.meteordesign.data.entity.AlbumEntity
import fr.meteordesign.data.entity.TrackEntity

private const val COVER = "http://placehold.it/600/92c952"
private const val TRACK_NAME = "accusamus beatae ad facilis cum similique qui sunt"

class MusicStorageMockup : MusicStorage {

    private val albums = MutableLiveData<List<AlbumEntity>>()
    private val tracks = MutableLiveData<List<TrackEntity>>()

    init {
        initAlbums()
        initTracks()
    }

    private fun initAlbums() {
        val albums = ArrayList<AlbumEntity>()
        for (i in 0..10) {
            albums.add(AlbumEntity(i.toLong(), COVER))
        }

        this.albums.value = albums
    }

    private fun initTracks() {
        val tracks = ArrayList<TrackEntity>()
        for (i in 0..10) {
            tracks.add(TrackEntity(i.toLong(), TRACK_NAME, -1))
        }

        this.tracks.value = tracks
    }

    override fun albums(): LiveData<List<AlbumEntity>> = albums

    override fun tracks(albumId: Long): LiveData<List<TrackEntity>> = tracks
}