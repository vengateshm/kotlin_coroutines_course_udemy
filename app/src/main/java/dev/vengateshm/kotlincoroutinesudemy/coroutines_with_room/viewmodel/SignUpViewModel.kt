package dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.model.LoginState
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.model.User
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val _signUpComplete = MutableLiveData<Boolean>()
    val signUpComplete: LiveData<Boolean> = _signUpComplete

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val db by lazy { UserDatabase(getApplication()).userDao() }

    fun signUp(username: String, password: String, otherInfo: String) {
        coroutineScope.launch {
            val user = db.getUser(username)
            if (user != null) {
                withContext(Dispatchers.Main) {
                    _error.value = "User already exists"
                }
            } else {
                val user = User(
                    username = username,
                    passwordHash = password.hashCode(),
                    info = otherInfo
                )
                val userId = db.insertUser(user)
                user.id = userId
                LoginState.login(user)
                withContext(Dispatchers.Main) {
                    _signUpComplete.value = true
                }
            }
        }
    }
}