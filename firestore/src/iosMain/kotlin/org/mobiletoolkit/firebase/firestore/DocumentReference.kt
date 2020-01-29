package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 12/08/2019.
 */
actual typealias DocumentReference = com.google.firebase.firestore.FIRDocumentReference

actual val DocumentReference.documentId: String
    get() = documentID

actual fun DocumentReference.getWithCallback(callback: Callback<DocumentSnapshot?>) {
    getDocumentWithCompletion { snapshot, error ->
        callback(
            snapshot,
            error?.run { Exception(localizedDescription) }
        )
    }
}

actual fun DocumentReference.getWithSnapshotListener(listener: Callback<DocumentSnapshot?>) : ListenerRegistration {
    return addSnapshotListener { snapshot, error ->
        listener(
            snapshot,
            error?.run { Exception(localizedDescription) }
        )
    }
}

actual fun DocumentReference.setDocumentData(data: Map<String, Any>, callback: Callback<Boolean>) {
    setData(data.map { it.key as? Any to it.value }.toMap()) { error ->
        callback(
            error == null,
            error?.run { Exception(localizedDescription) }
        )
    }
}