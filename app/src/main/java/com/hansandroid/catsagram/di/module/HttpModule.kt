package com.hansandroid.catsagram.di.module

import com.hansandroid.catsagram.api.BreedsApi
import com.hansandroid.catsagram.api.ImagesApi
import com.hansandroid.catsagram.util.URL
import dagger.Module
import dagger.Provides
import de.hdodenhof.circleimageview.BuildConfig
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class HttpModule {

    @Provides
    @Singleton
    fun provideHttpLogging() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl(URL.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideBreedsApi(retrofit: Retrofit) : BreedsApi = retrofit.create(BreedsApi::class.java)

    @Provides
    @Singleton
    fun provideImageApi(retrofit: Retrofit) : ImagesApi = retrofit.create(ImagesApi::class.java)
}