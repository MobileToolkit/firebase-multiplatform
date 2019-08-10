package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */
expect class CollectionReference
expect class DocumentReference
expect class DocumentSnapshot
expect class Firestore
expect interface ListenerRegistration
expect class Query
expect class QuerySnapshot

expect fun CollectionReference.documentWithID(id: String): DocumentReference
expect fun CollectionReference.getWithCallback(queryBlock: ((query: Query) -> Query)? = null, callback: CollectionCallback)
expect fun CollectionReference.getWithSnapshotListener(queryBlock: ((query: Query) -> Query)? = null, listener: CollectionListener) : ListenerRegistration

expect fun DocumentReference.docID(): String
expect fun DocumentReference.getWithCallback(callback: DocumentCallback)
expect fun DocumentReference.getWithSnapshotListener(listener: DocumentListener) : ListenerRegistration

expect fun DocumentSnapshot.documentReference(): DocumentReference

expect fun Firestore.collectionReference(path: String): CollectionReference

expect fun removeListenerRegistration(listenerRegistration: ListenerRegistration)