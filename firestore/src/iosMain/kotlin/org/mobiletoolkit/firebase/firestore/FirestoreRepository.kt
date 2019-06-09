package org.mobiletoolkit.firebase.firestore

import com.google.firebase.firestore.FIRCollectionReference
import com.google.firebase.firestore.FIRDocumentReference
import com.google.firebase.firestore.FIRDocumentSnapshot
import com.google.firebase.firestore.FIRFirestore
import kotlinx.cinterop.staticCFunction
import org.mobiletoolkit.repository.AsyncRepository
import platform.Foundation.NSThread
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_sync_f
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.native.concurrent.Continuation1
import kotlin.native.concurrent.callContinuation1

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
//actual abstract class FirestoreRepository<Entity : FirestoreModel>(
//    private val db: Firestore
//) : AsyncRepository<String, Entity> {
//
//    protected actual abstract val collectionPath: String
//
//    protected val collectionReference: FIRCollectionReference
//        get() = db.collectionWithPath(collectionPath)
//
//    protected fun documentReference(identifier: String): FIRDocumentReference =
//        collectionReference.documentWithPath(identifier)
//
//    protected abstract fun deserialize(snapshot: FIRDocumentSnapshot): Entity?
//
//    private fun buildEntity(snapshot: FIRDocumentSnapshot): Entity? = with (deserialize(snapshot)) {
//        this?.documentReference = snapshot.reference
//        return@with this
//    }
//
//    override fun get(identifier: String, callback: (entity: Entity?, error: String?) -> Unit) {
//        documentReference(identifier).getDocumentWithCompletion { snapshot, error ->
//            callback(
//                snapshot?.let { if (it.exists()) buildEntity(it) else null },
//                error?.localizedDescription
//            )
//        }
//
////        try {
////            documentReference(identifier).getDocumentWithCompletion { snapshot, error ->
////                callback(
////                    snapshot?.let { if (it.exists()) buildEntity(it) else null },
////                    error?.localizedDescription
////                )
////            }
////        } catch (e: Error) {
////            callback(null, "error")
////        }
//    }
//
////    override fun get(identifier: String, callback: AsyncRepositoryCallback<Entity?>) {
////        MainScope().launch {
////            suspendCoroutine<Entity?> { continuation ->
////                documentReference(identifier).getDocumentWithCompletion { snapshot, error ->
////                    if (error != null) {
////                        continuation.resumeWithException(Exception(error.localizedDescription))
////                    } else {
////                        continuation.resume(snapshot?.let { if (it.exists()) buildEntity(it) else null })
////                    }
////                }
////            }
////        }
////
//////        runBlocking {
//////            job.join()
//////        }
////    }
////
////    override fun create(entity: Entity, identifier: String?, callback: AsyncRepositoryCallback<Boolean>) {
////        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
////    }
////
////    override fun update(entity: Entity, callback: AsyncRepositoryCallback<Boolean>) {
////        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
////    }
////
////    override fun delete(entity: Entity, callback: AsyncRepositoryCallback<Boolean>) {
////        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
////    }
////
////    override fun get(callback: AsyncRepositoryCallback<List<Entity>>) {
////        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
////    }
//
//
//
////
////    override fun get(identifier: String): Entity? {
////        var result: Entity? = null
////
////        MainScope().launch {
////            result = awaitCallback { continuation ->
////                documentReference(identifier).getDocumentWithCompletion { snapshot, error ->
////                    if (error != null) {
////                        continuation.onError(Exception(error.localizedDescription))
////                    } else {
////                        continuation.onComplete(snapshot?.let { if (it.exists()) buildEntity(it) else null })
////                    }
////                }
////            }
////        }
////
////        return result
////    }
////
////    override fun get(): List<Entity> {
////        var results: List<Entity> = listOf()
////
////        MainScope().launch {
////            results = awaitCallback { continuation ->
////                collectionReference.getDocumentsWithCompletion { snapshot, error ->
////                    if (error != null) {
////                        continuation.onError(Exception(error.localizedDescription))
////                    } else {
////                        continuation.onComplete(snapshot?.documents?.mapNotNull { buildEntity(it as FIRDocumentSnapshot) } ?: listOf())
////                    }
////                }
////            }
////        }
////
////        return results
////    }
//}

//private class MainDispatcher: CoroutineDispatcher() {
//    override fun dispatch(context: CoroutineContext, block: Runnable) {
//        dispatch_async(dispatch_get_main_queue()) { block.run() }
//    }
//}
//
//internal class MainScope: CoroutineScope {
//    private val dispatcher = MainDispatcher()
//    private val job = Job()
//
//    override val coroutineContext: CoroutineContext
//        get() = dispatcher + job
//}
//
////actual fun getMainDispatcher(): CoroutineDispatcher = MainDispatcher()
////
////private class MainDispatcher: CoroutineDispatcher() {
////    override fun dispatch(context: CoroutineContext, block: Runnable) {
////        dispatch_async(dispatch_get_main_queue()) { block.run() }
////    }
////}
//
//inline fun <T1> mainContinuation(singleShot: Boolean = true, noinline block: (T1) -> Unit) = Continuation1(
//    block, staticCFunction { invokerArg ->
//        if (NSThread.isMainThread()) {
//            invokerArg!!.callContinuation1<T1>()
//        } else {
//            dispatch_sync_f(dispatch_get_main_queue(), invokerArg, staticCFunction { args ->
//                args!!.callContinuation1<T1>()
//            })
//        }
//    }, singleShot)