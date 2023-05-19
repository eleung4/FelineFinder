package com.example.felinefinder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.OverlayItem
import java.util.ArrayList


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
    private lateinit var catList: MutableList<Data>
    private lateinit var addCat: FloatingActionButton
    private lateinit var addLostCat: FloatingActionButton
    private lateinit var mapView: MapView
    private lateinit var thisCat: Data

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


            mapView = rootLayout.findViewById<MapView>(R.id.mapView_mapFragment)
            mapView.setTileSource(TileSourceFactory.MAPNIK)

            addCat = mapView.findViewById(R.id.fab_addCat)
            addLostCat = mapView.findViewById(R.id.fab_addLost)

            // should center the map??
            val mapController = mapView.controller
            mapController.setZoom(9.99)

            val startPoint = GeoPoint(34.1095664689106, -118.15445321324104);
            mapController.setCenter(startPoint);



            addCat.setOnClickListener {
                val detailIntent = Intent(requireContext(), CatAddInput::class.java).apply {
                    putExtra(lat)
                }
                startActivity(detailIntent)


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

        private fun addCat(
            name: String,
            description: String,
            friendly: String,
            lat: Double,
            long: Double
        ) {
            //your items
            thisCat = Data(name, description, friendly, lat, long)
            catList.add(thisCat)

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
}