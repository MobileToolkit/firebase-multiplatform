package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */
expect class Firestore

expect class CollectionReference
expect class Query
expect class QuerySnapshot
expect interface ListenerRegistration
expect class DocumentReference
expect class DocumentSnapshot

expect fun Firestore.withPath(path: String): CollectionReference

//typealias CollectionCallback = (documents: List<DocumentSnapshot>, error: String?) -> Unit
//typealias CollectionListener = (documents: List<DocumentSnapshot>, error: String?) -> Unit

expect fun CollectionReference.documentWithID(id: String): DocumentReference
expect fun CollectionReference.getWithCallback(queryBlock: ((query: Query) -> Query)? = null, callback: CollectionCallback)
expect fun CollectionReference.getWithSnapshotListener(queryBlock: ((query: Query) -> Query)? = null, listener: CollectionListener) : ListenerRegistration

//typealias DocumentCallback = (document: DocumentSnapshot?, error: String?) -> Unit
//typealias DocumentListener = (document: DocumentSnapshot?, error: String?) -> Unit

expect fun DocumentReference.docID(): String
expect fun DocumentReference.getWithCallback(callback: DocumentCallback)

expect fun DocumentSnapshot.documentReference(): DocumentReference