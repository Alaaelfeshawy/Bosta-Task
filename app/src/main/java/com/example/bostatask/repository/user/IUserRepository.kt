package com.example.bostatask.repository.user

import com.example.bostatask.model.album.Album
import com.example.bostatask.model.user.User
import retrofit2.Response

interface IUserRepository {
    suspend fun getUser(userId: String): Response<User>

    suspend fun  getUserAlbums(userId:String):Response<List<Album>>
}