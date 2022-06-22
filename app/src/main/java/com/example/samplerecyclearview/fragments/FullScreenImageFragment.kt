package com.example.samplerecyclearview.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntegerRes
import androidx.fragment.app.Fragment
import com.example.samplerecyclearview.databinding.FragmentFullscreenImageBinding

class FullScreenImageFragment : Fragment() {

    companion object {
        const val IMAGE_RES = "imageRes"
        const val IMAGE_BITMAP = "imageBitmap"
        const val IMAGE_PATH = "imagePath"

        fun newInstance(@IntegerRes imageRes: Int?, imageBitmap: Bitmap?, imagePath: String?) =
            FullScreenImageFragment().apply {
                arguments = Bundle().apply {
                    imageRes?.let { putInt(IMAGE_RES, it) }
                    putParcelable(IMAGE_BITMAP, imageBitmap)
                    putString(IMAGE_PATH, imagePath)
                }
            }
    }

    private lateinit var binding: FragmentFullscreenImageBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentFullscreenImageBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageRes = arguments?.getInt(IMAGE_RES)
        val imageBitmap: Bitmap? = arguments?.getParcelable(IMAGE_BITMAP)
        val imagePath: String? = arguments?.getString(IMAGE_PATH)
        var butterflyImageBitmap: Bitmap? = null
        imagePath?.let {
            butterflyImageBitmap = BitmapFactory.decodeFile(imagePath)
        }


        when {
            imageRes != 0 && imageRes != null-> {
                binding.imageView.setImageResource(imageRes)
            }
            imageBitmap != null-> {
                binding.imageView.setImageBitmap(imageBitmap)
            }
            butterflyImageBitmap != null-> {
                binding.imageView.setImageBitmap(butterflyImageBitmap)
            }
            else-> {
                //do Nothing
            }
        }
    }
}