package com.example.felinefinder

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.map
import androidx.recyclerview.widget.RecyclerView
import com.example.felinefinder.databinding.ActivityMainBinding
//import com.niels_ole.customtileserver.R

import org.osmdroid.config.Configuration.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
import org.osmdroid.views.overlay.OverlayItem

import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1
    private lateinit var map : MapView
    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done
        // This won't work unless you have imported this: org.osmdroid.config.Configuration.*
        getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils
        //note, the load method also sets the HTTP User Agent to your application's package name, if you abuse osm's
        //tile servers will get you banned based on this string.

        //inflate and create the map
        setContentView(R.layout.activity_main)

        map = findViewById<MapView>(R.id.map)
        map.setTileSource(TileSourceFactory.MAPNIK)
        val mapController = map.controller
        mapController.setZoom(9.5)
        val startPoint = GeoPoint(34.1095664689106, -118.15445321324104);
        mapController.setCenter(startPoint);

        //your items
        val items = ArrayList<OverlayItem>()
        items.add(OverlayItem("Title", "Description", GeoPoint(0.0, 0.0)))

//the overlay
        var overlay = ItemizedOverlayWithFocus<OverlayItem>(items, object: ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            override fun onItemSingleTapUp(index:Int, item:OverlayItem):Boolean {
                //do something
                return true
            }
            override fun onItemLongPress(index:Int, item:OverlayItem):Boolean {
                return false
            }
        }, context)
        overlay.setFocusItemsOnTap(true);

        map.overlays.add(overlay); //mapView?


        binding.fabAddCat.setOnClickListener() {
            Toast.makeText( this, "add cat button", Toast.LENGTH_SHORT).show()

        } // ee

        binding.fabAddLost.setOnClickListener() {
            Toast.makeText( this, "lost cat button", Toast.LENGTH_SHORT).show()

        }

    }

    override fun onResume() {
        super.onResume()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        Transformations.map.onResume() //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onPause() {
        super.onPause()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        Transformations.map.onPause()  //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val permissionsToRequest = ArrayList<String>()
        var i = 0
        while (i < grantResults.size) {
            permissionsToRequest.add(permissions[i])
            i++
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                REQUEST_PERMISSIONS_REQUEST_CODE)
        }
    }


    //if click on little map marker thingy, will show info bigger
    //sends to detail activity (ideally)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_main, viewGroup, false)

        return RecyclerView.ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val hero = dataSet[position]
        viewHolder.textViewName.text = hero.name
        viewHolder.textViewDesc.text = hero.description
        viewHolder.textViewFriendly.text = hero.friendly
        viewHolder.layout.setOnClickListener {
            Toast.makeText(it.context, hero.toString(), Toast.LENGTH_SHORT).show()
            //make intent to open new activity
            val detailIntent = Intent(it.context, CatsDetailActivity::class.java)
//            detailIntent.putExtra(HeroesDetailActivity.EXTRA_NAME, hero.name)
//            detailIntent.putExtra(HeroesDetailActivity.EXTRA_DESCRIPTION, hero.description)
//            detailIntent.putExtra(HeroesDetailActivity.EXTRA_SUPERPOWER, hero.superpower)
//            detailIntent.putExtra(HeroesDetailActivity.EXTRA_RANKING, hero.ranking)
//            detailIntent.putExtra(HeroesDetailActivity.EXTRA_IMAGE, hero.image)
            detailIntent.putExtra(CatsDetailActivity.EXTRA_CAT, cat)
            it.context.startActivity(detailIntent)
        }
    }

}





    /*private fun requestPermissionsIfNecessary(String[] permissions) {
        val permissionsToRequest = ArrayList<String>();
        permissions.forEach { permission ->
        if (ContextCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            permissionsToRequest.add(permission);
        }
    }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }*/
}
//dwnload google services emulator
//if api doesnt work, use photo, then track by calculating lat and long