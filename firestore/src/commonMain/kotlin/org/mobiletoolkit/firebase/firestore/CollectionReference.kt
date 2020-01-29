package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 12/08/2019.
 */
expect class CollectionReference

expect fun CollectionReference.documentWithID(id: String? = null): DocumentReference

expect fun CollectionReference.getWithCallback(queryBlock: ((query: Query) -> Query)? = null, callback: Callback<List<DocumentSnapshot>>)

expect fun CollectionReference.getWithSnapshotListener(queryBlock: ((query: Query) -> Query)? = null, listener: Callback<List<DocumentSnapshot>>) : ListenerRegistration