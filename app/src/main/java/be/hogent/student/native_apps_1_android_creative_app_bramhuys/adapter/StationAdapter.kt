package be.hogent.student.native_apps_1_android_creative_app_bramhuys.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.data.StationConnectionModel
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.databinding.ListItemStationBinding
/**
 * Lijst met Trein connecties binden aan een RexyclerView
 *
 */
class StationAdapter : androidx.recyclerview.widget.ListAdapter<StationConnectionModel,StationAdapter.ViewHolder>(StationDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationAdapter.ViewHolder {
return ViewHolder(ListItemStationBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: StationAdapter.ViewHolder, position: Int) {
        val station = getItem(position)
        holder.apply {
            itemView.tag = station.id
            bind(station)

             }
    }
    private fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {

        }
    }
    class ViewHolder(
        private val binding: ListItemStationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind( item: StationConnectionModel) {
            binding.apply {
               // clickListener = listener
                connection = item
                executePendingBindings()
            }
        }
    }
}