package be.hogent.student.native_apps_1_android_creative_app_bramhuys.ViewModel

import android.widget.Toast
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.data.*
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * ViewModel voor [StationListFragment]
 *
 */
class StationListViewModel  internal constructor(
    private val stationRepository: StationRepository
): ViewModel() {

    private val stations = MediatorLiveData<List<StationModel>>()
    private val status = MutableLiveData<Boolean?>()
    private val stationList = ArrayList<StationModel>()
    private val test = MutableLiveData<List<StationConnectionModel>>()
    private val stationConnection = MediatorLiveData<List<StationConnectionModel>>()

   init {
       val myExecutor : Executor = Executors.newSingleThreadExecutor()
       myExecutor.execute {
           stationList.addAll(stationRepository.getStationList())
       }


   }

    fun getStations() = stations

    fun getStationList() = stationList
    fun getStatus() = status
    fun getConnection(from:String,to:String) : MediatorLiveData<List<StationConnectionModel>>{
        lateinit var endpoints: StationApi


        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
            request.addHeader("Accept", "application/json")
            val request1 = request.build()
            chain.proceed(request1)
        }

        httpClient.connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
        httpClient.readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)

        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(UrlManager.API_HOST)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        endpoints = retrofit.create(StationApi::class.java)

        val call: Call<StationBetweenModel> = endpoints.getStationsBetween(from,to)

        call.enqueue(object : Callback<StationBetweenModel> {
            override fun onFailure(call: Call<StationBetweenModel>, t: Throwable) {
                System.out.println(t.message  )
            }

            override fun onResponse(call: Call<StationBetweenModel>, response: Response<StationBetweenModel>) {
                val live = response.body()

                stationConnection.addSource(test) { test -> stationConnection.value = test }
                if (response.isSuccessful){
                    stationConnection.value = live!!.station
                    status.value = true
                }else {
                   status.value = false
                }
            }
        })

        return stationConnection
    }


}
