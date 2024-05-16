package com.example.kotlin_viewintro

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(),AdapterView.OnItemClickListener {

    private var TAG = "MainActivity"
    private lateinit var lv : ListView
    lateinit var adapter: ListAdapter
    private var data = ArrayList<ViewIntro>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //mapView.getMapAsync(this);
        //mGoogleMap = mMapView.getMap();
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            // 沒有權限在此重新申請權限
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ), 100
            )
        } else {
            // 有權限了
            Log.d(TAG, "onCreate: 有權限了")
        }
        initView()
    }

    private fun initView() {
        Log.d(TAG, "initView: ")
        lv = findViewById(R.id.lv)
        adapter = ListAdapter(this,DataService.groups)
        lv.adapter = adapter
        lv.setOnItemClickListener(this)

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(TAG, "onItemClick: "+position)
        //Toast.makeText(this,"" + position, Toast.LENGTH_SHORT).show()
        var intent = Intent(this,ViewPageActivity::class.java)
        intent.putExtra("inputPosition", position)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onRequestPermissionsResult: YES")
        } else {
            Log.d(TAG, "onRequestPermissionsResult: NO")
        }
    }
}

