package be.hogent.student.native_apps_1_android_creative_app_bramhuys.util

import android.content.Context
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.ViewModel.MainViewModelFactory
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.ViewModel.StationListViewModelFactory
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.data.AppDatabase
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.data.StationRepository
/**
 * Injecteren van de ViewModelFactory's
 *
 */
object InjectorUtils {

    private fun getStationRepository(context: Context): StationRepository {
        return StationRepository.getInstance(AppDatabase.getInstance(context).stationDao())
    }

    fun provideStationListViewModelFactory(context: Context): StationListViewModelFactory {
        val repository = getStationRepository(context)
        return StationListViewModelFactory(repository)
    }

    fun provideMainViewModelFactory(context: Context): MainViewModelFactory {
        val repository = getStationRepository(context)
        return MainViewModelFactory(repository)
    }

}