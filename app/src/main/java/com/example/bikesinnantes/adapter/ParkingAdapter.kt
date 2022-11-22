package com.example.bikesinnantes.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bikesinnantes.R
import com.example.bikesinnantes.model.Parking
import com.example.bikesinnantes.model.currentLocation
import com.example.bikesinnantes.model.parkingSelected
import com.example.bikesinnantes.ui.parkings.ParkingDetailActivity

class ParkingAdapter(private val parkings:List<Parking>, private val context: Context):
    RecyclerView.Adapter<ParkingAdapter.ViewHolder>() {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        val name : TextView = itemView.findViewById(R.id.name)
        val adress : TextView = itemView.findViewById(R.id.adress)
        val availability : TextView = itemView.findViewById(R.id.availabilty)
        val status : ImageView = itemView.findViewById(R.id.status)
        val distance : TextView = itemView.findViewById((R.id.distance))

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_parkings, parent, false)
        return ViewHolder(view);
    }

    //Pour chaque view_id on met à jour les composants de la view (cardView: CardView,  name:TextView)
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n", "ResourceAsColor")

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parking = parkings[position]
        holder.name.text = "${parking.grpIdentifiant}-${parking.grpNom}"
        holder.adress.text = "(Cliquez pour avoir la localisation)"

        if (currentLocation != null){
            holder.distance.text = "${String.format("%.2f", currentLocation!!.distanceTo(parking.toLocation()) /1000)} km"
        }else{
            holder.distance.text = "- km"
        }

        if (parking.disponibilite.toInt() in 0..parking.grpComplet.toInt() ){
            holder.name.setText("${parking.grpNom} (COMPLET)")
            holder.name.setTextColor(context.getColor(R.color.noAvailableParkings))
        }else if(parking.disponibilite.toInt() <= -1 ) {
            holder.name.setText("${parking.grpNom} (PAS DISPONIBLE)")
            holder.adress.text = "(Localisation non disponible)"
            holder.name.setTextColor(context.getColor(R.color.invalidParkings))
        } else {
            holder.name.setTextColor(context.getColor(R.color.availableParkings))
        }

        holder.availability.text = parking.showDetails()
        if (parking.grpStatut.toInt() == 5){
            holder.status.setImageResource(R.drawable.ic_baseline_circle_green)
        }else if (parking.grpStatut.toInt() == 2) {
            holder.name.setText("${parking.grpNom}\n(Ouvert que pour les abonnés)")
            holder.name.setTextColor(context.getColor(R.color.membersParkings))
            holder.status.setImageResource(R.drawable.ic_baseline_subscriptions_24)
        } else if (parking.grpStatut.toInt() == 1) {
            holder.status.setImageResource(R.drawable.ic_baseline_circle_red)
        } else {
            holder.status.setImageResource(R.drawable.ic_baseline_do_not_disturb_24)
        }

        //Quand on click sur la card view -> on ouvre une nouvelle fenetre
        holder.cardView.setOnClickListener {
            val intent = Intent(context, ParkingDetailActivity::class.java)
            parkingSelected = parking
            context.startActivity(intent)
        }
    }

    //On retourne le nombre d'éléments de la liste stations
    override fun getItemCount(): Int {
        return parkings.size
    }
}