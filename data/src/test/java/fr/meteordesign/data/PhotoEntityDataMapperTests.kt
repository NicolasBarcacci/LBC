package fr.meteordesign.data

import fr.meteordesign.data.entity.PhotoEntity
import fr.meteordesign.data.entity.mapper.transform
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

private const val PHOTO_ID: Long = 1
private const val PHOTO_URL = "url"
private const val PHOTO_ALBUM_ID: Long = 101

class PhotoEntityDataMapperTests {

    @Test
    fun should_be_equals_when_photo_entity_transform_to_photo() {
        val photoEntity = PhotoEntity(PHOTO_ID, PHOTO_URL, PHOTO_ALBUM_ID)

        val photo = transform(photoEntity)

        assertThat(photo.id, `is`(equalTo(photoEntity.id)))
        assertThat(photo.url, `is`(equalTo(photoEntity.url)))
    }
}