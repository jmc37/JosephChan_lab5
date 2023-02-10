package com.example.josephchan_lab5

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.parcelize.Parcelize


val characterName = listOf("Ahsoka", "bb8", "c3p0", "Chewbacca", "Grogu", "Jabba", "Kilo", "Trooper", "Yoda")
val characterImage = listOf(R.drawable.ahsoka, R.drawable.bb8, R.drawable.c3po,R.drawable.chewbacca,R.drawable.grogu,R.drawable.jabba,R.drawable.kilo, R.drawable.trooper,R.drawable.yoda)


@Parcelize
class Fighter(val name: String,val resourceId: Int,val power: Int) : Parcelable

@Parcelize
class Battle(val fighterOne: Fighter, val fighterTwo: Fighter) : Parcelable{
     fun winner(fighterOne: Fighter, fighterTwo: Fighter): String {
         return if (fighterOne.power > fighterTwo.power){
             "true"
         }else if (fighterOne.power < fighterTwo.power){
             "false"
         }else{
             "tie"
         }
    }
}
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "com.example.josephchan_lab5.SOMEDAY"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [ArenaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArenaFragment : Fragment() {
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

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_arena, container, false)
    }

    private fun fight(){
        val rnds1 = (0..8).random() // generated random from 0 to 8 included
        val rnds2 = (0..8).random()
        val character1 = Fighter(characterName[rnds1], characterImage[rnds1], rnds1)
        val character2 = Fighter(characterName[rnds2], characterImage[rnds2], rnds2)
        val decider = Battle(character1, character2)
        val firstFighterDecision: String
        val secondFighterDecision: String
        when (decider.winner(decider.fighterOne, decider.fighterTwo)) {
            "true" -> {
                firstFighterDecision = "true"
                secondFighterDecision = "false"
            }
            "false" -> {
                firstFighterDecision = "false"
                secondFighterDecision = "true"
            }
            else -> {
                firstFighterDecision = "tie"
                secondFighterDecision = "tie"
            }
        }
        val ft = parentFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentContainerViewFighterOne, FighterFragment.newInstance(character1, firstFighterDecision))
        ft.replace(R.id.fragmentContainerViewFighterTwo, FighterFragment.newInstance(character2, secondFighterDecision))
        ft.commit()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.home_fragmentArena_button)
        fight()
        button.setOnClickListener{
            val fragmentTransaction = this.parentFragmentManager.beginTransaction()
            val arenaFragment = parentFragmentManager.findFragmentByTag("ARENAFRAGMENT_TAG")
            val homeFragment = parentFragmentManager.findFragmentByTag("HOMEFRAGMENT_TAG")
                fragmentTransaction.hide(arenaFragment!!)
                fragmentTransaction.show(homeFragment!!)
                fragmentTransaction.commit()
        }
        val nextButton = view.findViewById<Button>(R.id.nextbattle_fragmentArena_button)
        nextButton.setOnClickListener { fight() }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ArenaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ArenaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}