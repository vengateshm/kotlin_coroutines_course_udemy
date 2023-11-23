package dev.vengateshm.kotlincoroutinesudemy.coroutines_with_room.model

object LoginState {
    var isLoggedIn = false
    var user: User? = null

    fun logout() {
        isLoggedIn = false
        user = null
    }

    fun login(user: User) {
        isLoggedIn = true
        this.user = user
    }
}