package com.example.bostatask.api

import com.example.bostatask.model.album.Album
import com.example.bostatask.model.user.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String) : Response<User>

    @GET("albums")
    suspend fun getUserAlbums(@Query("userId") userId:String) : Response<List<Album>>
}