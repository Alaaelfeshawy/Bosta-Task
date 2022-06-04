package com.example.bostatask.repository.photos

import com.example.bostatask.api.PhotoApi
import com.example.bostatask.model.album.Photo
import retrofit2.Response
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val api: PhotoApi) : IPhotosRepository {
    override suspend fun getPhotos(albumId: String): Response<List<Photo>> {
        return api.getPhotos(albumId)
    }

}