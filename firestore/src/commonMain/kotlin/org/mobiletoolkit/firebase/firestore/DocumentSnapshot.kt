package org.mobiletoolkit.firebase.firestore

import kotlinx.serialization.KSerializer
import kotlinx.serialization.UnstableDefault
import org.mobiletoolkit.firebase.firestore.FirestoreModel

/**
 * Created by Sebastian Owodzin on 12/08/2019.
 */
expect class DocumentSnapshot

expect val DocumentSnapshot.documentReference: DocumentReference

expect val DocumentSnapshot.documentData: Map<String, Any>?

@UnstableDefault
expect fun <Entity : FirestoreModel> DocumentSnapshot.deserialize(serializer: KSerializer<Entity>): Entity?