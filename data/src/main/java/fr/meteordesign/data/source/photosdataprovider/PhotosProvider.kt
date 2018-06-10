package fr.meteordesign.data.source.photosdataprovider

import fr.meteordesign.data.entity.JsonPhoto
import io.reactivex.Observable
import retrofit2.http.GET

interface PhotosProvider  {

    @GET("photos")
    fun photos(): Observable<List<JsonPhoto>>
}