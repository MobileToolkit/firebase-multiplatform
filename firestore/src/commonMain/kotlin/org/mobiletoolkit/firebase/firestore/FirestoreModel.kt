package org.mobiletoolkit.firebase.firestore

import kotlinx.serialization.Transient
import org.mobiletoolkit.repository.Model

/**
 * Created by Sebastian Owodzin on 17/04/2019.
 */
abstract class FirestoreModel : Model<String> {

    @Transient
    internal var documentReference: DocumentReference? = null

    override val identifier: String?
        get() = documentReference?.docID()
}