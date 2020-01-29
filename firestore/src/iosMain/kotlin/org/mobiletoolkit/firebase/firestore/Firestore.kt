package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */
actual typealias Firestore = com.google.firebase.firestore.FIRFirestore

actual fun Firestore.collectionReference(path: String) = collectionWithPath(path)