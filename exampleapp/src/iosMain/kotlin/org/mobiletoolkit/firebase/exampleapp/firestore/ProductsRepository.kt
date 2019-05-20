package org.mobiletoolkit.firebase.exampleapp.firestore

import com.google.firebase.firestore.FIRDocumentSnapshot
import com.google.firebase.firestore.FIRFirestore
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Mapper
import kotlinx.serialization.json.Json
import kotlinx.serialization.stringify
import org.mobiletoolkit.firebase.firestore.FirestoreRepository
import platform.Foundation.NSThread

/**
 * Created by Sebastian Owodzin on 18/05/2019.
 */
actual class ProductsRepository(
    db: FIRFirestore
) : FirestoreRepository<Product>(db) {

    override val collectionPath = "/products"

    override fun deserialize(snapshot: FIRDocumentSnapshot): Product? =
//        snapshot.data()?.let { Mapper.unmap(Product.serializer(), it as Map<String, Any>) }
        snapshot.data()?.let {
            try {
                println("data: $it")

                val data = it as Map<String, Any>

                Product(data["name"] as String, data["description"] as String, data["price"] as Double)

//                Mapper.unmap(Product.serializer(), it as Map<String, Any>)
            } catch (e: Exception) {
                e.printStackTrace()
                println("exception: ${e.cause?.message}")
                null
            }
        }
}