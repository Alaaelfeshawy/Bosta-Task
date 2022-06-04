package com.example.bostatask.ui.album.view_holder

import android.view.View
import android.widget.ProgressBar
import com.example.bostatask.base.BaseViewHolder
import com.example.bostatask.databinding.AlbumItemBinding
import com.example.bostatask.model.album.Photo
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class AlbumViewHolder (private var itemBinding: AlbumItemBinding)
    : BaseViewHolder<AlbumItemBinding, Photo>(itemBinding) {

    override fun onBind(position: Int, model: Photo) {
        itemBinding = binding
        binding.pbLoading.visibility = View.VISIBLE
        val progressView : ProgressBar = binding.pbLoading;
        Picasso.get()
            .load(model.url)
            .into(binding.image,object: Callback{
                override fun onSuccess() {
                    progressView.visibility = View.GONE
                }
                override fun onError(e: Exception?) {
                    System.err.println(e.toString())
                }
            })
    }

}