package net.attilaszabo.reduxdemo.login

import net.attilaszabo.redux.PartialSubscriber
import net.attilaszabo.redux.PartialSubscriber.SubStateChangeListener
import net.attilaszabo.redux.Subscriber
import net.attilaszabo.reduxdemo.common.base.BaseViewModel
import net.attilaszabo.reduxdemo.domain.login.LoginActions
import net.attilaszabo.reduxdemo.state.LoginState
import net.attilaszabo.reduxdemo.state.ReduxDemoState

class LoginViewModel(private val mReactionListener: ReactionListener) : BaseViewModel(), SubStateChangeListener<ReduxDemoState, LoginState> {

    // BaseViewModel

    override fun onCreateSubscriber(): Subscriber<ReduxDemoState> = PartialSubscriber(this)

    // SubStateChangeListener

    override fun getSubState(state: ReduxDemoState): LoginState = state.login

    override fun onSubStateChanged(subState: LoginState) {
        if (subState.isLoggedIn) {
            mReactionListener.onLoginDone()
        }

        mReactionListener.setError(subState.error?.message ?: "")
        mReactionListener.adjustLoadingIndicatorVisibility(subState.isLoading)
    }

    // Public Api

    fun onLoginButtonPressed(username: String, password: String) {
        mStore.dispatch(LoginActions.Login(username, password))
    }

    // ActivityReactionInterface

    interface ReactionListener {
        fun onLoginDone()
        fun setError(message: String)
        fun adjustLoadingIndicatorVisibility(isVisible: Boolean)
    }
}
