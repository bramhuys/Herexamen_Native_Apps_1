package be.hogent.student.native_apps_1_android_creative_app_bramhuys.View

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.R
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.data.AppDatabase
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.workers.StationDatabaseWorker
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * MainActivity
 *
 */
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupPermissions()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, MainFragment.newInstance(), "mainFragment")
                .commit()
        }



        val db = AppDatabase.getInstance(this)

        val myExecutor : Executor = Executors.newSingleThreadExecutor()
        myExecutor.execute {
            val request = OneTimeWorkRequestBuilder<StationDatabaseWorker>().build()
            WorkManager.getInstance().enqueue(request)
            val stations = db.stationDao().getStationList()
        }


    }
    /**
     * Permissions opvragen
     */
    private fun setupPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION , Manifest.permission.ACCESS_FINE_LOCATION),
                0)

            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, MainFragment.newInstance(), "mainFragment")
                .commit()

        }
            else{

        }

    }
}
