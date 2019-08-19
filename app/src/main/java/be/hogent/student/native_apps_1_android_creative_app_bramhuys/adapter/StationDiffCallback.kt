package be.hogent.student.native_apps_1_android_creative_app_bramhuys.adapter

import androidx.recyclerview.widget.DiffUtil
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.data.StationConnectionModel
/**
 * Controle of item al in lijst bestaat
 *
 */
class StationDiffCallback : DiffUtil.ItemCallback<StationConnectionModel>() {
    override fun areItemsTheSame(oldItem: StationConnectionModel, newItem: StationConnectionModel): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StationConnectionModel, newItem: StationConnectionModel): Boolean {
       return oldItem == newItem
    }

}