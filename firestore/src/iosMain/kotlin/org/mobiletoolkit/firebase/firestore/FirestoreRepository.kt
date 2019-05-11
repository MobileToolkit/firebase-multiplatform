package org.mobiletoolkit.firebase.firestore

import com.google.firebase.firestore.FIRCollectionReference
import com.google.firebase.firestore.FIRDocumentReference
import com.google.firebase.firestore.FIRDocumentSnapshot
import com.google.firebase.firestore.FIRFirestore
import org.mobiletoolkit.repository.AsyncRepository
import org.mobiletoolkit.repository.AsyncRepositoryCallback
import org.mobiletoolkit.repository.Serializer

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
actual abstract class FirestoreRepository<Entity : FirestoreModel>(
    val db: FIRFirestore
) : AsyncRepository<String, Entity> {

    protected actual abstract val collectionPath: String

    protected actual abstract val serializer: Serializer<Entity>

    protected val collectionReference: FIRCollectionReference
        get() = db.collectionWithPath(collectionPath)

    protected fun documentReference(identifier: String): FIRDocumentReference =
        collectionReference.documentWithPath(identifier)

    private fun deserialize(snapshot: FIRDocumentSnapshot): Entity? = with (serializer.deserialize(snapshot)) {
        this?.documentReference = snapshot.reference
        return@with this
    }

    override fun exists(identifier: String, callback: AsyncRepositoryCallback<Boolean>) {
        documentReference(identifier).getDocumentWithCompletion { snapshot, error ->
            val exists = error == null && snapshot?.exists == true

            callback(exists, Error(error.toString()))
        }
    }

    override fun get(identifier: String, callback: AsyncRepositoryCallback<Entity?>) {
        documentReference(identifier).getDocumentWithCompletion { snapshot, error ->
            val entity = if (error == null && snapshot?.exists == true) {
                deserialize(snapshot)
            } else null

            callback(entity, Error(error.toString()))
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
        collectionReference.getDocumentsWithCompletion { snapshot, error ->
            val entities = if (error == null) {
                snapshot?.documents?.mapNotNull { deserialize(it as FIRDocumentSnapshot) }
            } else null

            callback(entities ?: listOf(), Error(error.toString()))
        }
    }
}