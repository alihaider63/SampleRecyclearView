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
import androidx.recyclerview.widget.RecyclerView
import com.example.samplerecyclearview.*
import com.example.samplerecyclearview.activities.SecondActivity
import com.example.samplerecyclearview.activities.SettingsActivity
import com.example.samplerecyclearview.adapters.ButterflyAdapter
import com.example.samplerecyclearview.databinding.MainActivityFragmentBinding
import com.example.samplerecyclearview.interfaces.MyInterface
import com.example.samplerecyclearview.models.Butterfly
import com.example.samplerecyclearview.models.FileInfo
import org.json.JSONArray
import org.json.JSONException
import java.io.File

class MainActivityFragment() : Fragment(), MyInterface {

    companion object {
        const val FILE_NAME = "photo.jpg"
        const val PARCELABLE_OBJECT = "parcelableObject"
        fun newInstance() = MainActivityFragment()
    }

    private lateinit var binding: MainActivityFragmentBinding
    private lateinit var adapter: ButterflyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var photoFile: File
    private lateinit var sharedPre: SharedPreferences
    private val REQUEST_CODE = 42
    private val BUTTERFLY_NAME = "butterflyName"
    private val BUTTERFLY_FAMILY = "butterflyFamily"
    private val PATH = "pathOfPic"

    val jsonString: String = "[\n" +
            "        {\n" +
            "            \"fileName\": \"14590457_1775646402674116_667940766311345630_n.jpg\",\n" +
            "            \"fileSize\": 31304,\n" +
            "            \"readUrl\": \"https://st-media.circleinapp.com/l34t4ua29opo6cgi1r6g4bo0uk7rnqds124l.jpg\",\n" +
            "            \"iconUrl\": \"https://media.circleinapp.com/img/file_ext/other-default-icon@3x.png\",\n" +
            "            \"fileType\": \"image/jpeg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"fileName\": \"1929761_532574010244727_660257506347945306_n.jpg\",\n" +
            "            \"fileSize\": 78223,\n" +
            "            \"readUrl\": \"https://st-media.circleinapp.com/hbb24ee8r5c999j0v23gukgcho5te1bkc23b.jpg\",\n" +
            "            \"iconUrl\": \"https://media.circleinapp.com/img/file_ext/other-default-icon@3x.png\",\n" +
            "            \"fileType\": \"image/jpeg\"\n" +
            "        }\n" +
            "    ]"

    val json2 = "[\n" +
            "    {\n" +
            "        \"fileName\": \"height of innocense.png\",\n" +
            "        \"fileSize\": 511731,\n" +
            "        \"readUrl\": \"https://st-media.circleinapp.com/hh8khl82d99m402acvu8ns0qmt4rpqarnu2u.png\",\n" +
            "        \"iconUrl\": \"https://media.circleinapp.com/img/file_ext/other-default-icon@3x.png\",\n" +
            "        \"fileType\": \"image/png\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"fileName\": \"\u0080TED-Ed - Approximately 7 million people around the world\u0080.mp4\",\n" +
            "        \"fileSize\": 11558592,\n" +
            "        \"readUrl\": \"https://st-media.circleinapp.com/9ts5jj3k4fpv7nul2ler0njqeg05uittec71\",\n" +
            "        \"iconUrl\": \"https://media.circleinapp.com/img/file_ext/mp4-icon@3x.png\",\n" +
            "        \"fileType\": \"video/mp4\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"fileName\": \"4_6037461525424243349.pdf\",\n" +
            "        \"fileSize\": 1810772,\n" +
            "        \"readUrl\": \"https://st-media.circleinapp.com/fa4vh5rrrfim2cmq5m99as8c5i8o1glg90u7\",\n" +
            "        \"iconUrl\": \"https://media.circleinapp.com/img/file_ext/pdf-icon@3x.png\",\n" +
            "        \"fileType\": \"application/pdf\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"fileName\": \"codegen_612d8d752e389.zip\",\n" +
            "        \"fileSize\": 1947,\n" +
            "        \"readUrl\": \"https://st-media.circleinapp.com/gctg418ljqnl7as4c56jd42qsr4baekpd3bj\",\n" +
            "        \"iconUrl\": \"https://media.circleinapp.com/img/file_ext/zip-icon@3x.png\",\n" +
            "        \"fileType\": \"application/zip\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"fileName\": \"LAB PE ATI HAI DUA.mp3\",\n" +
            "        \"fileSize\": 7571850,\n" +
            "        \"readUrl\": \"https://st-media.circleinapp.com/spevjs2tg4nsc8om65vu5lursq4oj0aqiv8d\",\n" +
            "        \"iconUrl\": \"https://media.circleinapp.com/img/file_ext/mp3-icon@3x.png\",\n" +
            "        \"fileType\": \"audio/mpeg\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"fileName\": \"Wedding-Invitation_Usman-Afzal.docx\",\n" +
            "        \"fileSize\": 342951,\n" +
            "        \"readUrl\": \"https://st-media.circleinapp.com/qg62am1qm11g1v4nca4k1sfj17jga6n08fhs\",\n" +
            "        \"iconUrl\": \"https://media.circleinapp.com/img/file_ext/docx-icon@3x.png\",\n" +
            "        \"fileType\": \"application/vnd.openxmlformats-officedocument.wordprocessingml.document\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"fileName\": \"products.txt\",\n" +
            "        \"fileSize\": 1061,\n" +
            "        \"readUrl\": \"https://st-media.circleinapp.com/pqlb0cc6hv7hebo51chsimmekamtvoikg5j4\",\n" +
            "        \"iconUrl\": \"https://media.circleinapp.com/img/file_ext/txt-icon@3x.png\",\n" +
            "        \"fileType\": \"text/plain\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"fileName\": \"GIt merge.gif\",\n" +
            "        \"fileSize\": 3785598,\n" +
            "        \"readUrl\": \"https://st-media.circleinapp.com/14s9s2a9m56b76evpn2ubjskr7hglho2b91t\",\n" +
            "        \"iconUrl\": \"https://media.circleinapp.com/img/file_ext/other-default-icon@3x.png\",\n" +
            "        \"fileType\": \"image/gif\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"fileName\": \"Master pizza.rar\",\n" +
            "        \"fileSize\": 98648,\n" +
            "        \"readUrl\": \"https://st-media.circleinapp.com/oc8i47n72bgjne0h1d6opbmfa5cf5kn9e31q\",\n" +
            "        \"iconUrl\": \"https://media.circleinapp.com/img/file_ext/other-default-icon@3x.png\",\n" +
            "        \"fileType\": \"application/x-rar\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"fileName\": \"f26d6d5fb489269ade98bc1eb840b0d3.jpg\",\n" +
            "        \"fileSize\": 14234,\n" +
            "        \"readUrl\": \"https://st-media.circleinapp.com/g7ub6l6tbi0uvc7ep4d7if7k66jk3i4j3k7m.jpg\",\n" +
            "        \"iconUrl\": \"https://media.circleinapp.com/img/file_ext/other-default-icon@3x.png\",\n" +
            "        \"fileType\": \"image/jpeg\"\n" +
            "    }\n" +
            "    ]"

