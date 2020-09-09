package jp.co.cyberagent.dojo2020.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

typealias StateListener = (FirebaseAuth) -> Unit

object FirebaseAuthentication {
    private val firebaseAuth = Firebase.auth

    @ExperimentalCoroutinesApi
    val currentUser = callbackFlow {
        val callback = FirebaseAuth.AuthStateListener { auth ->
            offer(auth.currentUser)
        }

        firebaseAuth.addAuthStateListener(callback)

        awaitClose { firebaseAuth.removeAuthStateListener(callback) }
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