package org.mobiletoolkit.firebase.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import org.mobiletoolkit.repository.AsyncRepository

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
actual abstract class FirestoreRepository<Entity : FirestoreModel>(
    private val db: FirebaseFirestore
) : AsyncRepository<String, Entity> {

    protected actual abstract val collectionPath: String

    protected val collectionReference: CollectionReference
        get() = db.collection(collectionPath)

    protected fun documentReference(identifier: String): DocumentReference =
        collectionReference.document(identifier)

    protected abstract fun deserialize(snapshot: DocumentSnapshot): Entity?

    private fun buildEntity(snapshot: DocumentSnapshot): Entity? = with (deserialize(snapshot)) {
        this?.documentReference = snapshot.reference
        return@with this
    }

    override fun get(identifier: String, callback: (entity: Entity?, error: String?) -> Unit) {
        documentReference(identifier).get().addOnCompleteListener { task ->
            callback(
                if (task.isSuccessful) task.result?.let { buildEntity(it) } else null,
                task.exception?.localizedMessage
            )
        }
    }

//    override fun get(identifier: String, callback: AsyncRepositoryCallback<Entity?>) {
//        documentReference(identifier).get().addOnCompleteListener { task ->
//            callback(
//                if (task.isSuccessful) task.result?.let { buildEntity(it) } else null,
//                task.exception?.localizedMessage
//            )
//        }
//    }
//
//    override fun create(entity: Entity, identifier: String?, callback: AsyncRepositoryCallback<Boolean>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun update(entity: Entity, callback: AsyncRepositoryCallback<Boolean>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun delete(entity: Entity, callback: AsyncRepositoryCallback<Boolean>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun get(callback: AsyncRepositoryCallback<List<Entity>>) {
//        collectionReference.get().addOnCompleteListener { task ->
//            callback(
//                (if (task.isSuccessful) task.result?.documents?.mapNotNull { buildEntity(it) } else null) ?: listOf(),
//                task.exception?.localizedMessage
//            )
//        }
//    }


//
//    override fun get(identifier: String): Entity? {
//        var result: Entity? = null
//
//        MainScope().launch {
//            result = documentReference(identifier).get().await()?.let { if (it.exists()) buildEntity(it) else null }
//        }
//
//        return result
//    }
//
//    override fun get(): List<Entity> {
//        var results: List<Entity> = listOf()
//
//        MainScope().launch {
//            results = collectionReference.get().await()?.documents?.mapNotNull { buildEntity(it) } ?: listOf()
//        }
//
//        return results
//    }
}

//actual fun getMainDispatcher(): CoroutineDispatcher = Dispatchers.Main