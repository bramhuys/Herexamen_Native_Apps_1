package be.hogent.student.native_apps_1_android_creative_app_bramhuys.View


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.R
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.ViewModel.MainViewModel
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.data.StationModel
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.databinding.FragmentMainBinding
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.util.InjectorUtils
import com.google.android.gms.location.LocationServices

/**
 * MainFragment
 *
 * Fragment voor het tonen van een input sherm
 */
class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    val fragment  = StationListFragment.newInstance()




    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMainBinding.inflate(inflater,container,false)
        val context = context ?: return binding.root
        val factory = InjectorUtils.provideMainViewModelFactory(context)

        viewModel = ViewModelProviders.of(this,factory).get(MainViewModel::class.java)

        /**
         * Permissions aanvragen indien nodig
         */
        if (checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION , Manifest.permission.ACCESS_FINE_LOCATION),
                0)

            fragmentManager!!
                .beginTransaction()
                .replace(R.id.root_layout, MainFragment.newInstance() , "MainFragment")
                .addToBackStack(null)
                .commit()


        }



        binding.btnZoeken.setOnClickListener { v ->

            if (checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED || checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (isConnected(context)) {
                    val ar =
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)

                    val input = binding.input.text.toString()

                    /**
                     * Huidige locatie opvragen
                     */

                    val bundle = Bundle()
                    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { location : Location ->


                            bundle.putParcelable("location", location)
                            fragment.arguments = bundle
                            bundle.putString("input", input)
                            viewModel.getStations()
                            bundle.putString("stationclosest", calculateClosest(location, viewModel).name)
                            fragmentManager!!
                                .beginTransaction()
                                .replace(R.id.root_layout, fragment, "stationlistFragment")
                                .addToBackStack(null)
                                .commit()
                        }
                        .addOnFailureListener { exception -> System.out.println(exception.message) }

                } else {
                    Toast.makeText(context, "Geen internet connectie gevonden", Toast.LENGTH_LONG).show()
                }
            }
        }



        setHasOptionsMenu(true)
        return binding.root
    }
    /**
     * Dichtbijzijnde Station opvragen
     */
    private fun calculateClosest (location : Location ,viewModel: MainViewModel): StationModel{

        lateinit var stationClosest : StationModel
        var smallestDistance = -1
        val stations = viewModel.getStations()
        for (station in stations){
            var locationClose = Location("location")
            locationClose.latitude = station.locationY
            locationClose.longitude = station.locationX

            val dist = location.distanceTo(locationClose)


            if (smallestDistance == -1 || dist < smallestDistance) {
                stationClosest = station
                smallestDistance = dist.toInt()
            }

        }
        return stationClosest
    }

    fun isConnected(context: Context): Boolean {

        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivity.activeNetworkInfo

        if(networkInfo !=null){
            return true
        }
        return false

    }

}
