package dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.model.LoginState
import dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val db by lazy { UserDatabase(getApplication()).userDao() }

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val _signedOut = MutableLiveData<Boolean>()
    val signedOut: LiveData<Boolean> = _signedOut

    private val _userDeleted = MutableLiveData<Boolean>()
    val userDeleted: LiveData<Boolean> = _userDeleted

    fun signOut() {
        LoginState.logout()
        _signedOut.value = true
    }

    fun deleteUser() {
        coroutineScope.launch {
            LoginState.user?.let { db.deleteUser(it.id) }
            withContext(Dispatchers.Main) {
                _userDeleted.value = true
            }
        }
    }
}