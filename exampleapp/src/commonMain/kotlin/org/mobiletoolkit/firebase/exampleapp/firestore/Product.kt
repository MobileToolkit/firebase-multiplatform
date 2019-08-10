package org.mobiletoolkit.firebase.exampleapp.firestore

import kotlinx.serialization.Serializable
import org.mobiletoolkit.firebase.firestore.FirestoreModel

/**
 * Created by Sebastian Owodzin on 18/05/2019.
 */
@Serializable
data class Product(
    val name: String,
    val description: String,
    val price: Double
) : FirestoreModel()
