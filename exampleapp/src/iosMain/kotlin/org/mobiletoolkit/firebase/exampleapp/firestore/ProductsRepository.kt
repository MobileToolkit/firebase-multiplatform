package org.mobiletoolkit.firebase.exampleapp.firestore

import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import org.mobiletoolkit.extensions.toJsonString
import org.mobiletoolkit.firebase.firestore.DocumentSnapshot
import org.mobiletoolkit.firebase.firestore.Firestore
import org.mobiletoolkit.firebase.firestore.FirestoreRepository

/**
 * Created by Sebastian Owodzin on 18/05/2019.
 */
@UseExperimental(UnstableDefault::class)
actual class ProductsRepository : FirestoreRepository<Product>(
    Firestore.firestore(),
    "/products"
) {

    override fun deserialize(snapshot: DocumentSnapshot): Product? =
        snapshot.data()?.let {
            try {
                with(Json.nonstrict) {
                    fromJson(Product.serializer(), parseJson(it.toJsonString()))
                }

//                Mapper.unmap(Product.serializer(), it as Map<String, Any>) // FIXME - this is not yet supported on iOS
            } catch (e: Exception) {
                e.printStackTrace()
                println("exception: ${e.message}")
                null
            }
        }
}
