package org.mobiletoolkit.firebase.firestore

import com.google.firebase.firestore.FIRDocumentReference
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.mobiletoolkit.repository.Model

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
@Serializable
actual open class FirestoreModel : Model<String> {

    @Transient
    internal var documentReference: FIRDocumentReference? = null

    override val identifier: String?
        get() = documentReference?.documentID
}

//actual class Serializer<T : Any> {
//    actual fun unserialize(data: Any): T? {
//        return null
//    }
//}