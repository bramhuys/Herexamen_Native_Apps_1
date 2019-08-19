package be.hogent.student.native_apps_1_android_creative_app_bramhuys.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Interface voor het ophalen van data uit de RoomDatabase
 *
 */
@Dao
interface StationDao {

    @Query("SELECT * FROM stationdetail ORDER BY id")
    fun getStations(): LiveData<List<StationModel>>

    @Query("SELECT * FROM stationdetail")
    fun getStationList(): List<StationModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stations: List<StationModel>?)



}
