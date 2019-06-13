package org.mobiletoolkit.firebase.firestore

import org.mobiletoolkit.repository.AsyncRepository
import org.mobiletoolkit.repository.AsyncRepositoryCallback

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
abstract class FirestoreRepository<Entity : FirestoreModel>(
    private val db: Firestore
) : AsyncRepository<String, Entity> {

    protected abstract val collectionPath: String

    protected val collectionReference: CollectionReference
        get() = db.withPath(collectionPath)

    protected fun documentReference(identifier: String): DocumentReference = collectionReference.documentWithID(identifier)

    protected abstract fun deserialize(snapshot: DocumentSnapshot): Entity?

    protected fun buildEntity(snapshot: DocumentSnapshot): Entity? = with(deserialize(snapshot)) {
        this?.documentReference = snapshot.documentReference()
        return@with this
    }

    override fun get(identifier: String, callback: AsyncRepositoryCallback<Entity?>) {
        documentReference(identifier).getWithCallback { snapshot, error ->
            callback(
                snapshot?.let { buildEntity(it) },
                error
            )
        }
    }

    override fun get(callback: AsyncRepositoryCallback<List<Entity>>) {
        collectionReference.getWithCallback { documents, error ->
            callback(
                documents.mapNotNull { buildEntity(it) },
                error
            )
        }
    }

    fun get(queryBlock: (query: Query) -> Query, callback: AsyncRepositoryCallback<List<Entity>>) {
        collectionReference.getWithCallback(queryBlock) { documents, error ->
            callback(
                documents.mapNotNull { buildEntity(it) },
                error
            )
        }
    }
}

//expect fun getMainDispatcher(): CoroutineDispatcher
//
//internal class MainScope: CoroutineScope {
//    private val dispatcher = getMainDispatcher()
//    private val job = Job()
//
//    override val coroutineContext: CoroutineContext
//        get() = dispatcher + job
//}