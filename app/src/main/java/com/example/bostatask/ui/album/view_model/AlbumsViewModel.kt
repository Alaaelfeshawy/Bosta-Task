package com.example.bostatask.ui.album.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bostatask.model.album.Photo
import com.example.bostatask.repository.photos.PhotoRepository
import com.example.bostatask.utlis.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel@Inject constructor(
    private val photoRepository: PhotoRepository,
) : ViewModel() {
     val photos = SingleLiveEvent<List<Photo>>()
     val loading= SingleLiveEvent<Boolean>()
     val error = SingleLiveEvent<String>()
    fun getPhotos(albumId : Int){
        loading.value = true
        viewModelScope.launch {
            val result = photoRepository.getPhotos("$albumId")
            if(result.isSuccessful){
                loading.value = false
                photos.value = result.body()
            }else if (result.errorBody() != null){
                loading.value = false
                error.value = result.message()

            }
        }

    }

}