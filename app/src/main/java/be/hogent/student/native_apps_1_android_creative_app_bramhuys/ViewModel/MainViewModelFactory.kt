package be.hogent.student.native_apps_1_android_creative_app_bramhuys.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.data.StationRepository


/**
 * ViewModelFactory Main
 *
 * Factory voor het aanmaken van [MainViewModel] met een constructor dat [StationRepository] aanvaard
*/
 

class MainViewModelFactory (private val repository: StationRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = MainViewModel(repository) as T
}
