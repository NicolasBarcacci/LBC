package fr.meteordesign.lbc

import fr.meteordesign.domain.Album
import fr.meteordesign.lbc.entity.ParcelableAlbum
import fr.meteordesign.lbc.entity.mapper.transform
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

private const val ALBUM_ID: Long = 1
private const val ALBUM_TITLE = "title"
private const val ALBUM_COVER_URL = "url"

class ParcelableAlbumDataMapperTests {

    @Test
    fun should_be_equals_when_transform_album_to_parcelable_album() {
        val album = Album(ALBUM_ID, ALBUM_TITLE, ALBUM_COVER_URL)

        val parcelableAlbum = transform(album)

        assertThat(parcelableAlbum.id, `is`(equalTo(album.id)))
        assertThat(parcelableAlbum.title, `is`(equalTo(album.title)))
        assertThat(parcelableAlbum.coverUrl, `is`(equalTo(album.coverUrl)))
    }

    @Test
    fun should_be_equals_when_transform_parcelable_album_to_album() {
        val parcelableAlbum = ParcelableAlbum(ALBUM_ID, ALBUM_TITLE, ALBUM_COVER_URL)

        val album = transform(parcelableAlbum)

        assertThat(album.id, `is`(equalTo(parcelableAlbum.id)))
        assertThat(album.title, `is`(equalTo(parcelableAlbum.title)))
        assertThat(album.coverUrl, `is`(equalTo(parcelableAlbum.coverUrl)))
    }
}