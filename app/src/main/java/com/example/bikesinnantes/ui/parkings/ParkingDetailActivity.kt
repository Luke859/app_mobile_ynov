package com.example.bikesinnantes.ui.parkings

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.bikesinnantes.R
import com.example.bikesinnantes.model.parkingSelected

class ParkingDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_detail)

        val parkingName = findViewById<TextView>(R.id.parkingName)
        val button = findViewById<Button>(R.id.buttonOpenMap)

        if (parkingSelected != null){

        }
        parkingSelected?.let { parking ->
            parkingName.text = parking.grpNom
            button.setOnClickListener {
                // Display a label at the location of Google's Sydney office
                val gmmIntentUri =
                    Uri.parse("geo:0,0?q=${parking.latitude},${parking.longitude}(${parking.grpNom})")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }
}