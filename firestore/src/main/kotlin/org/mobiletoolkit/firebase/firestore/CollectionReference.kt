package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 12/08/2019.
 */
actual typealias CollectionReference = com.google.firebase.firestore.CollectionReference

actual fun CollectionReference.documentWithID(id: String?): DocumentReference = if (id === null) document() else document(id)

actual fun CollectionReference.getWithCallback(queryBlock: ((query: Query) -> Query)?, callback: Callback<List<DocumentSnapshot>>) {
    (queryBlock?.let { it(this) } ?: this).get().addOnCompleteListener { task ->
        callback(
            (if (task.isSuccessful) task.result?.documents else null) ?: listOf(),
            task.exception
        )
    }
}

actual fun CollectionReference.getWithSnapshotListener(queryBlock: ((query: Query) -> Query)?, listener: Callback<List<DocumentSnapshot>>) : ListenerRegistration {
    return (queryBlock?.let { it(this) } ?: this).addSnapshotListener { snapshot, exception ->
        listener(
            snapshot?.documents ?: listOf(),
            exception
        )
    }
}