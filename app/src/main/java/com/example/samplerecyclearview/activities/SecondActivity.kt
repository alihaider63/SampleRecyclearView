package com.example.samplerecyclearview.activities

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.samplerecyclearview.models.Butterfly
import com.example.samplerecyclearview.fragments.MainActivityFragment
import com.example.samplerecyclearview.R
import com.example.samplerecyclearview.fragments.SecondActivityFragment
import com.example.samplerecyclearview.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    //It is not valid way to get view in variable
    //val myImage = findViewById<ImageView>(R.id.)

    //It is valid way
    lateinit var butterflyImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_second)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        butterflyImageView = findViewById(R.id.image)
//        val image = intent.getIntExtra("drawable", R.drawable.back)
//        val imageReceived: Bitmap? = intent.getParcelableExtra("bitmap")
//        val imagePath = intent.getStringExtra("image_path")
//        val imageByPath = BitmapFactory.decodeFile(imagePath)

        val butterfly = intent.getParcelableExtra<Butterfly?>(MainActivityFragment.PARCELABLE_OBJECT)
        val imageOfButterfly = butterfly?.butterfly
        val imageOfButterflyByPath = butterfly?.imagePath
        val titleOfButterfly = butterfly?.name
        val familyOfButterfly = butterfly?.family
        val fragment = SecondActivityFragment.newInstance(
            imageOfButterfly,
            titleOfButterfly,
            familyOfButterfly,
            imageOfButterflyByPath
        )
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_view, fragment)
        transaction.commit()
        supportFragmentManager.beginTransaction().add(R.id.fragment_view, fragment).commit()
        /*
        when {
            image != R.drawable.back -> {
                binding.myImage.setImageResource(image)
            }
            imageReceived != null -> {
                imageReceived?.let {
                    binding.myImage.setImageBitmap(it)
                }
            }
            else -> {
                imageByPath?.let {
                    binding.myImage.setImageBitmap(it)
                }

            }
        }
        */
    }

}