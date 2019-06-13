package org.mobiletoolkit.firebase.firestore

typealias CollectionCallback = (documents: List<DocumentSnapshot>, error: String?) -> Unit
typealias CollectionListener = (documents: List<DocumentSnapshot>, error: String?) -> Unit

typealias DocumentCallback = (document: DocumentSnapshot?, error: String?) -> Unit
typealias DocumentListener = (document: DocumentSnapshot?, error: String?) -> Unit