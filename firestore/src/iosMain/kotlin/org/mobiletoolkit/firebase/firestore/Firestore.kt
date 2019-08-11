package org.mobiletoolkit.firebase.firestore

import kotlinx.serialization.KSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import org.mobiletoolkit.extensions.toJsonString

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */
actual typealias CollectionReference = com.google.firebase.firestore.FIRCollectionReference
actual fun CollectionReference.documentWithID(id: String?): DocumentReference = if (id === null) documentWithAutoID() else documentWithPath(id)
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

actual typealias DocumentReference = com.google.firebase.firestore.FIRDocumentReference
actual val DocumentReference.documentId: String
    get() = documentID
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
actual fun DocumentReference.setDocumentData(data: Map<String, Any>) {
    setData(data.map { it.key as? Any to it.value }.toMap()) { error ->

    }
}

actual typealias DocumentSnapshot = com.google.firebase.firestore.FIRDocumentSnapshot
actual val DocumentSnapshot.documentReference: DocumentReference
    get() = reference
actual val DocumentSnapshot.documentData: Map<String, Any>?
    get() = data()?.map { it.key.toString() to it.value.toString() }?.toMap()
//actual fun <Entity> DocumentSnapshot.deserialize(serializer: KSerializer<Entity>): Entity? = documentData?.let { Mapper.unmap(serializer, it) }
@UnstableDefault
actual fun <Entity : FirestoreModel> DocumentSnapshot.deserialize(serializer: KSerializer<Entity>): Entity? {
    val entity = documentData?.let { data ->
        // TODO - need to fake JSON parsing because Mapper.unmap() doesn't work on iOS
        with(Json.nonstrict) {
            fromJson(serializer, parseJson(data.toJsonString()))
        }
    }

    entity?.documentReference = documentReference

    return entity
}

actual typealias Firestore = com.google.firebase.firestore.FIRFirestore
actual fun Firestore.collectionReference(path: String) = collectionWithPath(path)

actual typealias ListenerRegistration = com.google.firebase.firestore.FIRListenerRegistrationProtocol

actual typealias Query = com.google.firebase.firestore.FIRQuery

actual typealias QuerySnapshot = com.google.firebase.firestore.FIRQuerySnapshot

actual typealias WriteBatch = com.google.firebase.firestore.FIRWriteBatch

actual typealias Transaction = com.google.firebase.firestore.FIRTransaction