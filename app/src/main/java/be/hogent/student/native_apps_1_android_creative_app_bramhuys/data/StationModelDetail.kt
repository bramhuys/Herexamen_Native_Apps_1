package be.hogent.student.native_apps_1_android_creative_app_bramhuys.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * Model StationModelDetail
 *
 * Entity om tabel naam toe te wijzen
 */
@Entity(tableName = "stations")
data class StationModelDetail (

    @PrimaryKey @SerializedName("version")
    val version: String,
    @SerializedName("timestamp")
    val timestamp: Number,
    @SerializedName("station")
    val station: List<StationModel>
)
{

    override fun toString() = version


}
