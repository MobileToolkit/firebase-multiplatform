package org.mobiletoolkit.firebase.exampleapp.firestore

import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Mapper
import org.mobiletoolkit.firebase.firestore.Firestore
import org.mobiletoolkit.firebase.firestore.FirestoreRepository

/**
 * Created by Sebastian Owodzin on 18/05/2019.
 */
actual class ProductsRepository : FirestoreRepository<Product>(Firestore.getInstance()) {

    override val collectionPath = "/products"

    override fun deserialize(snapshot: DocumentSnapshot): Product? =
        snapshot.data?.let { Mapper.unmap(Product.serializer(), it) }
}