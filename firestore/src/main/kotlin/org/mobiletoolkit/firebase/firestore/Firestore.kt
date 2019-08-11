package org.mobiletoolkit.firebase.firestore

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Mapper

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */
actual typealias CollectionReference = com.google.firebase.firestore.CollectionReference
actual fun CollectionReference.documentWithID(id: String?): DocumentReference = if (id === null) document() else document(id)
actual fun CollectionReference.getWithCallback(queryBlock: ((query: Query) -> Query)?, callback: CollectionCallback) {
    (queryBlock?.let { it(this) } ?: this).get().addOnCompleteListener { task ->
        callback(
            (if (task.isSuccessful) task.result?.documents else null) ?: listOf(),
            task.exception
        )
    }
}
actual fun CollectionReference.getWithSnapshotListener(queryBlock: ((query: Query) -> Query)?, listener: CollectionListener) : ListenerRegistration {
    return (queryBlock?.let { it(this) } ?: this).addSnapshotListener { snapshot, exception ->
        listener(
            snapshot?.documents ?: listOf(),
            exception
        )
    }
}

actual typealias DocumentReference = com.google.firebase.firestore.DocumentReference
actual val DocumentReference.documentId: String
    get() = id
actual fun DocumentReference.getWithCallback(callback: DocumentCallback) {
    get().addOnCompleteListener { task ->
        callback(
            if (task.isSuccessful) task.result else null,
            task.exception
        )
    }
}
actual fun DocumentReference.getWithSnapshotListener(listener: DocumentListener) : ListenerRegistration {
    return addSnapshotListener { snapshot, exception ->
        listener(
            snapshot,
            exception
        )
    }
}
actual fun DocumentReference.setDocumentData(data: Map<String, Any>) {
    set(data).addOnCompleteListener { task ->

    }
}

actual typealias DocumentSnapshot = com.google.firebase.firestore.DocumentSnapshot
actual val DocumentSnapshot.documentReference: DocumentReference
    get() = reference
actual val DocumentSnapshot.documentData: Map<String, Any>?
    get() = data
actual fun <Entity : FirestoreModel> DocumentSnapshot.deserialize(serializer: KSerializer<Entity>): Entity? {
    val entity = documentData?.let { Mapper.unmap(serializer, it) }

    entity?.documentReference = documentReference

    return entity
}

actual typealias Firestore = com.google.firebase.firestore.FirebaseFirestore
actual fun Firestore.collectionReference(path: String) = collection(path)

actual typealias ListenerRegistration = com.google.firebase.firestore.ListenerRegistration

actual typealias Query = com.google.firebase.firestore.Query

actual typealias QuerySnapshot = com.google.firebase.firestore.QuerySnapshot

actual typealias WriteBatch = com.google.firebase.firestore.WriteBatch

actual typealias Transaction = com.google.firebase.firestore.Transaction