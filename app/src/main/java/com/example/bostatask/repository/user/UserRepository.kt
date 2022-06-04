package com.example.bostatask.repository.user

import com.example.bostatask.api.UserApi
import com.example.bostatask.model.album.Album
import com.example.bostatask.model.user.User
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi) : IUserRepository {
    override suspend fun getUser(userId: String): Response<User> {
        return api.getUser(userId)
    }

    override suspend fun getUserAlbums(userId: String): Response<List<Album>> {
        return api.getUserAlbums(userId)
    }
}