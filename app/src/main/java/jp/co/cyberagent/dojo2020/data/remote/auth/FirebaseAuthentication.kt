package jp.co.cyberagent.dojo2020.data.remote.auth

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

typealias AuthSuccessListener = (AuthResult) -> Unit
typealias AuthFailureListener = (Exception) -> Unit

object FirebaseAuthentication {
    const val GOOGLE_AUTH_INTENT_REQUEST: Int = 773
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

    fun signInWithGoogle(
        idToken: String,
        onSuccessListener: AuthSuccessListener = {},
        onFailureListener: AuthFailureListener = {}
    ) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { onSuccessListener(it) }
            .addOnFailureListener { onFailureListener(it) }
    }

    fun getClient(context: Context, clientId: String): GoogleSignInClient {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(context, googleSignInOptions)
    }

    fun signOut() {
        firebaseAuth.signOut()
    }
}