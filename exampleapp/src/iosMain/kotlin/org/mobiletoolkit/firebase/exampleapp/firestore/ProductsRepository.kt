package org.mobiletoolkit.firebase.exampleapp.firestore

import com.google.firebase.firestore.FIRDocumentSnapshot
import kotlinx.serialization.Mapper
import org.mobiletoolkit.firebase.firestore.Firestore
import org.mobiletoolkit.firebase.firestore.FirestoreRepository

/**
 * Created by Sebastian Owodzin on 18/05/2019.
 */
actual class ProductsRepository : FirestoreRepository<Product>(Firestore.firestore()) {

    override val collectionPath = "/products"

    override fun deserialize(snapshot: FIRDocumentSnapshot): Product? =
        snapshot.data()?.let {
            try {
                val data = it as Map<String, Any>

                Product(data["name"] as String, data["description"] as String, data["price"] as Double)
//                Mapper.unmap(Product.serializer(), it as Map<String, Any>) // FIXME - this seems to be not working on iOS
            } catch (e: Exception) {
                e.printStackTrace()
                println("exception: ${e.message}")
//                println("exception: ${e.cause?.message}")
                null
            }
        }
}
