package net.attilaszabo.reduxdemo.state

import java.io.Serializable

data class LoginState(
        val isLoading: Boolean = false,
        val isLoggedIn: Boolean = false,
        val username: String? = null,
        val password: String? = null,
        val oAuthToken: String? = null,
        val error: Throwable? = null
) : Serializable
