package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */
actual typealias Firestore = com.google.firebase.firestore.FIRFirestore

actual typealias CollectionReference = com.google.firebase.firestore.FIRCollectionReference
actual typealias Query = com.google.firebase.firestore.FIRQuery
actual typealias QuerySnapshot = com.google.firebase.firestore.FIRQuerySnapshot
actual typealias ListenerRegistration = com.google.firebase.firestore.FIRListenerRegistrationProtocol
actual typealias DocumentReference = com.google.firebase.firestore.FIRDocumentReference
actual typealias DocumentSnapshot = com.google.firebase.firestore.FIRDocumentSnapshot

actual fun Firestore.withPath(path: String) = collectionWithPath(path)

actual fun CollectionReference.documentWithID(id: String) = documentWithPath(id)
actual fun CollectionReference.getWithCallback(queryBlock: ((query: Query) -> Query)?, callback: CollectionCallback) {
    (queryBlock?.let { it(this) } ?: this).getDocumentsWithCompletion { snapshot, nsError ->
        callback(
            snapshot?.documents as? List<DocumentSnapshot> ?: listOf(),
            nsError?.localizedDescription
        )
    }
}
actual fun CollectionReference.getWithSnapshotListener(queryBlock: ((query: Query) -> Query)?, listener: CollectionListener) : ListenerRegistration {
    return (queryBlock?.let { it(this) } ?: this).addSnapshotListener { querySnapshot, nsError ->
        listener(
            querySnapshot?.documents as? List<DocumentSnapshot> ?: listOf(),
            nsError?.localizedDescription
        )
    }
}

actual fun DocumentReference.docID() = documentID
actual fun DocumentReference.getWithCallback(callback: DocumentCallback) {
    getDocumentWithCompletion { snapshot, error ->
        callback(
            snapshot,
            error?.localizedDescription
        )
    }
}

actual fun DocumentSnapshot.documentReference() = reference