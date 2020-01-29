package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 12/08/2019.
 */
actual typealias CollectionReference = com.google.firebase.firestore.FIRCollectionReference

actual fun CollectionReference.documentWithID(id: String?): DocumentReference = if (id === null) documentWithAutoID() else documentWithPath(id)

actual fun CollectionReference.getWithCallback(queryBlock: ((query: Query) -> Query)?, callback: Callback<List<DocumentSnapshot>>) {
    (queryBlock?.let { it(this) } ?: this).getDocumentsWithCompletion { snapshot, error ->
        callback(
            snapshot?.documents?.mapNotNull { if (it is DocumentSnapshot) it else null } ?: listOf(),
            error?.run { Exception(localizedDescription) }
        )
    }
}

actual fun CollectionReference.getWithSnapshotListener(queryBlock: ((query: Query) -> Query)?, listener: Callback<List<DocumentSnapshot>>) : ListenerRegistration {
    return (queryBlock?.let { it(this) } ?: this).addSnapshotListener { snapshot, error ->
        listener(
            snapshot?.documents?.mapNotNull { if (it is DocumentSnapshot) it else null } ?: listOf(),
            error?.run { Exception(localizedDescription) }
        )
    }
}