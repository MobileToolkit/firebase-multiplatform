package org.mobiletoolkit.firebase.exampleapp.firestore

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Mapper
import kotlinx.serialization.Serializable
import org.mobiletoolkit.firebase.firestore.FirestoreModel
import org.mobiletoolkit.firebase.firestore.FirestoreRepository
import org.mobiletoolkit.repository.Serializer

/**
 * Created by Sebastian Owodzin on 18/04/2019.
 */
class ProductsRepository(
    db: FirebaseFirestore
) : FirestoreRepository<Product>(db) {

    override val collectionPath = "/products"

    override val serializer = ProductSerializer()
}

@UseExperimental(ImplicitReflectionSerializer::class)
class ProductSerializer: Serializer<Product> {
    override fun deserialize(data: Any): Product? {
        return (data as? DocumentSnapshot)?.data?.let { Mapper.unmap(it) }
    }
}

@Serializable
data class Product(
    val name: String,
    val description: String,
    val price: Double
) : FirestoreModel()