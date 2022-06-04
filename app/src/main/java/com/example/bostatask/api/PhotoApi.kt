package com.example.bostatask.api

import com.example.bostatask.model.album.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    @GET("photos")
    suspend fun getPhotos(@Query("albumId") albumId:String) : Response<List<Photo>>
}