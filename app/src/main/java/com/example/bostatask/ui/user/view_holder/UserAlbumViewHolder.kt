package com.example.bostatask.ui.user.view_holder

import com.example.bostatask.base.BaseViewHolder
import com.example.bostatask.databinding.UserAlbumItemBinding
import com.example.bostatask.model.album.Album

class UserAlbumViewHolder(private val itemBinding:UserAlbumItemBinding)
    : BaseViewHolder<UserAlbumItemBinding,Album>(itemBinding) {

    override fun onBind(position: Int, model: Album) {
        binding.albumName.text = model.title
    }
}