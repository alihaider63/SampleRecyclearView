package com.example.samplerecyclearview.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.samplerecyclearview.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val SHARED_PREF = "sharedPreferences"
        const val TONE_DETECTION = "toneDetection"
        const val AUTOCORRECTION = "autoCorrection"
        const val AUTO_CAPITALIZATION = "autoCapitalization"
        const val SWIPE = "swipe"
        const val PREDICTIVE = "predictive"
        const val SUGGEST_EMOJIS = "suggestEmojis"
        const val SYNONYMS = "synonyms"
        const val SHOW_SYNONYMS = "showSynonyms"
        const val DOUBLE_SPACE_PERIOD = "DoubleSpacePeriod"
        const val SUGGEST_CONTACTS_NAMES = "suggestContactsNames"
    }

    private lateinit var binding: ActivitySettingsBinding
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
        binding = ActivitySettingsBinding.inflate(layoutInflater)
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

        binding.back.setOnClickListener {
            onBackPressed()
        }

    }

    private fun saveData() {
        val sharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply {
            putBoolean(TONE_DETECTION, switchOfToneDetection.isChecked)
            putBoolean(AUTOCORRECTION, switchOfAutoCorrection.isChecked)
            putBoolean(AUTO_CAPITALIZATION, switchOfAutoCapitalize.isChecked)
            putBoolean(SWIPE, switchOfSwipe.isChecked)
            putBoolean(PREDICTIVE, switchOfPredictive.isChecked)
            putBoolean(SUGGEST_EMOJIS, switchOfSuggestEmojis.isChecked)
            putBoolean(SYNONYMS, switchOfSynonyms.isChecked)
            putBoolean(SHOW_SYNONYMS, switchOfShowSynonymsAfterDelay.isChecked)
            putBoolean(DOUBLE_SPACE_PERIOD, switchOfDoubleSpacePeriod.isChecked)
            putBoolean(SUGGEST_CONTACTS_NAMES, switchOfSuggestContactNames.isChecked)
        }
            .apply()
    }

    private fun loadData() {
        val sharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        switchOfToneDetection.isChecked = sharedPref.getBoolean(TONE_DETECTION, false)
        switchOfAutoCorrection.isChecked = sharedPref.getBoolean(AUTOCORRECTION, false)
        switchOfAutoCapitalize.isChecked = sharedPref.getBoolean(AUTO_CAPITALIZATION, false)
        switchOfSwipe.isChecked = sharedPref.getBoolean(SWIPE, false)
        switchOfPredictive.isChecked = sharedPref.getBoolean(PREDICTIVE, false)
        switchOfSuggestEmojis.isChecked = sharedPref.getBoolean(SUGGEST_EMOJIS, false)
        switchOfSynonyms.isChecked = sharedPref.getBoolean(SYNONYMS, false)
        switchOfShowSynonymsAfterDelay.isChecked = sharedPref.getBoolean(SHOW_SYNONYMS, false)
        switchOfDoubleSpacePeriod.isChecked = sharedPref.getBoolean(DOUBLE_SPACE_PERIOD, false)
        switchOfSuggestContactNames.isChecked = sharedPref.getBoolean(SUGGEST_CONTACTS_NAMES, false)
    }


}