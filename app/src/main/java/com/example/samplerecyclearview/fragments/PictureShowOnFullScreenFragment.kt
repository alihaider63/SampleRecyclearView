package com.example.samplerecyclearview.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntegerRes
import androidx.fragment.app.Fragment
import com.example.samplerecyclearview.R
import com.example.samplerecyclearview.databinding.PictureShowOnFullscreenFragmentBinding

class PictureShowOnFullScreenFragment : Fragment() {

    companion object {
        const val DRAWABLE_EXTRA = "drawable"
        const val BITMAP_EXTRA = "bitmap"
        const val IMAGE_PATH_EXTRA = "imagePath"

        fun newInstance(@IntegerRes butterfly: Int?, image: Bitmap?, imagePath: String?): Fragment {
            val args = Bundle()
            butterfly?.let { args.putInt(DRAWABLE_EXTRA, it) }


            args.putParcelable(BITMAP_EXTRA, image)
            args.putString(IMAGE_PATH_EXTRA, imagePath)
            val fragment = PictureShowOnFullScreenFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: PictureShowOnFullscreenFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = PictureShowOnFullscreenFragmentBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageRes = arguments?.getInt(DRAWABLE_EXTRA)
        val imageBitmap: Bitmap? = arguments?.getParcelable(BITMAP_EXTRA)
        val imagePath: String? = arguments?.getString(IMAGE_PATH_EXTRA)
        var imageByPathAsBitmap: Bitmap? = null
        imagePath?.let {
            imageByPathAsBitmap = BitmapFactory.decodeFile(imagePath)
        }


        when {
            imageRes != 0 -> {
                if (imageRes != null) {
                    binding.fullScreenImage.setImageResource(imageRes)
                }
            }
            imageBitmap != null -> {
                binding.fullScreenImage.setImageBitmap(imageBitmap)
            }
            imageByPathAsBitmap != null -> {
                binding.fullScreenImage.setImageBitmap(imageByPathAsBitmap)
            }
            else -> {
                binding.fullScreenImage.setImageResource(R.drawable.back)
            }
        }
    }
}