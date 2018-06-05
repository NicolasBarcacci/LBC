package fr.meteordesign.lbc.mockup

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import fr.meteordesign.domain.Album
import fr.meteordesign.domain.Song
import fr.meteordesign.domain.repository.MusicRepository

private const val COVER = "http://placehold.it/600/92c952"
private const val SONG_NAME = "accusamus beatae ad facilis cum similique qui sunt"

class MusicRepositoryMockup : MusicRepository {

    private val albums = MutableLiveData<List<Album>>()
    private val songs = MutableLiveData<List<Song>>()

    init {
        initAlbums()
        intSongs()
    }

    private fun initAlbums() {
        val albums = ArrayList<Album>()
        for (i in 0..10) {
            albums.add(Album(i.toLong(), COVER))
        }

        this.albums.value = albums
    }

    private fun intSongs() {
        val songs = ArrayList<Song>()
        for (i in 0..10) {
            songs.add(Song(i.toLong(), SONG_NAME))
        }

        this.songs.value = songs
    }

    override fun albumsByPage(): LiveData<List<Album>> = albums

    override fun songs(albumId: Long): LiveData<List<Song>> = songs
}