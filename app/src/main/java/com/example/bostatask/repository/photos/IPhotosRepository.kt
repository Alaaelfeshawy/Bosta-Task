package com.example.bostatask.repository.photos

import com.example.bostatask.model.album.Photo
import retrofit2.Response

interface IPhotosRepository {
    suspend fun getPhotos(albumId:String): Response<List<Photo>>
}