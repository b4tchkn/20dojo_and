package jp.co.cyberagent.dojo2020.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

typealias StateListener = (FirebaseAuth) -> Unit

object FirebaseAuthentication {

    private var stateListenerList: MutableList<StateListener?> = mutableListOf()
    private val firebaseAuth = Firebase.auth.apply {
        addAuthStateListener { auth ->
            stateListenerList.forEach { it?.invoke(auth) }
        }
    }

    fun addStateListener(stateListener: StateListener) {
        stateListenerList.add(stateListener)
    }

    fun signIn(
        email: String,
        password: String,
        consumer: (FirebaseUser) -> Unit = {}
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val user = result.user ?: return@addOnSuccessListener

                consumer(user)
            }
    }

    fun signUp(
        email: String,
        password: String,
        consumer: (FirebaseUser) -> Unit = {}
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val user = result.user ?: return@addOnSuccessListener

                consumer(user)
            }
    }

    fun signOut() {
        firebaseAuth.signOut()
    }
}