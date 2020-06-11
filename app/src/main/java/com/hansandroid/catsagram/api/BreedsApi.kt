package com.hansandroid.catsagram.api

import com.hansandroid.catsagram.model.BreedModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface BreedsApi {

    @Headers("x-api-key: 06cbe3d2-6589-4036-99e0-ce831987d081")
    @GET("v1/breeds")
    fun getBreeds() : Observable<Array<BreedModel>>

}