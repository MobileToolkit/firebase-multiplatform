//package org.mobiletoolkit.coroutines
//
//import kotlinx.coroutines.suspendCancellableCoroutine
//import kotlin.coroutines.resume
//import kotlin.coroutines.resumeWithException
//
///**
// * Created by Sebastian Owodzin on 21/05/2019.
// */
//interface Callback<T> {
//    fun onComplete(result: T)
//    fun onError(e: Exception)
//}
//
//suspend fun <T> awaitCallback(block: (Callback<T>) -> Unit): T =
//    suspendCancellableCoroutine { continuation ->
//        block(object : Callback<T> {
//            override fun onComplete(result: T) = continuation.resume(result)
//            override fun onError(e: Exception) = continuation.resumeWithException(e)
//        })
//    }
//
////fun <A, T> toSuspendFunction(fn: (A, Callback<T>) -> Unit): suspend (A) -> T = { a: A ->
////    awaitCallback { fn(a, it) }
////}
////
////fun <A, B, T> toSuspendFunction(fn: (A, B, Callback<T>) -> Unit): suspend (A, B) -> T = { a: A, b: B ->
////    awaitCallback { fn(a, b, it) }
////}