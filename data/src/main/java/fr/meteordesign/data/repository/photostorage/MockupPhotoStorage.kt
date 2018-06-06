package fr.meteordesign.data.repository.photostorage

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import fr.meteordesign.data.entity.AlbumEntity
import fr.meteordesign.data.entity.PhotoEntity

private const val COVER = "http://placehold.it/600/92c952"
private const val PHOTO_NAME = "accusamus beatae ad facilis cum similique qui sunt"

class PhotoStorageMockup : PhotoStorage {

    private val albums = MutableLiveData<List<AlbumEntity>>()
    private val photos = MutableLiveData<List<PhotoEntity>>()

    init {
        initAlbums()
        initPhotos()
    }

    private fun initAlbums() {
        val albums = ArrayList<AlbumEntity>()
        for (i in 0..10) {
            albums.add(AlbumEntity(i.toLong(), COVER))
        }

        this.albums.value = albums
    }

    private fun initPhotos() {
        val photos = ArrayList<PhotoEntity>()
        for (i in 0..10) {
            photos.add(PhotoEntity(i.toLong(), PHOTO_NAME, -1))
        }

        this.photos.value = photos
    }

    override fun albums(): LiveData<List<AlbumEntity>> = albums

    override fun photos(albumId: Long): LiveData<List<PhotoEntity>> = photos
}