package org.mobiletoolkit.firebase.firestore

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.Transient
import org.mobiletoolkit.repository.Model

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
abstract class FirestoreModel : Model<String> {

    @Transient
    internal var documentReference: DocumentReference? = null

    @Transient
    override val identifier: String?
        get() = documentReference?.documentId

    @OptIn(ExperimentalSerializationApi::class)
    @Suppress("UNCHECKED_CAST")
    internal fun <Entity : FirestoreModel> serialize(serializer: KSerializer<Entity>): Map<String, Any> = Properties.encodeToMap(serializer, this as Entity)
}