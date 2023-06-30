package com.example.demostrava.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demostrava.R
import com.mapbox.maps.MapView
import com.mapbox.maps.Style

class DetailActivityActivity : AppCompatActivity() {
    var mapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_activity)
        mapView = findViewById(R.id.mapView)
        mapView?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS)
    }
}