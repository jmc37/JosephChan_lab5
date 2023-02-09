package com.example.josephchan_lab5

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import kotlinx.parcelize.Parcelize


val characterName = listOf<String>("Ahsoka", "bb8", "c3p0", "Chewbacca", "Grogu", "Jabba", "Kilo", "Trooper", "Yoda")
val characterImage = listOf<Int>(R.drawable.ahsoka, R.drawable.bb8, R.drawable.c3po,R.drawable.chewbacca,R.drawable.grogu,R.drawable.jabba,R.drawable.kilo, R.drawable.trooper,R.drawable.yoda)


@Parcelize
class Fighter(val name: String,val resourceId: Int,val power: Int) : Parcelable

@Parcelize
class Battle(val fighterOne: Fighter, val fighterTwo: Fighter) : Parcelable{

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

    fun fight(){
        val arraylist = ArrayList<Fighter>()
        val rnds1 = (0..9).random() // generated random from 0 to 10 included
        val rnds2 = (0..9).random()

        val character1 = Fighter(characterName[rnds1], characterImage[rnds1], rnds1)
        val character2 = Fighter(characterName[rnds2], characterImage[rnds2], rnds2)
        fun fight(fighterOne: Fighter, fighterTwo: Fighter){
            val intent = Intent(this, FighterFragment::class.java)
            intent.putParcelableArrayListExtra(CHARACTERS, arraylist)
            ContextCompat.startActivity(intent)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.home_fragmentArena_button)
        button.setOnClickListener{
            val fragmentTransaction = this.parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer_main, HomeFragment())
            fragmentTransaction.commit()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ArenaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ArenaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}