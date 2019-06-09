package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */

expect class Firestore
expect class CollectionReference
expect class DocumentReference
expect class DocumentSnapshot

expect fun Firestore.withPath(path: String): CollectionReference

expect fun CollectionReference.docWithID(id: String): DocumentReference

expect fun DocumentReference.docID(): String
expect fun DocumentReference.getWithCallback(callback: (snapshot: DocumentSnapshot?, error: String?) -> Unit)

expect fun DocumentSnapshot.documentReference(): DocumentReference