package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */
actual typealias Firestore = com.google.firebase.firestore.FIRFirestore

actual typealias CollectionReference = com.google.firebase.firestore.FIRCollectionReference
actual typealias Query = com.google.firebase.firestore.FIRQuery
actual typealias DocumentReference = com.google.firebase.firestore.FIRDocumentReference
actual typealias DocumentSnapshot = com.google.firebase.firestore.FIRDocumentSnapshot

actual fun Firestore.withPath(path: String) = collectionWithPath(path)

actual fun CollectionReference.documentWithID(id: String) = documentWithPath(id)
actual fun CollectionReference.getWithCallback(queryBlock: ((query: Query) -> Query)?, callback: (documents: List<DocumentSnapshot>, error: String?) -> Unit) {
    (queryBlock?.let { it(this) } ?: this).getDocumentsWithCompletion { snapshot, error ->
        callback(
            snapshot?.documents as? List<DocumentSnapshot> ?: listOf(),
            error?.localizedDescription
        )
    }
}

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