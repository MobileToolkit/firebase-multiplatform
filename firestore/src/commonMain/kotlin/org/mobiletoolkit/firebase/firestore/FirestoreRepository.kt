package org.mobiletoolkit.firebase.firestore

import kotlinx.serialization.KSerializer
import org.mobiletoolkit.repository.AsyncRepositoryCallback
import org.mobiletoolkit.repository.ObservableAsyncRepository
import org.mobiletoolkit.repository.ObservableAsyncRepositoryListener

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
abstract class FirestoreRepository<Entity : FirestoreModel>(
    private val db: Firestore,
    private val collectionPath: String,
    protected val serializer: KSerializer<Entity>,
    private val debugOn: Boolean = false
) : ObservableAsyncRepository<String, Entity> {

    protected val collectionReference: CollectionReference
        get() = db.collectionReference(collectionPath)

    protected fun documentReference(identifier: String?): DocumentReference = collectionReference.documentWithID(identifier)

//    protected fun buildEntity(snapshot: DocumentSnapshot): Entity? = with(snapshot.deserialize(serializer)) {
//        this?.documentReference = snapshot.documentReference
//        return@with this
//    }

    override fun get(callback: AsyncRepositoryCallback<List<Entity>>) {
        collectionReference.getWithCallback { documents, error ->
            callback(
                documents.mapNotNull { it.deserialize(serializer) },
                error
            )
        }
    }

    fun get(queryBlock: (query: Query) -> Query, callback: AsyncRepositoryCallback<List<Entity>>) {
        collectionReference.getWithCallback(queryBlock) { documents, error ->
            callback(
                documents.mapNotNull { it.deserialize(serializer) },
                error
            )
        }
    }

    override fun get(identifier: String, callback: AsyncRepositoryCallback<Entity?>) {
        documentReference(identifier).getWithCallback { document, error ->
            callback(
                document?.deserialize(serializer),
                error
            )
        }
    }

    override fun create(entity: Entity, identifier: String?, callback: AsyncRepositoryCallback<Boolean>) {
        documentReference(identifier).setDocumentData(entity.serialize(serializer), callback)
    }

    override fun update(entity: Entity, callback: AsyncRepositoryCallback<Boolean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(entity: Entity, callback: AsyncRepositoryCallback<Boolean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val repositoryObservers = mutableListOf<ObserverReference>()

    override fun observe(listener: ObservableAsyncRepositoryListener<List<Entity>>): ObservableAsyncRepository.ObserverReference {
        val observerReference = ObserverReference(
            collectionReference.getWithSnapshotListener { documents, error ->
                listener(
                    documents.mapNotNull { it.deserialize(serializer) },
                    error
                )
            }
        )

        repositoryObservers.add(observerReference)

        return observerReference
    }

    fun observe(queryBlock: (query: Query) -> Query, listener: ObservableAsyncRepositoryListener<List<Entity>>): ObservableAsyncRepository.ObserverReference {
        val observerReference = ObserverReference(
            collectionReference.getWithSnapshotListener(queryBlock) { documents, error ->
                listener(
                    documents.mapNotNull { it.deserialize(serializer) },
                    error
                )
            }
        )

        repositoryObservers.add(observerReference)

        return observerReference
    }

    override fun observe(identifier: String, listener: ObservableAsyncRepositoryListener<Entity>): ObservableAsyncRepository.ObserverReference {
        val observerReference = ObserverReference(
                documentReference(identifier).getWithSnapshotListener { document, error ->
                listener(
                    document?.deserialize(serializer),
                    error
                )
            }
        )

        repositoryObservers.add(observerReference)

        return observerReference
    }

    override fun stopAllObservers() {
        repositoryObservers.forEach(ObserverReference::stop)
        repositoryObservers.clear()
    }

    data class ObserverReference(
        private val listenerRegistration: ListenerRegistration
    ) : ObservableAsyncRepository.ObserverReference {
        override fun stop() = listenerRegistration.remove()
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