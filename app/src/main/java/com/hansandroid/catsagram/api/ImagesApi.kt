package com.hansandroid.catsagram.api

import com.hansandroid.catsagram.model.CatImageModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ImagesApi {

    @Headers("x-api-key: 06cbe3d2-6589-4036-99e0-ce831987d081")
    @GET("v1/images/search")
    fun getImages(@Query("breed_id") id: String, @Query("limit") count: Int) : Observable<Array<CatImageModel>>

}