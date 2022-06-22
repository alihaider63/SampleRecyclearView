package com.example.samplerecyclearview.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntegerRes
import androidx.fragment.app.Fragment
import com.example.samplerecyclearview.databinding.FragmentButterflyDetailBinding
import com.example.samplerecyclearview.interfaces.OnBackPressed


class ButterflyDetailFragment : Fragment() {


    companion object {
        const val IMAGE_RES = "image"
        const val IMAGE_PATH = "imagePath"
        const val BUTTERFLY_NAME = "butterflyName"
        const val BUTTERFLY_FAMILY = "butterflyFamily"
        var listenerOfBackPressed: OnBackPressed? = null

        fun newInstance(
            butterflyName: String?,
            butterflyFamily: String?,
            @IntegerRes imageRes: Int?,
            imagePath: String?,
            listener: OnBackPressed
        ) = ButterflyDetailFragment().apply {
            arguments = Bundle().apply {
                imageRes?.let {
                    putInt(IMAGE_RES, imageRes)
                }
                putString(IMAGE_PATH, imagePath)
                putString(BUTTERFLY_FAMILY, butterflyFamily)
                putString(BUTTERFLY_NAME, butterflyName)
            }
            listenerOfBackPressed = listener
        }
    }

    private lateinit var binding: FragmentButterflyDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentButterflyDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageRes = arguments?.getInt(IMAGE_RES)
        val imagePath = arguments?.getString(IMAGE_PATH)
        val butterflyName = arguments?.getString(BUTTERFLY_NAME)
        val butterflyFamily = arguments?.getString(BUTTERFLY_FAMILY)
        val bitmapImage = BitmapFactory.decodeFile(imagePath)

        if (imageRes != null && imageRes != 0) {
            binding.butterflyImageView.setImageResource(imageRes)
        } else {
            binding.butterflyImageView.setImageBitmap(bitmapImage)
        }
        binding.butterflyNameInput.text = butterflyName
        binding.familyInput.text = butterflyFamily

        binding.backIcon.setOnClickListener {
            listenerOfBackPressed?.onBackIconClicked()
        }
    }
}