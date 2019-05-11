package org.mobiletoolkit.firebase.firestore

import org.mobiletoolkit.repository.AsyncRepository
import org.mobiletoolkit.repository.Serializer

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
expect abstract class FirestoreRepository<Entity : FirestoreModel> : AsyncRepository<String, Entity> {

    protected abstract val collectionPath: String

    protected abstract val serializer: Serializer<Entity>
}