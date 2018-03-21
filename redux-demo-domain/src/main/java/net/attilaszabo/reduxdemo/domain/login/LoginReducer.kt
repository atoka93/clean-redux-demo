package net.attilaszabo.reduxdemo.domain.login

import net.attilaszabo.redux.Action
import net.attilaszabo.redux.Reducer
import net.attilaszabo.reduxdemo.state.LoginState

internal class LoginReducer : Reducer<LoginState> {

    override fun reduce(state: LoginState, action: Action): LoginState =
            when (action) {
                is LoginActions.Login -> {
                    LoginState(isLoading = true, username = action.username, password = action.password)
                }
                is LoginActions.LoggedIn -> {
                    LoginState(isLoggedIn = true, username = state.username, oAuthToken = action.oAuthToken)
                }
                is LoginActions.Error -> {
                    state.copy(isLoading = false, error = action.throwable)
                }
                else -> state
            }
}
