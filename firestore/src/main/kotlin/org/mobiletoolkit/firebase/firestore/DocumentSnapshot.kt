package org.mobiletoolkit.firebase.firestore

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Mapper
import org.mobiletoolkit.firebase.firestore.FirestoreModel

/**
 * Created by Sebastian Owodzin on 12/08/2019.
 */
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