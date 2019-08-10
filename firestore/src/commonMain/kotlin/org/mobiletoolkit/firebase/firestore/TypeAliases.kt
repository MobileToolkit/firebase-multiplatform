package org.mobiletoolkit.firebase.firestore

typealias CollectionCallback = (documents: List<DocumentSnapshot>, error: Exception?) -> Unit
typealias CollectionListener = (documents: List<DocumentSnapshot>, error: Exception?) -> Unit

typealias DocumentCallback = (document: DocumentSnapshot?, error: Exception?) -> Unit
typealias DocumentListener = (document: DocumentSnapshot?, error: Exception?) -> Unit