package jp.co.cyberagent.dojo2020.data.remote.auth

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object FirebaseAuthentication {

    private val firebaseAuth = Firebase.auth

    fun firebaseUser() = firebaseAuth.currentUser

    fun signIn(
        email: String,
        password: String,
        consumer: (FirebaseUser) -> Unit
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
        consumer: (FirebaseUser) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val user = result.user ?: return@addOnSuccessListener

                consumer(user)
            }
    }
}