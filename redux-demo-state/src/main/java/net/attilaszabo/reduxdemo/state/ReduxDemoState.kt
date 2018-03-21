package net.attilaszabo.reduxdemo.state

import java.io.Serializable

data class ReduxDemoState(
        val login: LoginState = LoginState(),
        val repositories: RepositoriesState = RepositoriesState()
) : Serializable
