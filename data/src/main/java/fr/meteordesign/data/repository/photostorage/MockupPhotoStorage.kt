package fr.meteordesign.data.repository.photostorage

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import fr.meteordesign.data.entity.AlbumEntity
import fr.meteordesign.data.entity.PhotoEntity

private const val URL = "http://placehold.it/600/92c952"
private const val NAME = "accusamus beatae ad facilis cum similique qui sunt"

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
            albums.add(AlbumEntity(i.toLong(), NAME, URL))
        }

        this.albums.value = albums
    }

    override fun insert(albumEntity: AlbumEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initPhotos() {
        val photos = ArrayList<PhotoEntity>()
        for (i in 0..10) {
            photos.add(PhotoEntity(i.toLong(), URL, -1))
        }

        this.photos.value = photos
    }

    override fun albums(): LiveData<List<AlbumEntity>> = albums

    override fun photos(albumId: Long): LiveData<List<PhotoEntity>> = photos

    override fun insert(photoEntity: PhotoEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}