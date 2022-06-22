package com.example.samplerecyclearview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.samplerecyclearview.fragments.MainFragment
import com.example.samplerecyclearview.R
import com.example.samplerecyclearview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState ?: showFragment()

    }

    private fun showFragment() {
        val fragment = MainFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_fragment_view, fragment)
            .commit()
    }
}
