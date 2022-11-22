package com.example.bikesinnantes.ui.parkings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bikesinnantes.model.Parking

class ParkingViewModel : ViewModel() {

    private val _parkings = MutableLiveData<List<Parking>>().apply {
        value = ArrayList()
    }
    val parkings: MutableLiveData<List<Parking>> = _parkings
}