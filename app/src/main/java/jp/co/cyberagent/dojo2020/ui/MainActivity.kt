package jp.co.cyberagent.dojo2020.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import jp.co.cyberagent.dojo2020.databinding.ActivityAuthBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private val userViewModel by viewModels<UserViewModel>()

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth

        with(binding) {
            userViewModel.user.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, "${it.uid} success!!", Toast.LENGTH_LONG).show()

                helloWorld.text = it.email
            }

            signInButton.setOnClickListener {
                val text = titleEditText.text.toString()

                val list = text.split(",")
                val email = list.first()
                val pass = list.drop(1).first()

                Toast.makeText(this@MainActivity, "[$email]", Toast.LENGTH_LONG).show()
                Toast.makeText(this@MainActivity, "[$pass]", Toast.LENGTH_LONG).show()

                val tag = "DEBUG"
                Log.d(tag, "[$email]")
                Log.d(tag, "[$pass]")

                userViewModel.signIn(email, pass)
            }

            signUpButton.setOnClickListener {
                val text = titleEditText.text.toString()

                val list = text.split(",")
                val email = list.first()
                val pass = list.drop(1).first()

                userViewModel.signUp(email, pass)
            }
        }

    }

    override fun onStart() {
        super.onStart()

        firebaseAuth.currentUser ?: return
    }
}