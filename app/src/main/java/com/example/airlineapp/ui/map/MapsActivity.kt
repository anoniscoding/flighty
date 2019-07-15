package com.example.airlineapp.ui.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.airlineapp.R
import com.example.airlineapp.data.ScheduleLocation
import com.example.airlineapp.ui.schedule.ScheduleFragment.Companion.SCHEDULE_LOCATION_TAG
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var _map: GoogleMap
    private lateinit var _scheduleLocation: ScheduleLocation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        _scheduleLocation = intent.getParcelableExtra(SCHEDULE_LOCATION_TAG)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        _map = googleMap

        val origin = LatLng(_scheduleLocation.origin.lat, _scheduleLocation.origin.lng)
        val destination = LatLng(_scheduleLocation.destination.lat, _scheduleLocation.destination.lng)

        _map.addPolyline(
            PolylineOptions()
                .clickable(true)
                .add(origin, destination)
        )

        // Position the map's camera near origin location
        _map.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 4f))
        _map.addMarker(MarkerOptions().position(origin).title(_scheduleLocation.origin.label))
        _map.addMarker(MarkerOptions().position(destination).title(_scheduleLocation.destination.label))
    }
}
