package com.example.bostatask.ui.imag_viewer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.bostatask.databinding.FragmentImageViewerBinding
import com.squareup.picasso.Picasso
import kotlin.math.max


class ImageViewerFragment : Fragment() {
    private var _binding: FragmentImageViewerBinding? = null
    private val binding get() = _binding!!
    private var url : String?=null
    private var scaleGestureDetector: ScaleGestureDetector? = null
    private val mScaleFactor = 1.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageViewerBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener(mScaleFactor , binding.image))
        arguments?.let {
            url = it.getString("URL")
        }
        if(url != null){
            Picasso.get()
                .load(url)
                .into(binding.image )
        }
        binding.root.setOnTouchListener { _, event ->
            scaleGestureDetector?.onTouchEvent(event)
            true
        }
    }
    private inner class ScaleListener(var mScaleFactor : Float , var imageView : ImageView) : SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = max(0.1f, mScaleFactor.coerceAtMost(10.0f))
            imageView.scaleX = mScaleFactor
            imageView.scaleY = mScaleFactor
            return true
        }
    }

}