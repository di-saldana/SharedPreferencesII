package es.ua.eps.sharedpreferencesii

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.preference.PreferenceManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.textView)
        val textInput = view.findViewById<TextView>(R.id.textInput)
        textView.visibility = View.GONE

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        view.findViewById<Button>(R.id.previewButton).setOnClickListener {
            val textSize = sharedPrefs?.getString("size", "16")
            val textColor = sharedPrefs?.getString("text_color", "#000000")
            val backgroundColor = sharedPrefs?.getString("background_color", "#FFFFFF")
            val isBold = sharedPrefs?.getBoolean("bold", false) ?: false
            val isItalic = sharedPrefs?.getBoolean("italic", false) ?: false
            val alpha = sharedPrefs?.getInt("alpha", 0)
            val rotation = sharedPrefs?.getInt("rotation", 0)

            textView.text = textInput.text
            textView.textSize = textSize?.toFloat() ?: 16f
            textView.setTextColor(Color.parseColor(textColor))
            textView.setBackgroundColor(Color.parseColor(backgroundColor))
            textView.setTypeface(null, if (isBold) Typeface.BOLD else Typeface.NORMAL)
            textView.setTypeface(textView.typeface, if (isItalic) Typeface.ITALIC else Typeface.NORMAL)
            val alphaFloat = alpha?.toFloat() ?: 1f
            textView.alpha = alphaFloat / 255f // Convert alpha to a float between 0 and 1
            textView.rotation = rotation?.toFloat() ?: 0f

            textView.visibility = View.VISIBLE
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}