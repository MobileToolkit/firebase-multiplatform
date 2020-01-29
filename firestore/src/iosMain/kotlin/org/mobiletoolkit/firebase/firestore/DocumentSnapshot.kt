package org.mobiletoolkit.firebase.firestore

import kotlinx.serialization.KSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import org.mobiletoolkit.extensions.toJsonString
import org.mobiletoolkit.firebase.firestore.FirestoreModel

/**
 * Created by Sebastian Owodzin on 12/08/2019.
 */
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