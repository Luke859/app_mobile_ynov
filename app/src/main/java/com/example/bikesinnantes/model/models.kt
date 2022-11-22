package com.example.bikesinnantes.model

import android.location.Location
import kotlinx.serialization.*

var currentLocation: Location? = null
var stationSelected:Station? = null
var allStations:List<Station>? = null
var parkingSelected:Parking? = null
var allParkings:List<Parking>? = null

@Serializable
data class Station (
    val id: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val status: String,
    val address: String,
    val bikeStands: Long,
    val availableBikes: Long,
    val availableBikeStands: Long,
    val recordId: String
){
    fun toLocation(): Location {

        val location = Location("")

        location.latitude = latitude
        location.longitude = longitude

        return location
    }

    fun showDetails(): CharSequence? {
        return " \uD83D\uDEB2 ${availableBikes} \uD83D\uDCE3${availableBikeStands} ✅$bikeStands"
    }
}

@Serializable
data class Parking (
    val id: Long,
    //Le nom du parking
    val grpNom: String,
    //Nombres de places disponibles pour usagers horaires
    val grpDisponible: Long,
    val disponibilite: Long,
    //Coordonnées
    val latitude: Double,
    val longitude: Double,
    /* Les differents status
    0 Invalide (comptage hors service) 	Neutre (affichage au noir)
    1 Groupe parking fermé pour tous clients	FERME
    2 Groupe parking fermé au client horaires et ouvert pour les abonnés (exemple : un parking fermé aux clients horaires la nuit ou le dimanche)	ABONNES
    5 Groupe parking ouvert à tous les clients. Le nombre de places correspond au nombre de places destinées aux clients horaires */
    val grpStatut: Long,
    //Identifiant
    val grpIdentifiant: String,
    //Nombre maximum de places dans le parking
    val grpExploitation:String,
    //Seuil à partir du quel l'affichage COMPLET s'affiche
    val grpComplet: Long,
    //Id de l'équipement public
    val idobj: String,
){
    fun toLocation(): Location {

        val location = Location("")

        location.latitude = latitude
        location.longitude = longitude

        return location
    }

    fun showDetails(): CharSequence? {
        return "${disponibilite} ✅\n${grpExploitation} max"
    }
}

