package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */
expect class Firestore

expect fun Firestore.collectionReference(path: String): CollectionReference
