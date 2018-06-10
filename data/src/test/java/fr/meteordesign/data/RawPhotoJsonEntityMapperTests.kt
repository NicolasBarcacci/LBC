package fr.meteordesign.data

import fr.meteordesign.data.entity.JsonPhoto
import fr.meteordesign.data.entity.mapper.transformToAlbumEntity
import fr.meteordesign.data.entity.mapper.transformToPhotoEntity
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

private const val ALBUM_ID: Long = 101
private const val PHOTO_ID: Long = 1
private const val TITLE = "title"
private const val URL = "http://photo"
private const val THUMBNAIL_URL = "http://thumbnailPhoto"

class RawPhotoJsonEntityMapperTests {

    lateinit var jsonPhoto: JsonPhoto

    @Before
    fun init() {
        jsonPhoto = JsonPhoto(ALBUM_ID, PHOTO_ID, TITLE, URL, THUMBNAIL_URL)
    }

    @Test
    fun should_be_equals_when_json_photo_transform_to_photo_entity() {
        val photoEntity = transformToPhotoEntity(jsonPhoto)

        assertThat(photoEntity.id, `is`(equalTo(jsonPhoto.id)))
        assertThat(photoEntity.url, `is`(equalTo(jsonPhoto.url)))
        assertThat(photoEntity.albumId, `is`(equalTo(jsonPhoto.albumId)))
    }

    @Test
    fun should_be_equals_when_json_photo_transform_to_album_entity() {
        val albumEntity = transformToAlbumEntity(jsonPhoto)

        assertThat(albumEntity.id, `is`(equalTo(jsonPhoto.albumId)))
        assertThat(albumEntity.title, `is`(equalTo(jsonPhoto.title)))
        assertThat(albumEntity.coverUrl, `is`(equalTo(jsonPhoto.url)))
    }
}