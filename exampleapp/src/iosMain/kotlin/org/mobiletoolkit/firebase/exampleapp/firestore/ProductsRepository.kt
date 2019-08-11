package org.mobiletoolkit.firebase.exampleapp.firestore

import org.mobiletoolkit.firebase.firestore.Firestore
import org.mobiletoolkit.firebase.firestore.FirestoreRepository

/**
 * Created by Sebastian Owodzin on 18/05/2019.
 */
actual class ProductsRepository : FirestoreRepository<Product>(
    Firestore.firestore(),
    "/products",
    Product.serializer()
)
