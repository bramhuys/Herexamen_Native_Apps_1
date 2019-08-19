package be.hogent.student.native_apps_1_android_creative_app_bramhuys.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  Een interface voor het adres te linken voor een Request
 *
 */
interface StationApi {

    @GET(UrlManager.REPOS)
    fun getStations(): Call<StationModelDetail>

    @GET("connections/?format=json")
    fun getStationsBetween(@Query("from") from: String,@Query("to") to: String): Call<StationBetweenModel>



}