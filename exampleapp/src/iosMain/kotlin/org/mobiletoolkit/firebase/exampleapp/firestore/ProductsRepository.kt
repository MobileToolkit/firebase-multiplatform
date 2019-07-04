package org.mobiletoolkit.firebase.exampleapp.firestore

import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Mapper
import kotlinx.serialization.json.Json
import org.mobiletoolkit.firebase.firestore.DocumentSnapshot
import org.mobiletoolkit.firebase.firestore.Firestore
import org.mobiletoolkit.firebase.firestore.FirestoreRepository

/**
 * Created by Sebastian Owodzin on 18/05/2019.
 */
actual class ProductsRepository : FirestoreRepository<Product>(Firestore.firestore(), "/products") {

    @UseExperimental(ImplicitReflectionSerializer::class)
    override fun deserialize(snapshot: DocumentSnapshot): Product? =
        snapshot.data()?.let {
            try {
                val data = it as Map<String, Any>
                val jsonString = "{${data.map { "\"${it.key}\": \"${it.value}\"" }.joinToString(", ")}}"

                Json.nonstrict.fromJson(Product.serializer(), Json.nonstrict. parseJson(jsonString))

//                Mapper.unmap(Product.serializer(), it as Map<String, Any>) // FIXME - this seems to be not working on iOS
            } catch (e: Exception) {
                e.printStackTrace()
                println("exception: ${e.message}")
                null
            }
        }
}
