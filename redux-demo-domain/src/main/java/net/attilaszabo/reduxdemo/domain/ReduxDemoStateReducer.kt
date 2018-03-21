package net.attilaszabo.reduxdemo.domain

import net.attilaszabo.redux.Action
import net.attilaszabo.redux.Reducer
import net.attilaszabo.reduxdemo.domain.login.LoginReducer
import net.attilaszabo.reduxdemo.domain.repositories.RepositoriesReducer
import net.attilaszabo.reduxdemo.state.ReduxDemoState

class ReduxDemoStateReducer : Reducer<ReduxDemoState> {

    // Members

    private val mLoginReducer: LoginReducer = LoginReducer()
    private val mRepositoriesReducer: RepositoriesReducer = RepositoriesReducer()

    // Reducer

    override fun reduce(state: ReduxDemoState, action: Action): ReduxDemoState =
            ReduxDemoState(
                    mLoginReducer.reduce(state.login, action),
                    mRepositoriesReducer.reduce(state.repositories, action)
            )
}
