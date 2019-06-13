package org.mobiletoolkit.firebase.firestore

/**
 * Created by Sebastian Owodzin on 02/06/2019.
 */
actual typealias Firestore = com.google.firebase.firestore.FirebaseFirestore

actual typealias CollectionReference = com.google.firebase.firestore.CollectionReference
actual typealias Query = com.google.firebase.firestore.Query
actual typealias DocumentReference = com.google.firebase.firestore.DocumentReference
actual typealias DocumentSnapshot = com.google.firebase.firestore.DocumentSnapshot

actual fun Firestore.withPath(path: String) = collection(path)

actual fun CollectionReference.documentWithID(id: String) = document(id)
actual fun CollectionReference.getWithCallback(queryBlock: ((query: Query) -> Query)?, callback: (documents: List<DocumentSnapshot>, error: String?) -> Unit) {
    (queryBlock?.let { it(this) } ?: this).get().addOnCompleteListener { task ->
        callback(
            (if (task.isSuccessful) task.result?.documents else null) ?: listOf(),
            task.exception?.localizedMessage
        )
    }
}

//actual fun CollectionReference.getWithCallback(queryBlock: (query: Query) -> Unit, callback: (documents: List<DocumentSnapshot>, error: String?) -> Unit) {
//    queryBlock(this).get().addOnCompleteListener { task ->
//        callback(
//            (if (task.isSuccessful) task.result?.documents else null) ?: listOf(),
//            task.exception?.localizedMessage
//        )
//    }
//}

actual fun DocumentReference.docID() = id
actual fun DocumentReference.getWithCallback(callback: (snapshot: DocumentSnapshot?, error: String?) -> Unit) {
    get().addOnCompleteListener { task ->
        callback(
            if (task.isSuccessful) task.result else null,
            task.exception?.localizedMessage
        )
    }
}

actual fun DocumentSnapshot.documentReference() = reference