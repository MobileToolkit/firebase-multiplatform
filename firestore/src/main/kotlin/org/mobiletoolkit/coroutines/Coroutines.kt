//package org.mobiletoolkit.coroutines
//
//import com.google.android.gms.tasks.Task
////import com.google.android.gms.tasks.TaskCompletionSource
////import kotlinx.coroutines.Deferred
//import kotlin.coroutines.Continuation
//import kotlin.coroutines.resume
//import kotlin.coroutines.resumeWithException
//import kotlin.coroutines.suspendCoroutine
//
//suspend fun <T> Task<T>.await(): T? {
//    if (isComplete) return if (isSuccessful) result else throw exception!!
//
//    return suspendCoroutine { continuation: Continuation<T> ->
//        addOnSuccessListener { continuation.resume(it) }
//        addOnFailureListener { continuation.resumeWithException(it) }
//    }
//}
//
////fun <T> Deferred<T>.asTask(): Task<T> {
////    val source = TaskCompletionSource<T>()
////    invokeOnCompletion {
////        try {
////            source.setResult(getCompleted())
////        } catch (e: Exception) {
////            source.setException(e)
////        }
////    }
////    return source.task
////}