package be.hogent.student.native_apps_1_android_creative_app_bramhuys.ViewModel

import androidx.lifecycle.ViewModel
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.data.StationModel
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.data.StationRepository
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * ViewModel voor [MainFragment]
 *
 */
class MainViewModel  internal constructor(
    private val stationRepository: StationRepository
): ViewModel() {

    val items = ArrayList<StationModel>()


    init {
        val myExecutor : Executor = Executors.newSingleThreadExecutor()
        myExecutor.execute {
            items.addAll(stationRepository.getStationList())
        }
    }

    fun  getStations(): ArrayList<StationModel>  {
        return items
    }



}