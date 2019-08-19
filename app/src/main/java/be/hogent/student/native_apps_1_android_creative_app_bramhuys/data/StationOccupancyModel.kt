package be.hogent.student.native_apps_1_android_creative_app_bramhuys.data


import com.google.gson.annotations.SerializedName

/**
 * Model StationOccupancyModel
 *
 */
    data class StationOccupancyModel(
    @SerializedName("@id")
     val idd: String,
    @SerializedName("name")
     val name: String

)