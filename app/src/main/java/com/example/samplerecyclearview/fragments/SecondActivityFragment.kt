package com.example.samplerecyclearview.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntegerRes
import com.example.samplerecyclearview.databinding.SecondActivityFragmentBinding


class SecondActivityFragment : Fragment() {

    companion object {
        const val IMAGE_OF_BUTTERFLY = "imageOfButterfly"
        const val IMAGE_OFBUTTERFLY_BYPATH = "imageOfButterflyByPath"
        const val TITLE_OF_BUTTERFLY = "titleOfButterfly"
        const val FAMILY_OF_BUTTERFLY = "familyOfButterfly"
        fun newInstance(
            @IntegerRes imageOfButterfly: Int?,
            titleOfButterfly: String?,
            familyOfButterfly: String?,
            imageOfButterflyByPath: String?
        ): Fragment {
            val bundle = Bundle()

            if (imageOfButterfly != null) {
                bundle.putInt(IMAGE_OF_BUTTERFLY,imageOfButterfly)
            }

            bundle.putString(IMAGE_OFBUTTERFLY_BYPATH, imageOfButterflyByPath)
            bundle.putString(FAMILY_OF_BUTTERFLY, familyOfButterfly)
            bundle.putString(TITLE_OF_BUTTERFLY, titleOfButterfly)
            val fragment = SecondActivityFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    private lateinit var binding: SecondActivityFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SecondActivityFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //arguments.getInt()
        //binding.
        val imageOfButterfly = arguments?.getInt(IMAGE_OF_BUTTERFLY)
        val imageOfButterflyByPath = arguments?.getString(IMAGE_OFBUTTERFLY_BYPATH)
        val titleOfButterfly = arguments?.getString(TITLE_OF_BUTTERFLY)
        val familyOfButterfly = arguments?.getString(FAMILY_OF_BUTTERFLY)
        val bitmapImage = BitmapFactory.decodeFile(imageOfButterflyByPath)

        if (imageOfButterfly != null && imageOfButterfly != 0) {
            binding.imageOfButterfly.setImageResource(imageOfButterfly)
        } else {
            binding.imageOfButterfly.setImageBitmap(bitmapImage)
        }
        binding.titleOfButterfly.text = titleOfButterfly
        binding.familyOfButterfly.text = familyOfButterfly

    }

}