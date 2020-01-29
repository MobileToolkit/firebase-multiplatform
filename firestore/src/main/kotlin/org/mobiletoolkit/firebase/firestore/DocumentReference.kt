package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 12/08/2019.
 */
actual typealias DocumentReference = com.google.firebase.firestore.DocumentReference

actual val DocumentReference.documentId: String
    get() = id

actual fun DocumentReference.getWithCallback(callback: Callback<DocumentSnapshot?>) {
    get().addOnCompleteListener { task ->
        callback(
            if (task.isSuccessful) task.result else null,
            task.exception
        )
    }
}

actual fun DocumentReference.getWithSnapshotListener(listener: Callback<DocumentSnapshot?>) : ListenerRegistration {
    return addSnapshotListener { snapshot, exception ->
        listener(
            snapshot,
            exception
        )
    }
}

actual fun DocumentReference.setDocumentData(data: Map<String, Any>, callback: Callback<Boolean>) {
    set(data).addOnCompleteListener {
        callback(
            it.isSuccessful,
            it.exception
        )
    }
}