package fr.meteordesign.data

import fr.meteordesign.data.entity.AlbumEntity
import fr.meteordesign.data.entity.mapper.transform
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

private const val ALBUM_ID: Long = 1
private const val ALBUM_TITLE = "title"
private const val ALBUM_COVER_URL = "url"

class AlbumEntityDataMapperTests {

    @Test
    fun should_be_equals_when_album_entity_transform_to_album() {
        val albumEntity = AlbumEntity(ALBUM_ID, ALBUM_TITLE, ALBUM_COVER_URL)

        val album = transform(albumEntity)

        assertThat(album.id, `is`(equalTo(albumEntity.id)))
        assertThat(album.title, `is`(equalTo(albumEntity.title)))
        assertThat(album.coverUrl, `is`(equalTo(albumEntity.coverUrl)))
    }
}