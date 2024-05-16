package com.example.kotlin_viewintro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ViewPageActivity : AppCompatActivity() {

    private var TAG = "ViewPageActivity"
    private lateinit var img_viewPage_back: ImageView
    private lateinit var img_viewPage_picture: ImageView
    private lateinit var txt_viewPage_info: TextView
    private lateinit var btn_viewPage_map: Button
    var position = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_page)
        initView()
    }

    private fun initView() {
        position = intent.getIntExtra("inputPosition",0)
        Log.d(TAG, "initView: position = "+position)

        img_viewPage_back = findViewById(R.id.img_viewPage_back)
        img_viewPage_picture = findViewById(R.id.img_viewPage_picture)
        txt_viewPage_info = findViewById(R.id.txt_viewPage_info)
        btn_viewPage_map = findViewById(R.id.btn_viewPage_map)

        var itemPosition = DataService.groups.get(position)
        Log.d(TAG, "initView: viewItem.picture = "+ itemPosition.picture)
        Log.d(TAG, "initView: viewItem.info = "+ itemPosition.info)

        img_viewPage_picture.setImageResource(itemPosition.picture)
        txt_viewPage_info.text = getString(itemPosition.info)


        img_viewPage_back.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d(TAG, "onClick: img_viewPage_back")
                finish()
            }
        })

        btn_viewPage_map.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d(TAG, "onClick: btn_viewPage_map")
                //Toast.makeText(applicationContext,"跳至MAP位置,待完成",Toast.LENGTH_SHORT).show()
                //var intent = Intent(this,MapViewActivity::class.java)
                var intent = Intent(applicationContext,MapViewActivity::class.java)
                //var intent = Intent(applicationContext,MapsActivity::class.java)
                intent.putExtra("inputPosition", position)
                startActivity(intent)
            }

        })

    }
}