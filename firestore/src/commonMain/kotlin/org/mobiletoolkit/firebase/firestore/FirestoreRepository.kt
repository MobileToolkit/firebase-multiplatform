package org.mobiletoolkit.firebase.firestore

import org.mobiletoolkit.repository.AsyncRepository

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
expect abstract class FirestoreRepository<Entity : FirestoreModel> : AsyncRepository<String, Entity> {

    protected abstract val collectionPath: String
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