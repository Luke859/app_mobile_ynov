package com.example.bikesinnantes.ui.parkings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bikesinnantes.adapter.ParkingAdapter
import com.example.bikesinnantes.api.ParkingApi
import com.example.bikesinnantes.api.RetrofitHelper
import com.example.bikesinnantes.databinding.FragmentParkingsBinding
import com.example.bikesinnantes.model.allParkings
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ParkingFragment : Fragment() {

    private var _binding: FragmentParkingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val parkingViewModel =
            ViewModelProvider(this).get(ParkingViewModel::class.java)

        _binding = FragmentParkingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recycler2View
        var progressBarParkings = binding.progressBarParkings

        parkingViewModel.parkings.observe(viewLifecycleOwner) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = ParkingAdapter(it, requireContext())
            progressBarParkings.visibility = View.GONE
            allParkings = it
        }

        val parkingApi = RetrofitHelper().getInstance().create(ParkingApi::class.java)
        GlobalScope.launch{
            val result = parkingApi.getParking()
            Log.d("PARKING", result.body().toString())
            parkingViewModel.parkings.postValue(result.body())
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}