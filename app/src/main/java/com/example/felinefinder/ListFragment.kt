package com.example.felinefinder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
 lateinit var catList: MutableList<Data>


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var mainActivity: MainActivity
//    lateinit var view : RecyclerView
    lateinit var text : TextView



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
        mainActivity = requireActivity() as MainActivity
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)

        text = rootView.findViewById(R.id.textView_cats)




        return rootView




    }

    override fun onStart() {
        super.onStart()
        Log.d("ListFrag", "onCreateView: ${mainActivity.catList}")
        text.setText(mainActivity.catList.joinToString { "\n" })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

//        fun addCatToList(
//            name: String,
//            friendly: String,
//            lat: Double,
//            long: Double,
//            description: String,
//            lost: Boolean
//        ) {
//            var cat = Data(name, friendly, description, lat, long, lost)
//            catList.add(cat)
//
//
//        }


    }
}
