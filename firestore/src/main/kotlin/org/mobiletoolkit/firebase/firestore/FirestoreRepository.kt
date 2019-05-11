package org.mobiletoolkit.firebase.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import org.mobiletoolkit.repository.AsyncRepository
import org.mobiletoolkit.repository.AsyncRepositoryCallback
import org.mobiletoolkit.repository.Serializer

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
actual abstract class FirestoreRepository<Entity : FirestoreModel>(
    val db: FirebaseFirestore
) : AsyncRepository<String, Entity> {

    protected actual abstract val collectionPath: String

    protected actual abstract val serializer: Serializer<Entity>

    protected val collectionReference: CollectionReference
        get() = db.collection(collectionPath)

    protected fun documentReference(identifier: String): DocumentReference =
        collectionReference.document(identifier)

    private fun deserialize(snapshot: DocumentSnapshot): Entity? = with (serializer.deserialize(snapshot)) {
        this?.documentReference = snapshot.reference
        return@with this
    }

    override fun exists(identifier: String, callback: AsyncRepositoryCallback<Boolean>) {
        documentReference(identifier).get().addOnCompleteListener {
            val exists = it.isSuccessful && it.result?.exists() == true

            callback(exists, it.exception?.let { exception -> Error(exception) })
        }
    }

    override fun get(identifier: String, callback: AsyncRepositoryCallback<Entity?>) {
        documentReference(identifier).get().addOnCompleteListener { task ->
            val entity = if (task.isSuccessful && task.result?.exists() == true) {
                task.result?.let { deserialize(it) }
            } else null

            callback(entity, task.exception?.let { exception -> Error(exception) })
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

    override fun get(callback: AsyncRepositoryCallback<List<Entity>>) {
        collectionReference.get().addOnCompleteListener { task ->
            val entities = if (task.isSuccessful) {
                task.result?.documents?.mapNotNull { deserialize(it) }
            } else null

            callback(entities ?: listOf(), task.exception?.let { exception -> Error(exception) })
        }
    }
}