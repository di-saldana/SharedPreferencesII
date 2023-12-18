package es.ua.eps.sharedpreferencesii

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import androidx.preference.SeekBarPreference

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class SettingsFragment : PreferenceFragmentCompat() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onNavigateToScreen(preferenceScreen: PreferenceScreen) {
        super.onNavigateToScreen(preferenceScreen)
        setPreferenceScreen(preferenceScreen)
        updateSummaries()
    }

    private fun updateSummaries() {
        val sizePreference = findPreference<ListPreference>("size")
        val textColorPreference = findPreference<ListPreference>("text_color")
        val backgroundColorPreference = findPreference<ListPreference>("background_color")
        val alphaPreference = findPreference<SeekBarPreference>("alpha")
        val rotationPreference = findPreference<SeekBarPreference>("rotation")

        sizePreference?.summary = sizePreference?.value
        textColorPreference?.summary = textColorPreference?.entry
        backgroundColorPreference?.summary = backgroundColorPreference?.entry
        alphaPreference?.summary = (alphaPreference?.value?.div(100.00)).toString()
        rotationPreference?.summary = rotationPreference?.value.toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}