package com.example.bostatask.ui.album

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bostatask.R
import com.example.bostatask.base.BaseAdapter
import com.example.bostatask.databinding.AlbumItemBinding
import com.example.bostatask.databinding.FragmentAlbumBinding
import com.example.bostatask.model.album.Photo
import com.example.bostatask.ui.album.view_holder.AlbumViewHolder
import com.example.bostatask.ui.album.view_model.AlbumsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment : Fragment() {
    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!
    private val viewModel : AlbumsViewModel by lazy {
        ViewModelProvider(this)[AlbumsViewModel::class.java]
    }
    private val adapter: BaseAdapter<Photo, AlbumItemBinding> by lazy {
        BaseAdapter(
            R.layout.album_item,
            {
                val bundle = Bundle()
        bundle.putString("URL", it.url)
                findNavController().navigate(R.id.action_albumFragment_to_imageViewer,bundle)

            }
        ) {
            AlbumViewHolder(it)
        }
    }
    private val photosList = ArrayList<Photo>()
    private var filteredPhotoList : ArrayList<Photo>? = null
    private var albumId:Int?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            albumId = it.getInt("ALBUM_ID")
        }
        photosList.clear()
        binding.albums.adapter = adapter
        viewModel.getPhotos(albumId ?: 0)
        binding.search.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(photosList.isNotEmpty()){
                    filteredPhotoList = ArrayList()
                    filteredPhotoList =photosList.filter {
                         it.title?.contains(s,true) == true} as ArrayList<Photo>
                    adapter.setDataList(filteredPhotoList ?: ArrayList())
                }
            }
            override fun afterTextChanged(s: Editable) {}
        })
        viewModel.photos.observe(viewLifecycleOwner){
            photos-> photos?.let {
            adapter.setDataList(it)
            photosList.addAll(it)
        }

        }
        viewModel.loading.observe(viewLifecycleOwner){
            if(it == true){
                binding.pbLoading.visibility=View.VISIBLE
            }else{
                binding.pbLoading.visibility=View.GONE
            }
        }
    }
}