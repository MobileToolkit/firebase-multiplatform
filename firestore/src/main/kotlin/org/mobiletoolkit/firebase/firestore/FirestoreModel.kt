package org.mobiletoolkit.firebase.firestore

import com.google.firebase.firestore.DocumentReference
import kotlinx.serialization.Transient
import org.mobiletoolkit.repository.Model

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
//actual abstract class FirestoreModel : Model<String> {
//
////    @Transient
////    internal var documentReference: DocumentReference? = null
//
////    @Transient
////    override val identifier: String?
////        get() = documentReference?.id
//
////    override fun equals(other: Any?): Boolean {
////        if (other is FirestoreModel) {
////            return other.identifier == identifier
////        }
////
////        return super.equals(other)
////    }
////
////    override fun hashCode(): Int {
////        return identifier?.hashCode() ?: super.hashCode()
////    }
//}

actual typealias DocumentReference = DocumentReference