package be.hogent.student.native_apps_1_android_creative_app_bramhuys.data

import com.google.gson.annotations.SerializedName

/**
 * Model StationPlatforminfoModel
 *
 */
data class StationPlatforminfoModel(

    @SerializedName("name")
     val name: String,
    @SerializedName("normal")
     val normal: String
    )
