package be.hogent.student.native_apps_1_android_creative_app_bramhuys.data


import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*
/**
 * Model StationConnectionModel
 *
 */
data class StationConnectionModel(

        @SerializedName("id")
     val id: String,
        @SerializedName("departure")
     val departure: StationDepartureModel,
        @SerializedName("arrival")
     val arrival: StationArrivalModel,
        @SerializedName("duration")
     val duration: String,
        @SerializedName("occupancy")
    val occupancy: StationOccupancyModel
    )
    {

        override fun toString() = id

        @RequiresApi(Build.VERSION_CODES.O)
        fun  getArrivalDate() : String{

           val date =  java.time.format.DateTimeFormatter.ISO_INSTANT
                .format(java.time.Instant.ofEpochSecond(arrival.time.toLong()))

            return date.substring(11,19)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun  getDepartureDate() : String{

            val date =  java.time.format.DateTimeFormatter.ISO_INSTANT
                .format(java.time.Instant.ofEpochSecond(departure.time.toLong()))

            return date.substring(11,19)
        }
    }