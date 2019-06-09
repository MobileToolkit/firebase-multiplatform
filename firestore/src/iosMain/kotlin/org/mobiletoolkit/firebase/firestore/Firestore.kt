package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */

actual typealias Firestore = com.google.firebase.firestore.FIRFirestore
actual typealias CollectionReference = com.google.firebase.firestore.FIRCollectionReference
actual typealias DocumentReference = com.google.firebase.firestore.FIRDocumentReference
actual typealias DocumentSnapshot = com.google.firebase.firestore.FIRDocumentSnapshot

actual fun Firestore.withPath(path: String) = collectionWithPath(path)

actual fun CollectionReference.docWithID(id: String) = documentWithPath(id)

actual fun DocumentReference.docID() = documentID
actual fun DocumentReference.getWithCallback(callback: (snapshot: DocumentSnapshot?, error: String?) -> Unit) {
    getDocumentWithCompletion { snapshot, error ->
        callback(
            snapshot,
            error?.localizedDescription
        )
    }
}

actual fun DocumentSnapshot.documentReference() = reference