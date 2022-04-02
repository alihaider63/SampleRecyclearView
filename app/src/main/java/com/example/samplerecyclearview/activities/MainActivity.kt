package com.example.samplerecyclearview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.samplerecyclearview.fragments.MainActivityFragment
import com.example.samplerecyclearview.R


class MainActivity : AppCompatActivity() {

    companion object{
        const val DRAWABLE_EXTRA = "drawable"
        const val BITMAP_EXTRA = "bitmap"
        const val IMAGE_PATH_EXTRA = "image_path"
    }
    private var backPressTime: Long = 0
    private var backToast: Toast? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = MainActivityFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.main_fragment_view, fragment)
                .commit()

        //adapter = MyAdapter(butterflyObjects, ::onButterClicked)
        /*adapter = MyAdapter(butterflyObjects,this)
        recyclerView = findViewById(R.id.listOfButterfly)
        addNewItemButton = findViewById(R.id.addNewItem)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addNewItemButton.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile(FILE_NAME)

            val fileProvider = FileProvider.getUriForFile(this,
                "com.example.samplerecyclearview.fileprovider",photoFile)
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

            if (cameraIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(cameraIntent, REQUEST_CODE)
            } else {
                Toast.makeText(this, "Unable To open Camera", Toast.LENGTH_SHORT).show()
            }
            //takePic()
            //addInfo()

        }
        */
    }

}
