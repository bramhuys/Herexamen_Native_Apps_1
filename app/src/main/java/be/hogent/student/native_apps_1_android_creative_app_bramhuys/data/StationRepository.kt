package be.hogent.student.native_apps_1_android_creative_app_bramhuys.data
/**
 * Repository voor DAO's
 *
 */
class StationRepository private constructor(private val stationDao:StationDao) {

    fun getStations() = stationDao.getStations()
    fun getStationList() = stationDao.getStationList()
    companion object {


        @Volatile private var instance: StationRepository? = null

        fun getInstance(stationDao: StationDao) =
            instance ?: synchronized(this) {
                instance ?: StationRepository(stationDao).also { instance = it }
            }
    }
}