    @SuppressLint("ResourceType")
    val butterflyObjects = arrayListOf<Butterfly>(
        Butterfly("Argusvlinder","(Lasiommata megera)", R.drawable.butterfly1),
        Butterfly("Bont Zandoogje","(Pararge aegeria)", R.drawable.butterfly2),
        Butterfly("Bruin Zandoogje","(Maniola jurtina)", R.drawable.butterfly3),
        Butterfly("Distelvlinder","(Vanessa cardui)", R.drawable.butterfly4),
        Butterfly("Distelvlinder","(Vanessa cardui)", R.drawable.butterfly5),
        Butterfly("Distelvlinder","(Vanessa cardui)", R.drawable.butterfly6),
        Butterfly("Zwartsprietdikkopje","(Thymelicus lineola)", R.drawable.butterfly7),
        Butterfly("Bretons spikkeldikkopje","(Pyrgus armoricanus)", R.drawable.butterfly8),
        Butterfly("Aardbeivlinder","(Pyrgus malvae)", R.drawable.butterfly9),
        Butterfly("Koninginnenpage","(Papilio machaon)", R.drawable.butterfly10)
    )

    val obj = mutableListOf<FileInfo>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = MainActivityFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ButterflyAdapter(butterflyObjects, this)
        recyclerView = binding.listOfButterfly

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        val addNewItem = binding.addNewItem
        addNewItem.setOnClickListener {
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

        val setting = binding.settingsButton
        setting?.setOnClickListener {
            onSettingsClicked()
        }

        try {
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val name = jsonObject.optString("fileName")
                val size = jsonObject.optInt("fileSize")
                val imageUrl = jsonObject.optString("readUrl")
                val iconImageUrl = jsonObject.optString("iconUrl")
                val filetype = jsonObject.optString("fileType")
                val data = FileInfo(name, size, imageUrl, iconImageUrl, filetype)
                obj.add(data)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    private fun onSettingsClicked() {
        val settingsIntent = Intent(context, SettingsActivity::class.java)

        startActivity(settingsIntent)
    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName,".jpg",storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //val takenImage = data?.extras?.get("data") as Bitmap
            val imagePath = photoFile.absolutePath
            //val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            sharedPre = context?.getSharedPreferences("myData", MODE_PRIVATE)!!
            val editor = sharedPre?.edit()
            editor?.apply {
                putString(PATH, imagePath)
                putString(BUTTERFLY_NAME, "Ali")
                putString(BUTTERFLY_FAMILY, "Khokhar")
                apply()
            }
            imagePath.let {
                adapter.addItem(Butterfly("Ali","Haider",null,it))
                Toast.makeText(this.context, "Successfully Added Item.", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    override fun onButterClicked(position: Int, ArrayList: MutableList<Butterfly>) {
        val fragment = PictureShowOnFullScreenFragment.newInstance(
            ArrayList[position].butterfly,
            ArrayList[position].image,
            ArrayList[position].imagePath
        )
        val transaction = parentFragmentManager.beginTransaction()
        transaction.add(R.id.main_fragment_view, fragment)
            .addToBackStack(null)
            .commit()
    }

    @SuppressLint("ResourceType")
    override fun onForwardPressed(position: Int, ArrayList: MutableList<Butterfly>) {
        val intent = Intent(context, SecondActivity::class.java)
        val butterfly = ArrayList[position]

        intent.putExtra(PARCELABLE_OBJECT, butterfly)
        startActivity(intent)
    }
}
