package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

//creating moshi builder
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// creating Retrofit Object with moshi builder
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//Creating object of MarsApiService
object MarsApi{
    val retrofitServices : MarsApiService by lazy {
    retrofit.create(MarsApiService::class.java)
    }
}
// creating interface
interface MarsApiService{
    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>
}