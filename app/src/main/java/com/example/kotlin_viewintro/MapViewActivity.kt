package com.example.kotlin_viewintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapViewActivity : AppCompatActivity(),OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var img_mapView_back: ImageView
    private lateinit var mMap: GoogleMap
    private var TAG = "MapViewActivity"
    var position = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_view)

        val mapViewBundle = savedInstanceState?.getBundle(MAPVIEW_BUNDLE_KEY)
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)

        position = intent.getIntExtra("inputPosition",0)
        Log.d(TAG, "initView: position = "+position)

        img_mapView_back = findViewById(R.id.img_mapView_back)
        img_mapView_back.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d(TAG, "onClick: img_viewPage_back")
                finish()
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY) ?: Bundle().also {
            outState.putBundle(MAPVIEW_BUNDLE_KEY, it)
        }
        mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onMapReady(map: GoogleMap) {
        //TODO("Not yet implemented")
        mMap = map

        // Add a marker in Sydney and move the camera
        var itemPosition = DataService.groups.get(position)
        val sydney = LatLng(itemPosition.Lat, itemPosition.Lng)
        mMap.addMarker(MarkerOptions().position(sydney))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15F))
    }

    companion object {
        private const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    }
}