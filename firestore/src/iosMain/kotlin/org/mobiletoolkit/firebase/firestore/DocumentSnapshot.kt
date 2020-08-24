package org.mobiletoolkit.firebase.firestore

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.fromJson
import kotlinx.serialization.json.parseJson
import org.mobiletoolkit.extensions.toJsonString
import org.mobiletoolkit.firebase.firestore.FirestoreModel
import org.mobiletoolkit.json

/**
 * Created by Sebastian Owodzin on 12/08/2019.
 */
actual typealias DocumentSnapshot = native.firestore.FIRDocumentSnapshot

actual val DocumentSnapshot.documentReference: DocumentReference
    get() = reference

actual val DocumentSnapshot.documentData: Map<String, Any>?
    get() = data()?.map { it.key.toString() to it.value.toString() }?.toMap()
//actual fun <Entity> DocumentSnapshot.deserialize(serializer: KSerializer<Entity>): Entity? = documentData?.let { Mapper.unmap(serializer, it) }

actual fun <Entity : FirestoreModel> DocumentSnapshot.deserialize(serializer: KSerializer<Entity>): Entity? {
    val entity = documentData?.let { data ->
        // TODO - need to fake JSON parsing because Mapper.unmap() doesn't work on iOS
        with(json()) {
            decodeFromJsonElement(serializer, parseToJsonElement(data.toJsonString()))
        }
    }

    entity?.documentReference = documentReference

    return entity
}