package com.example.bostatask.di

import com.example.bostatask.repository.photos.IPhotosRepository
import com.example.bostatask.repository.photos.PhotoRepository
import com.example.bostatask.repository.user.IUserRepository
import com.example.bostatask.repository.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoriesModule {
    @Provides
    @Singleton
    fun providerUserRepository(userRepository: UserRepository): IUserRepository {
        return userRepository
    }

    @Provides
    @Singleton
    fun providerPhotoRepository(photoRepository: PhotoRepository): IPhotosRepository {
        return photoRepository
    }
}