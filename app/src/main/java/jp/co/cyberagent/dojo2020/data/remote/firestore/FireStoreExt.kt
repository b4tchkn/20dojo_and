package jp.co.cyberagent.dojo2020.data.remote.firestore

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

fun FirebaseFirestore.memosRef(uid: String): CollectionReference {
    return this
        .collection(FireStoreConstants.USERS)
        .document(uid)
        .collection(FireStoreConstants.MEMOS)
}

fun CollectionReference.document(id: Int): DocumentReference {
    return this.document(id.toString())
}