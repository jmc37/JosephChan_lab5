package com.example.josephchan_lab5

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FighterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FighterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Fighter? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fighter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.character_name)
        val winnerView = view.findViewById<TextView>(R.id.winorlose)
        textView.text = param1?.name ?: "hello"
        val imageView = view.findViewById<ImageView>(R.id.imageView_character)
        param1?.resourceId?.let { imageView.setImageResource(it) }
        when (param2) {
            "true" -> {
                winnerView.text = getString(R.string.Winner)
                imageView.animation = AnimationUtils.loadAnimation(view.context, R.anim.shake)
                winnerView.setTextColor(Color.GREEN)
            }
            "false" -> {
                winnerView.text = getString(R.string.loser)
                winnerView.setTextColor(Color.RED)
            }
            else -> {
                winnerView.text = getString(R.string.tie)
                winnerView.setTextColor(Color.WHITE)
            }
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FighterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Fighter, param2: String) =
            FighterFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}