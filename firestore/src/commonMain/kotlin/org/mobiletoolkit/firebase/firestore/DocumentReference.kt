package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 12/08/2019.
 */
expect class DocumentReference

expect val DocumentReference.documentId: String

expect fun DocumentReference.getWithCallback(callback: Callback<DocumentSnapshot?>)

expect fun DocumentReference.getWithSnapshotListener(listener: Callback<DocumentSnapshot?>) : ListenerRegistration

expect fun DocumentReference.setDocumentData(data: Map<String, Any>, callback: Callback<Boolean>)