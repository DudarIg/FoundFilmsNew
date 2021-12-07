package ru.dudar.findfilms.ui

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.core.view.isVisible
import ru.dudar.findfilms.R
import ru.dudar.findfilms.databinding.FragmentSettingsBinding
import ru.dudar.findfilms.domain.Disable
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ru.dudar.findfilms.util.AsyncGeocoder
import ru.dudar.findfilms.util.toPrintString
import java.util.jar.Manifest
private const val GPS_UPDATE_DURATION_MS = 1_000L
private const val GPS_UPDATE_DISTANCE_M = 10f

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private var location: Location? = null
        set(value) {
            field = value
            value?.let {
                val latlon = LatLng(it.latitude, it.longitude)
                mapView?.setMinZoomPreference(18f)
                mapView?.addMarker(MarkerOptions().position(latlon))
                mapView?.moveCamera(CameraUpdateFactory.newLatLng(latlon))
            }
        }
    private var locate : LatLng? = null
    private var mapView: GoogleMap? = null

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission())
    {
        if (it) {
            explain()
        } else
        {
            binding.zeroTextView.isVisible = true
            binding.explainTextView.isVisible = true
            binding.locateButton.isVisible = true
            binding.locateButton.setOnClickListener{
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }
    }

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            this@SettingsFragment.location = location

        }
        override fun onProviderEnabled(provider: String) {
            Toast.makeText(activity, "Enabled $provider", Toast.LENGTH_SHORT).show()
        }
        override fun onProviderDisabled(provider: String) {
            Toast.makeText(activity, "Disabled $provider", Toast.LENGTH_SHORT).show()
        }
    }


    private val locationManager by  lazy { context?.getSystemService(LOCATION_SERVICE) as LocationManager }

    private val callback = OnMapReadyCallback { googleMap ->
        mapView = googleMap
        location?.let {
             locate = LatLng(it.latitude, it.longitude)
        }

        googleMap.setMinZoomPreference(10f)
        googleMap.addMarker(MarkerOptions().position(locate!!).title("Ваше расположение"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(locate!!))

        googleMap.setOnMapClickListener {
            locate = LatLng(it.latitude, it.longitude)
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(locate!!))
            googleMap.setMinZoomPreference(10f)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)
        val disable = context as Disable
        disable.onDisableButton(false, R.id.as_settings)

        binding.zeroTextView.isVisible = false
        binding.explainTextView.isVisible = false

        binding.locateButton.isVisible = false

        permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)


    }

    fun explain() {

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        if (ActivityCompat.checkSelfPermission( context!!,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED)
                    return

        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        binding.realButton.setOnClickListener {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                GPS_UPDATE_DURATION_MS,
                GPS_UPDATE_DISTANCE_M,
                locationListener
            )
        }

    }

    override fun onStop() {
        super.onStop()
        val disable = context as Disable
        disable.onDisableButton(true, R.id.as_settings)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}