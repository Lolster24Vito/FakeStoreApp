package hr.algebra.fakestoreapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import hr.algebra.fakestoreapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class StoreMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View=inflater.inflate(R.layout.fragment_store_map, container, false)

        // Inflate the layout for this fragment

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val zagrebLocation = LatLng(45.7881, 15.9662)
        val zagrebLocation2 = LatLng(45.7826, 15.9502)
        val zagrebLocation3 = LatLng(45.77908, 15.93475)

        mMap.addMarker(
            MarkerOptions()
                .position(zagrebLocation)
                .title("first shop in zagreb"))
        mMap.addMarker(
            MarkerOptions()
                .position(zagrebLocation2)
                .title("second shop in zagreb"))
        mMap.addMarker(
            MarkerOptions()
                .position(zagrebLocation3)
                .title("third shop in zagreb"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zagrebLocation,14.0f))

    }

}