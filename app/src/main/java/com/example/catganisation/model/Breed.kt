package com.example.catganisation.model

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Breed(
    val adaptability: Long? = null,
    val affectionLevel: Long? = null,
    val altNames: String? = null,
    val cfaURL: String? = null,
    val childFriendly: Long? = null,
    val country_code: String? = null,
    val country_codes: String? = null,
    val description: String? = null,
    val dogFriendly: Long? = null,
    val energyLevel: Long? = null,
    val experimental: Long? = null,
    val grooming: Long? = null,
    val hairless: Long? = null,
    val healthIssues: Long? = null,
    val hypoallergenic: Long? = null,
    val id: String? = null,
    val indoor: Long? = null,
    val intelligence: Long? = null,
    val lap: Long? = null,
    val lifeSpan: String? = null,
    val name: String? = null,
    val natural: Long? = null,
    val origin: String? = null,
    val rare: Long? = null,
    val rex: Long? = null,
    val sheddingLevel: Long? = null,
    val shortLegs: Long? = null,
    val socialNeeds: Long? = null,
    val strangerFriendly: Long? = null,
    val suppressedTail: Long? = null,
    val temperament: String? = null,
    val vcahospitalsURL: String? = null,
    val vetstreetURL: String? = null,
    val vocalisation: Long? = null,
    val wikipedia_url: String? = null,

    ) : Parcelable {
    @IgnoredOnParcel
    var urlImage: String? = null
}