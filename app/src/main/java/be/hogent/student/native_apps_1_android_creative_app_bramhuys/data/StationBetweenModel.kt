package be.hogent.student.native_apps_1_android_creative_app_bramhuys.data

import com.google.gson.annotations.SerializedName

/**
 * Model StationBetweenModel
 *
 */
data class StationBetweenModel(

        @SerializedName("version")
        val version: String,
        @SerializedName("timestamp")
        val timestamp: Number,
        @SerializedName("connection")
        val station: List<StationConnectionModel>
    ) {

        override fun toString() = version


    }
