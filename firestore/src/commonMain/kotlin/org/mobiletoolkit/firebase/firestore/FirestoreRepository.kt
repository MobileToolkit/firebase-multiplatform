package org.mobiletoolkit.firebase.firestore

import org.mobiletoolkit.repository.AsyncRepositoryCallback
import org.mobiletoolkit.repository.ObservableAsyncRepository
import org.mobiletoolkit.repository.ObservableAsyncRepositoryListener

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
abstract class FirestoreRepository<Entity : FirestoreModel>(
    private val db: Firestore,
    private val collectionPath: String,
    private val debugOn: Boolean = false
) : ObservableAsyncRepository<String, Entity> {

    protected val collectionReference: CollectionReference
        get() = db.collectionReference(collectionPath)

    protected fun documentReference(identifier: String): DocumentReference = collectionReference.documentWithID(identifier)

    protected abstract fun deserialize(snapshot: DocumentSnapshot): Entity?

    protected fun buildEntity(snapshot: DocumentSnapshot): Entity? = with(deserialize(snapshot)) {
        this?.documentReference = snapshot.documentReference()
        return@with this
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

    override fun get(identifier: String, callback: AsyncRepositoryCallback<Entity?>) {
        documentReference(identifier).getWithCallback { document, error ->
            callback(
                document?.let { buildEntity(it) },
                error
            )
        }
    }

    override fun create(entity: Entity, identifier: String?, callback: AsyncRepositoryCallback<Boolean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
                    documents.mapNotNull { buildEntity(it) },
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
                    documents.mapNotNull { buildEntity(it) },
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
                    document?.let { buildEntity(it) },
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
        override fun stop() = removeListenerRegistration(listenerRegistration)
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