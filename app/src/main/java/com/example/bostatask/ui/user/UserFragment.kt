package com.example.bostatask.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bostatask.R
import com.example.bostatask.base.BaseAdapter
import com.example.bostatask.databinding.FragmentUserBinding
import com.example.bostatask.databinding.UserAlbumItemBinding
import com.example.bostatask.model.album.Album
import com.example.bostatask.ui.user.view_holder.UserAlbumViewHolder
import com.example.bostatask.ui.user.view_model.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel : UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }
    private val adapter: BaseAdapter<Album, UserAlbumItemBinding> by lazy {
        BaseAdapter(
            R.layout.user_album_item,
            {
                val bundle = Bundle()
                bundle.putInt("ALBUM_ID",it.id ?: 0)
                findNavController().navigate(R.id.action_userFragment_to_albumFragment , bundle)
            }
        ) {
            UserAlbumViewHolder(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userAlbums.adapter = adapter
        viewModel.getUser()
        viewModel.getUserAlbum()
        viewModel.user.observe(viewLifecycleOwner) {
            binding.name.text = it?.name
            binding.address.text = it?.address?.street +" "+ it?.address?.suite +" "+ it?.address?.city+" ,"
            binding.geo.text = it?.address?.geo?.lat.toString() + " - "+it?.address?.geo?.lng.toString()
        }
        viewModel.userAlbum.observe(viewLifecycleOwner) {
            it?.let { it1 -> adapter.setDataList(it1) }
            adapter.notifyDataSetChanged()
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if(it == true){
                binding.pbLoading.visibility=View.VISIBLE
            }else{
                binding.pbLoading.visibility=View.GONE
            }
        }
    }
}