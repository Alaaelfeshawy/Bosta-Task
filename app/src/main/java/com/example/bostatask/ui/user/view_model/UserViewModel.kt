package com.example.bostatask.ui.user.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bostatask.model.album.Album
import com.example.bostatask.model.user.User
import com.example.bostatask.repository.user.UserRepository
import com.example.bostatask.utlis.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel  @Inject constructor(
    private val userRepository: UserRepository,
    ) : ViewModel() {
     val user = SingleLiveEvent<User>()
     val userAlbum = SingleLiveEvent<List<Album>>()
     val loading = SingleLiveEvent<Boolean>()
     val errorImpl = SingleLiveEvent<String>()

    fun getUser(){
        loading.value = true
        viewModelScope.launch {
            val result = userRepository.getUser("1")
            if(result.isSuccessful){
                user.value = result.body()
                loading.value = false
            }else if (result.errorBody() != null){
                loading.value = false
                errorImpl.value = result.message()

            }
        }

    }

    fun getUserAlbum(){
        loading.value = true
        viewModelScope.launch {
            val result = userRepository.getUserAlbums("1")
            if(result.isSuccessful){
                userAlbum.value = result.body()
                loading.value = false
            }else if (result.errorBody() != null){
                loading.value = false
                errorImpl.value = result.message()

            }
        }

    }

}