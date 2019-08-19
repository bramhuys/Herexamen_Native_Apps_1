package be.hogent.student.native_apps_1_android_creative_app_bramhuys.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



/**
 * Model StationModel
 *
 * Entity om tabel naam toe te wijzen
 */
@Entity(tableName = "stationdetail")
data class StationModel (
    @PrimaryKey @ColumnInfo(name = "id")
    val id: String,
    @SerializedName("@id")
    val idd: String,
    val locationX:Double,
    val locationY: Double,
    val name: String,
    val standardname: String
)
    {

        override fun toString() = name


    }