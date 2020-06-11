package com.hansandroid.catsagram.api

import com.hansandroid.catsagram.model.CatImageModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header

interface ImagesApi {

    @GET("/images/search")
    fun getBreeds(@Header("x-api-key") token: String) : Observable<Array<CatImageModel>>

}