package be.hogent.student.native_apps_1_android_creative_app_bramhuys.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.ViewModel.StationListViewModel
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.data.*
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.util.STATION_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Worker om data op te halen en in de database te steken
 *
 */
class StationDatabaseWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams)  {

    private val TAG by lazy { StationDatabaseWorker::class.java.simpleName }
    lateinit var endpoints : StationApi

    override fun doWork(): Result {

       val stationType = object : TypeToken<List<StationModel>>() {}.type
       var jsonReader: JsonReader? = null

        return try {

            val inputStream = applicationContext.assets.open(STATION_DATA_FILENAME)
            jsonReader = JsonReader(inputStream.reader())
            val stationList: List<StationModel> = Gson().fromJson(jsonReader, stationType)
            val database = AppDatabase.getInstance(applicationContext)
            database.stationDao().insertAll(stationList)


            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor{chain ->
                val original = chain.request()
                val request = original.newBuilder()
                request.addHeader("Accept", "application/json")
                val request1 = request.build()
                chain.proceed(request1)
            }

            httpClient.connectTimeout(60,java.util.concurrent.TimeUnit.SECONDS)
            httpClient.readTimeout(60,java.util.concurrent.TimeUnit.SECONDS)
            val retrofit = Retrofit.Builder()
                .baseUrl(UrlManager.API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()

            endpoints = retrofit.create(StationApi::class.java)


            val call: Call<StationModelDetail> = endpoints.getStations()

            call.enqueue(object : Callback<StationModelDetail> {
                override fun onFailure(call: Call<StationModelDetail>, t: Throwable) {

                }

                override fun onResponse(call: Call<StationModelDetail>, response: Response<StationModelDetail>) {
                    val live = response.body()

                    val database = AppDatabase.getInstance(applicationContext)
                    val myExecutor : Executor = Executors.newSingleThreadExecutor()
                   myExecutor.execute {
                       database.stationDao().insertAll(live!!.station)
                   }


                }
            })
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        } finally {
            jsonReader?.close()
        }




    }

}