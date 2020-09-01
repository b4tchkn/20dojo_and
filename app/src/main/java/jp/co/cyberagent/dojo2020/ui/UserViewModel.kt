package jp.co.cyberagent.dojo2020.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import jp.co.cyberagent.dojo2020.data.model.User
import kotlinx.coroutines.launch

class UserViewModel(private val firebaseAuth: FirebaseAuth) : ViewModel() {
    private val mutableUser: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User>
        get() = mutableUser

    fun onStart() = viewModelScope.launch {
        val currentUser = firebaseAuth.currentUser ?: return@launch

        val email = currentUser.email ?: return@launch
        val uid = currentUser.uid

        mutableUser.value = User(uid, email)
    }

    fun signIn() {

    }

    fun logIn() {

    }
}