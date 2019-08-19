package be.hogent.student.native_apps_1_android_creative_app_bramhuys.data

import com.google.gson.annotations.SerializedName

/**
 * Model StationDepartureModel
 *
 */
data class StationDepartureModel(
    @SerializedName("delay")
    
     val delay: String,
    @SerializedName("station")
    
     val station: String,
    @SerializedName("stationinfo")
    
     val stationinfo: StationModel,
    @SerializedName("time")
    
     val time: String,
    @SerializedName("vehicle")
    
     val vehicle: String,
    @SerializedName("platform")
    
     val platform: String,
    @SerializedName("platforminfo")
    
     val platforminfo: StationPlatforminfoModel,
    @SerializedName("canceled")
    
     val canceled: String,
    @SerializedName("departureConnection")
    
     val departureConnection: String,
    @SerializedName("direction")
    
     val direction: StationDirectionModel,
    @SerializedName("left")
    
     val left: String,
    @SerializedName("walking")
    
     val walking: String,
    @SerializedName("occupancy")
    
     val occupancy: StationOccupancyModel
    )
