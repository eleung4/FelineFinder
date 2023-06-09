package com.example.felinefinder

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.OverlayItem

//how to make list for the cats that displays on list fragment and also through the methods


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var addCat: FloatingActionButton
    private lateinit var mapView: MapView
    private lateinit var thisCat: Data
    lateinit var mainActivity : MainActivity


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        val EXTRA_NAME = "name"
        val EXTRA_DESCRIPTION = "description"
        val EXTRA_FRIENDLY = "friendly"
        val EXTRA_LAT = "lat"
        val EXTRA_LONG = "long"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
//        mapView = (view?.findViewById(R.id.mapView_mapFragment) ?: this) as MapView'


    }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            // Inflate the layout for this fragment
            val rootLayout = inflater.inflate(R.layout.fragment_map, container, false)
            //requireContext()

            mainActivity = requireActivity() as MainActivity


            mapView = rootLayout.findViewById<MapView>(R.id.mapView_mapFragment)
            mapView.setTileSource(TileSourceFactory.MAPNIK)

            addCat = rootLayout.findViewById(R.id.fab_addCat)

            // should center the map??
            val mapController = mapView.controller
            mapController.setZoom(9.99)//

            val startPoint = GeoPoint(34.1095664689106, -118.15445321324104);
            mapController.setCenter(startPoint);



            addCat.setOnClickListener {
//                val name: String
//                val description: String
//                val friendly: String
//                val lat : Double
//                val long : Double
                val inflater = layoutInflater
                val dialoglayout: View = inflater.inflate(R.layout.dialog_cat_add_input, null)
                val nameText = dialoglayout.findViewById<EditText>(R.id.editText_add_name)
                val friendlyText = dialoglayout.findViewById<EditText>(R.id.editText_add_friendly)
                val latText = dialoglayout.findViewById<EditText>(R.id.editText_add_lat)
                val longText = dialoglayout.findViewById<EditText>(R.id.editText_add_long)
                val descText = dialoglayout.findViewById<EditText>(R.id.editText_add_description)
                val lostBox = dialoglayout.findViewById<CheckBox>(R.id.checkbox_lost)

//                val inputEditTextField = EditText(requireActivity())
                val dialog = AlertDialog.Builder(requireContext())
                    .setTitle("Add a Cat")
                    .setMessage("Put in the cat's attributes. If it is a lost cat, click the lost box.")
                    .setView(dialoglayout)
                    .setPositiveButton("OK") { _, _ ->
                        val editTextName = nameText.text.toString()
                        val editTextFriendly = friendlyText.text.toString()
                        val editTextLat = latText.text.toString().toDouble()
                        val editTextLong = longText.text.toString().toDouble()
                        val editTextDesc = descText.text.toString()
                        val editLost = lostBox.isChecked
                        Log.d("mapview","name value is: $editTextName")
                        Log.d("mapview","friendly value is: $editTextFriendly")
                        Log.d("mapview","lat value is: $editTextLat")
                        Log.d("mapview","long value is: $editTextLong")
                        Log.d("mapview","description value is: $editTextDesc")
                        Log.d("mapview","lost value is: $editLost")

//                        catList.add(editTextName, editTextFriendly, editTextDesc, editTextLat, editTextLong, editLost)
//                        Data cat1 = newData(name; friendly; description; lat; long; lost)

                        var cat1 = Data(editTextName, editTextFriendly, editTextDesc, editTextLat, editTextLong, editLost)
//                        ListFragment.addCatToList(cat1)

                        //mainActivity.catList.add(cat1)
                        mainActivity.addCat(cat1)
                        addIcon2(editTextLat, editTextLong, editTextName, editTextFriendly, editTextDesc, editLost)





                    }
                    .setNegativeButton("Cancel", null)

//                val builder = AlertDialog.Builder(requireContext())
//                builder.setView(dialoglayout)
//                builder.show()
//                    .create()
                dialog.show()

            }

            return rootLayout
        }


        fun addIcon(lat: Double, long: Double) {
            var marker = Marker(mapView)
            marker.position = GeoPoint(lat, long)
            marker.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_cat_icon_24)
            marker.title = "Test Marker"
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
            mapView.overlays.add(marker)
            mapView.invalidate()

//        marker.setOnMarkerClickListener() {
//
//            val detailIntent = Intent(it.context, CatsDetailActivity::class.java)
//            detailIntent.putExtra(CatsDetailActivity.EXTRA_CAT, cat)
//
//            it.context.startActivity(detailIntent)
//        }
            //im so confused what am i doing
        }

    fun addIcon2(lat: Double, long: Double, name: String, friendly: String, description: String, lost: Boolean) {
        var marker = Marker(mapView)
        marker.position = GeoPoint(lat, long)
        marker.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_cat_icon_24)
        marker.title = "$name \nfriendly: $friendly \n$description \n lost: $lost"
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        mapView.overlays.add(marker)
        mapView.invalidate()

    }

        fun addCat(
            name: String,
            description: String,
            friendly: String,
            lat: Double,
            long: Double
        ) {
            //your items


//            thisCat = Data(name, description, friendly, lat, long)
//            catList.add(thisCat)

            val items = ArrayList<OverlayItem>()
            items.add(OverlayItem(name, description, GeoPoint(lat, long)))


//the overlay
            var overlay = ItemizedOverlayWithFocus<OverlayItem>(
                items,
                object : ItemizedIconOverlay.OnItemGestureListener<OverlayItem> {
                    override fun onItemSingleTapUp(index: Int, item: OverlayItem): Boolean {
                        //do something
                        return true
                    }

                    override fun onItemLongPress(index: Int, item: OverlayItem): Boolean {
                        return false
                    }
                },
                requireContext()
            )
            overlay.setFocusItemsOnTap(true);

            mapView.overlays.add(overlay); //mapView?
            addIcon(lat, long)

        }
    }
