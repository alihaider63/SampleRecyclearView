package com.example.samplerecyclearview.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.samplerecyclearview.databinding.SettingsLayoutBinding

class SettingsActivity: AppCompatActivity() {

    companion object {
        const val SHAREDPREF = "sharedPreferences"
        const val TONEDETECTION = "toneDetection"
        const val AUTOCORRECTION = "autoCorrection"
        const val AUTOCAPITALIZATION = "autoCapitalization"
        const val SWIPE = "swipe"
        const val PREDICTIVE = "predictive"
        const val SUGGESTEMOJIS = "suggestEmojis"
        const val SYNONYMS = "synonyms"
        const val SHOWSYNONYMS = "showSynonyms"
        const val DOUBLESPACEPERIOD = "DoubleSpacePeriod"
        const val SUGGESTCONTACTSNAMES = "suggestContactsNames"
    }

    private lateinit var binding: SettingsLayoutBinding
    private lateinit var switchOfToneDetection: SwitchCompat
    private lateinit var switchOfAutoCorrection: SwitchCompat
    private lateinit var switchOfAutoCapitalize: SwitchCompat
    private lateinit var switchOfSwipe: SwitchCompat
    private lateinit var switchOfPredictive: SwitchCompat
    private lateinit var switchOfSuggestEmojis: SwitchCompat
    private lateinit var switchOfSynonyms: SwitchCompat
    private lateinit var switchOfShowSynonymsAfterDelay: SwitchCompat
    private lateinit var switchOfDoubleSpacePeriod: SwitchCompat
    private lateinit var switchOfSuggestContactNames: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        switchOfToneDetection = binding.switchOfToneDetection
        switchOfAutoCorrection = binding.switchOfAutoCorrection
        switchOfAutoCapitalize = binding.switchOfAutoCapitalize
        switchOfSwipe = binding.switchOfSwipe
        switchOfPredictive = binding.switchOfPredictive
        switchOfSuggestEmojis = binding.switchOfSuggestEmojis
        switchOfSynonyms = binding.switchOfSynonyms
        switchOfShowSynonymsAfterDelay = binding.switchOfShowSynonymsAfterDelay
        switchOfDoubleSpacePeriod = binding.switchOfDoubleSpacePeriod
        switchOfSuggestContactNames = binding.switchOfSuggestContactNames

        loadData()
        switchOfToneDetection.setOnClickListener {
            saveData()
        }
        switchOfAutoCorrection.setOnClickListener {
            saveData()
        }
        switchOfAutoCapitalize.setOnClickListener {
            saveData()
        }
        switchOfSwipe.setOnClickListener {
            saveData()
        }
        switchOfPredictive.setOnClickListener {
            saveData()
        }
        switchOfSuggestEmojis.setOnClickListener {
            saveData()
        }
        switchOfSynonyms.setOnClickListener {
            saveData()
        }
        switchOfShowSynonymsAfterDelay.setOnClickListener {
            saveData()
        }
        switchOfDoubleSpacePeriod.setOnClickListener {
            saveData()
        }
        switchOfSuggestContactNames.setOnClickListener {
            saveData()
        }
    }
    private fun saveData() {
        val sharedPref = getSharedPreferences(SHAREDPREF, MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            putBoolean(TONEDETECTION, switchOfToneDetection.isChecked)
            putBoolean(AUTOCORRECTION, switchOfAutoCorrection.isChecked)
            putBoolean(AUTOCAPITALIZATION, switchOfAutoCapitalize.isChecked)
            putBoolean(SWIPE, switchOfSwipe.isChecked)
            putBoolean(PREDICTIVE, switchOfPredictive.isChecked)
            putBoolean(SUGGESTEMOJIS, switchOfSuggestEmojis.isChecked)
            putBoolean(SYNONYMS, switchOfSynonyms.isChecked)
            putBoolean(SHOWSYNONYMS, switchOfShowSynonymsAfterDelay.isChecked)
            putBoolean(DOUBLESPACEPERIOD, switchOfDoubleSpacePeriod.isChecked)
            putBoolean(SUGGESTCONTACTSNAMES, switchOfSuggestContactNames.isChecked)
        }
            .apply()
    }
    private fun loadData() {
        val sharedPref = getSharedPreferences(SHAREDPREF, MODE_PRIVATE)
        switchOfToneDetection.isChecked = sharedPref.getBoolean(TONEDETECTION, false)
        switchOfAutoCorrection.isChecked = sharedPref.getBoolean(AUTOCORRECTION, false)
        switchOfAutoCapitalize.isChecked = sharedPref.getBoolean(AUTOCAPITALIZATION, false)
        switchOfSwipe.isChecked = sharedPref.getBoolean(SWIPE, false)
        switchOfPredictive.isChecked = sharedPref.getBoolean(PREDICTIVE,false)
        switchOfSuggestEmojis.isChecked = sharedPref.getBoolean(SUGGESTEMOJIS, false)
        switchOfSynonyms.isChecked = sharedPref.getBoolean(SYNONYMS, false)
        switchOfShowSynonymsAfterDelay.isChecked = sharedPref.getBoolean(SHOWSYNONYMS, false)
        switchOfDoubleSpacePeriod.isChecked = sharedPref.getBoolean(DOUBLESPACEPERIOD, false)
        switchOfSuggestContactNames.isChecked = sharedPref.getBoolean(SUGGESTCONTACTSNAMES, false)
    }
}