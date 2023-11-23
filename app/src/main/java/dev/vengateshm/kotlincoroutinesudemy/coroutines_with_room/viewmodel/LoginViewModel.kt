package dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.model.LoginState
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val _loggedIn = MutableLiveData<Boolean>()
    val loggedIn: LiveData<Boolean> = _loggedIn

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val db by lazy { UserDatabase(getApplication()).userDao() }

    fun login(username: String, password: String) {
        coroutineScope.launch {
            val user = db.getUser(username)
            if (user == null) {
                withContext(Dispatchers.Main) {
                    _error.value = "User not found"
                }
            } else {
                if (user.passwordHash == password.hashCode()) {
                    LoginState.login(user)
                    withContext(Dispatchers.Main) {
                        _loggedIn.value = true
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _error.value = "Password is incorrect"
                    }
                }
            }
        }
    }
}