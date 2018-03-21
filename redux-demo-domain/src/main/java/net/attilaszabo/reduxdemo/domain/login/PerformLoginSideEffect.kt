package net.attilaszabo.reduxdemo.domain.login

import net.attilaszabo.redux.Action
import net.attilaszabo.redux.Dispatcher
import net.attilaszabo.redux.enhancers.SideEffect
import net.attilaszabo.reduxdemo.domain.common.DomainResult
import net.attilaszabo.reduxdemo.domain.login.LoginActions.LoggedIn
import net.attilaszabo.reduxdemo.domain.login.data.IAccountsRepository
import net.attilaszabo.reduxdemo.state.ReduxDemoState

internal class PerformLoginSideEffect(private val mAccountsRepository: IAccountsRepository) : SideEffect<ReduxDemoState>() {

    override fun dispatch(state: () -> ReduxDemoState, action: Action, dispatcher: Dispatcher) {
        if (action is LoginActions.Login) {
            val resultHandler = object : DomainResult<LoggedIn> {
                override fun success(result: LoggedIn) {
                    dispatcher.dispatch(result)
                }

                override fun error(throwable: Throwable?) {
                    throwable?.let { dispatcher.dispatch(LoginActions.Error(it)) }
                }
            }
            mAccountsRepository.grantNewAccessToken(action.username, action.password, resultHandler)
        }
    }
}
