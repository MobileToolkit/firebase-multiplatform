package org.mobiletoolkit.firebase.firestore

import com.google.firebase.firestore.DocumentReference
import kotlinx.serialization.*
import org.mobiletoolkit.repository.Model
import kotlin.jvm.Transient

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
//@Serializable
//actual open class FirestoreModel : Model<String> {
//
//    @Transient
//    internal var documentReference: DocumentReference? = null
//
//    override val identifier: String?
//        get() = documentReference?.id
//}

actual open class FirestoreModel : Model<String> {

    @Transient
    internal var documentReference: DocumentReference? = null

    override val identifier: String?
        get() = documentReference?.id

//    override fun equals(other: Any?): Boolean {
//        if (other is FirestoreModel) {
//            return other.identifier == identifier
//        }
//
//        return super.equals(other)
//    }
//
//    override fun hashCode(): Int {
//        return identifier?.hashCode() ?: super.hashCode()
//    }
}
//
//open class FirestoreModelSerializer : KSerializer<FirestoreModel> {
//    override fun deserialize(decoder: Decoder): FirestoreModel {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun serialize(encoder: Encoder, obj: FirestoreModel) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//}



//@UseExperimental(ImplicitReflectionSerializer::class)
//actual open class Serializer<T : Any> {
//    actual fun unserialize(data: Any): T? {
//        return null
//    }
//}

//@UseExperimental(ImplicitReflectionSerializer::class)
//inline fun <reified T: Any> DocumentSnapshot.toObject2(): T? = (data as? Map<String, Any>)?.let { Mapper.unmap(it) }