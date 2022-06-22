package com.example.samplerecyclearview.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.samplerecyclearview.R
import com.example.samplerecyclearview.databinding.ActivityButterflyDetailBinding
import com.example.samplerecyclearview.fragments.ButterflyDetailFragment
import com.example.samplerecyclearview.fragments.MainFragment
import com.example.samplerecyclearview.interfaces.OnBackPressed
import com.example.samplerecyclearview.models.Butterfly


class ButterflyDetailActivity : AppCompatActivity(), OnBackPressed {

    private lateinit var binding: ActivityButterflyDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButterflyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val butterfly = intent.getParcelableExtra<Butterfly?>(MainFragment.PARCELABLE_OBJECT)
        val butterflyName = butterfly?.butterflyName
        val family = butterfly?.family
        val imageRes = butterfly?.imageRes
        val imagePath = butterfly?.imagePath

        val fragment =
            ButterflyDetailFragment.newInstance(butterflyName, family, imageRes, imagePath, this)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_view, fragment)
        transaction.commit()
    }

    override fun onBackIconClicked() {
        onBackPressed()
    }

}