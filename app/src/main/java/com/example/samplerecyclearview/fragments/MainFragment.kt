package com.example.samplerecyclearview.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplerecyclearview.R
import com.example.samplerecyclearview.activities.ButterflyDetailActivity
import com.example.samplerecyclearview.activities.SettingsActivity
import com.example.samplerecyclearview.adapters.ButterflyAdapter
import com.example.samplerecyclearview.databinding.FragmentMainBinding
import com.example.samplerecyclearview.interfaces.ButterflyInterface
import com.example.samplerecyclearview.models.Butterfly
import java.io.File

class MainFragment : Fragment(), ButterflyInterface {

    companion object {

        const val FILE_NAME = "photo.jpg"
        const val PARCELABLE_OBJECT = "parcelableObject"
        private const val REQUEST_CODE = 42
        private const val BUTTERFLY_NAME = "butterflyName"
        private const val BUTTERFLY_FAMILY = "butterflyFamily"
        private const val PATH = "pathOfPic"
        fun newInstance() = MainFragment()

    }

    private lateinit var binding: FragmentMainBinding
    private var adapter: ButterflyAdapter? = null
    private lateinit var photoFile: File
    private lateinit var sharedPre: SharedPreferences

    @SuppressLint("ResourceType")
    val butterflyObjectList = arrayListOf(
        Butterfly("Argusvlinder", "(Lasiommata megera)", R.drawable.butterfly1),
        Butterfly("Bont Zandoogje", "(Pararge aegeria)", R.drawable.butterfly2),
        Butterfly("Bruin Zandoogje", "(Maniola jurtina)", R.drawable.butterfly3),
        Butterfly("Distelvlinder", "(Vanessa cardui)", R.drawable.butterfly4),
        Butterfly("Distelvlinder", "(Vanessa cardui)", R.drawable.butterfly5),
        Butterfly("Distelvlinder", "(Vanessa cardui)", R.drawable.butterfly6),
        Butterfly("Zwartsprietdikkopje", "(Thymelicus lineola)", R.drawable.butterfly7),
        Butterfly("Bretons spikkeldikkopje", "(Pyrgus armoricanus)", R.drawable.butterfly8),
        Butterfly("Aardbeivlinder", "(Pyrgus malvae)", R.drawable.butterfly9),
        Butterfly("Koninginnenpage", "(Papilio machaon)", R.drawable.butterfly10)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {

        adapter = ButterflyAdapter(butterflyObjectList, this)

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }

        binding.addNewItem.setOnClickListener {
            startCamera()
        }

        binding.hamburgerIcon.setOnClickListener {
            onSettingIconClicked()
        }
    }

    private fun startCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        photoFile = getPhotoFile(FILE_NAME)

        val fileProvider = this.context?.let { it1 ->
            photoFile.let { it2 ->
                FileProvider.getUriForFile(
                    it1,
                    "com.example.samplerecyclearview.fileprovider", it2
                )
            }
        }
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
        if (this.context?.let { it1 -> cameraIntent.resolveActivity(it1.packageManager) } != null) {
            startActivityForResult(cameraIntent, REQUEST_CODE)
        } else {
            Toast.makeText(this.context, "Unable To open Camera", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSettingIconClicked() {
        val settingsIntent = Intent(context, SettingsActivity::class.java)
        startActivity(settingsIntent)
    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //val takenImage = data?.extras?.get("data") as Bitmap
            val imagePath = photoFile.absolutePath
            //val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            sharedPre = context?.getSharedPreferences("myData", MODE_PRIVATE)!!
            val editor = sharedPre.edit()
            editor?.apply {
                putString(PATH, imagePath)
                putString(BUTTERFLY_NAME, "Ali")
                putString(BUTTERFLY_FAMILY, "Khokhar")
                apply()
            }
            imagePath.let {
                adapter?.addButterfly(Butterfly("Ali", "Haider", null, it))
                Toast.makeText(this.context, "Successfully Added Item.", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    override fun onImageClicked(butterfly: Butterfly) {
        val fragment = FullScreenImageFragment.newInstance(
            butterfly.imageRes,
            butterfly.imageBitmap,
            butterfly.imagePath
        )
        val transaction = parentFragmentManager.beginTransaction()
        transaction.add(R.id.main_fragment_view, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onForwardIconPressed(butterfly: Butterfly) {
        val intent = Intent(context, ButterflyDetailActivity::class.java)
        intent.putExtra(PARCELABLE_OBJECT, butterfly)
        startActivity(intent)
    }
}
