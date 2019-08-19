package be.hogent.student.native_apps_1_android_creative_app_bramhuys.data

import com.google.gson.annotations.SerializedName

/**
 * Model StationArrivalModel
 *
 */
data class StationArrivalModel (
    @SerializedName("delay")
    
     val delay: String ,
    @SerializedName("station")
    
     val station: String ,
    @SerializedName("stationinfo")
    
     val stationinfo: StationModel ,
    @SerializedName("time")
    
     val time: String ,
    @SerializedName("vehicle")
    
     val vehicle: String ,
    @SerializedName("platform")
    
     val platform: String ,
    @SerializedName("platforminfo")
    
     val platforminfo: StationPlatforminfoModel ,
    @SerializedName("canceled")
    
     val canceled: String ,
    @SerializedName("direction")
    
     val direction: StationDirectionModel,
    @SerializedName("arrived")
    
     val arrived: String ,
    @SerializedName("walking")
    
     val walking: String
    )
