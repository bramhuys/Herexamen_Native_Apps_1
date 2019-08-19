package be.hogent.student.native_apps_1_android_creative_app_bramhuys.View


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.ViewModel.StationListViewModel
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.adapter.StationAdapter
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.databinding.FragmentStationBinding
import be.hogent.student.native_apps_1_android_creative_app_bramhuys.util.InjectorUtils

/**
 * StationListFragment
 *
 */
class StationListFragment :Fragment() {

    private lateinit var viewModel: StationListViewModel
    private lateinit var text : String
    private lateinit var recview : RecyclerView
    companion object {

        fun newInstance(): StationListFragment {
            return StationListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStationBinding.inflate(inflater,container,false)
        val context = context ?: return binding.root

        val from = arguments!!.getString("stationclosest")

        val to = arguments!!.getString("input")
        val factory = InjectorUtils.provideStationListViewModelFactory(context)

        viewModel = ViewModelProviders.of(this, factory).get(StationListViewModel::class.java)
       // val stations = viewModel.getStationList()

        val adapter = StationAdapter()

        binding.stationList.adapter= adapter
        subscribeUi(adapter ,from, to,binding)

        viewModel.getStatus().observe(this, Observer { status ->

           if (status == false  )  {
                Toast.makeText(context,"Plaats niet gevonden",Toast.LENGTH_LONG).show()
                fragmentManager!!.popBackStackImmediate()
            }
        })


      //  binding.stationList.visibility = View.VISIBLE



        return binding.root
    }
    /**
     *  Observer voor Connectie List
     */
    private fun subscribeUi(adapter: StationAdapter, from:String , to:String,binding:FragmentStationBinding) {

        viewModel.getConnection(from,to).observe(viewLifecycleOwner, Observer { connection ->

            if (connection != null) {

                adapter.submitList(connection)
                binding.progressBar.visibility = View.INVISIBLE
                binding.stationList.visibility = View.VISIBLE
            }

        })
    }



    }

