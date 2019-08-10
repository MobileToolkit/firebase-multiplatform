package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */
actual typealias CollectionReference = com.google.firebase.firestore.CollectionReference
actual typealias DocumentReference = com.google.firebase.firestore.DocumentReference
actual typealias DocumentSnapshot = com.google.firebase.firestore.DocumentSnapshot
actual typealias Firestore = com.google.firebase.firestore.FirebaseFirestore
actual typealias ListenerRegistration = com.google.firebase.firestore.ListenerRegistration
actual typealias Query = com.google.firebase.firestore.Query
actual typealias QuerySnapshot = com.google.firebase.firestore.QuerySnapshot

actual fun CollectionReference.documentWithID(id: String) = document(id)
actual fun CollectionReference.getWithCallback(queryBlock: ((query: Query) -> Query)?, callback: CollectionCallback) {
    (queryBlock?.let { it(this) } ?: this).get().addOnCompleteListener { task ->
        callback(
            (if (task.isSuccessful) task.result?.documents else null) ?: listOf(),
            task.exception
        )
    }
}
actual fun CollectionReference.getWithSnapshotListener(queryBlock: ((query: Query) -> Query)?, listener: CollectionListener) : ListenerRegistration {
    return (queryBlock?.let { it(this) } ?: this).addSnapshotListener { querySnapshot, exception ->
        listener(
            querySnapshot?.documents ?: listOf(),
            exception
        )
    }
}

actual fun DocumentReference.docID() = id
actual fun DocumentReference.getWithCallback(callback: DocumentCallback) {
    get().addOnCompleteListener { task ->
        callback(
            if (task.isSuccessful) task.result else null,
            task.exception
        )
    }
}

actual fun DocumentReference.getWithSnapshotListener(listener: DocumentListener) : ListenerRegistration {
    return addSnapshotListener { documentSnapshot, exception ->
        listener(
            documentSnapshot,
            exception
        )
    }
}

actual fun DocumentSnapshot.documentReference() = reference

actual fun Firestore.collectionReference(path: String) = collection(path)

actual fun removeListenerRegistration(listenerRegistration: ListenerRegistration) = listenerRegistration.remove()