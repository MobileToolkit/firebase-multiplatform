package org.mobiletoolkit.firebase.firestore

import kotlinx.serialization.KSerializer

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */
expect class CollectionReference
expect fun CollectionReference.documentWithID(id: String? = null): DocumentReference
expect fun CollectionReference.getWithCallback(queryBlock: ((query: Query) -> Query)? = null, callback: CollectionCallback)
expect fun CollectionReference.getWithSnapshotListener(queryBlock: ((query: Query) -> Query)? = null, listener: CollectionListener) : ListenerRegistration

expect class DocumentReference
expect val DocumentReference.documentId: String
expect fun DocumentReference.getWithCallback(callback: DocumentCallback)
expect fun DocumentReference.getWithSnapshotListener(listener: DocumentListener) : ListenerRegistration
expect fun DocumentReference.setDocumentData(data: Map<String, Any>)

expect class DocumentSnapshot
expect val DocumentSnapshot.documentReference: DocumentReference
expect val DocumentSnapshot.documentData: Map<String, Any>?
expect fun <Entity : FirestoreModel> DocumentSnapshot.deserialize(serializer: KSerializer<Entity>): Entity?

expect class Firestore
expect fun Firestore.collectionReference(path: String): CollectionReference

expect interface ListenerRegistration {
    fun remove()
}

expect class Query

expect class QuerySnapshot

expect class WriteBatch

expect class Transaction