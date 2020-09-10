package jp.co.cyberagent.dojo2020.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import jp.co.cyberagent.dojo2020.R
import jp.co.cyberagent.dojo2020.data.remote.auth.FirebaseAuthentication
import jp.co.cyberagent.dojo2020.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationController: NavController
    private lateinit var googleSignInClient: GoogleSignInClient

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigationController = findNavController(R.id.navigation_host_fragment)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.navigation_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navigation_host_fragment).navigateUp()
    }

    override fun onStart() {
        super.onStart()

        if (Firebase.auth.currentUser == null) {
            googleSignInClient = FirebaseAuthentication.getClient(
                this,
                getString(R.string.default_web_client_id)
            )

            showSignInIntent()
        }
    }

    private fun showSignInIntent() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, FirebaseAuthentication.GOOGLE_AUTH_INTENT_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FirebaseAuthentication.GOOGLE_AUTH_INTENT_REQUEST) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account?.idToken ?: return

                FirebaseAuthentication.signInWithGoogle(idToken)
            } catch (e: ApiException) {

            }
        }
    }

}