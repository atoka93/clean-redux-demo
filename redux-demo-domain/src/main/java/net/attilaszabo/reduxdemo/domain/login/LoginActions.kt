package net.attilaszabo.reduxdemo.domain.login

import net.attilaszabo.redux.Action

sealed class LoginActions : Action {
    data class Login(val username: String, val password: String) : LoginActions()
    data class LoggedIn(val oAuthToken: String) : LoginActions()
    data class Error(val throwable: Throwable) : LoginActions()
}
