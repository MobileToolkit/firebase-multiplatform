package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */
actual typealias CollectionReference = com.google.firebase.firestore.FIRCollectionReference
actual typealias DocumentReference = com.google.firebase.firestore.FIRDocumentReference
actual typealias DocumentSnapshot = com.google.firebase.firestore.FIRDocumentSnapshot
actual typealias Firestore = com.google.firebase.firestore.FIRFirestore
actual typealias ListenerRegistration = com.google.firebase.firestore.FIRListenerRegistrationProtocol
actual typealias Query = com.google.firebase.firestore.FIRQuery
actual typealias QuerySnapshot = com.google.firebase.firestore.FIRQuerySnapshot

actual fun CollectionReference.documentWithID(id: String) = documentWithPath(id)
actual fun CollectionReference.getWithCallback(queryBlock: ((query: Query) -> Query)?, callback: CollectionCallback) {
    (queryBlock?.let { it(this) } ?: this).getDocumentsWithCompletion { snapshot, error ->
        callback(
            snapshot?.documents?.mapNotNull { if (it is DocumentSnapshot) it else null } ?: listOf(),
            error?.run { Exception(localizedDescription) }
        )
    }
}
actual fun CollectionReference.getWithSnapshotListener(queryBlock: ((query: Query) -> Query)?, listener: CollectionListener) : ListenerRegistration {
    return (queryBlock?.let { it(this) } ?: this).addSnapshotListener { snapshot, error ->
        listener(
            snapshot?.documents?.mapNotNull { if (it is DocumentSnapshot) it else null } ?: listOf(),
            error?.run { Exception(localizedDescription) }
        )
    }
}

actual fun DocumentReference.docID() = documentID
actual fun DocumentReference.getWithCallback(callback: DocumentCallback) {
    getDocumentWithCompletion { snapshot, error ->
        callback(
            snapshot,
            error?.run { Exception(localizedDescription) }
        )
    }
}
actual fun DocumentReference.getWithSnapshotListener(listener: DocumentListener) : ListenerRegistration {
    return addSnapshotListener { snapshot, error ->
        listener(
            snapshot,
            error?.run { Exception(localizedDescription) }
        )
    }
}

actual fun DocumentSnapshot.documentReference() = reference

actual fun Firestore.collectionReference(path: String) = collectionWithPath(path)

actual fun removeListenerRegistration(listenerRegistration: ListenerRegistration) = listenerRegistration.remove()