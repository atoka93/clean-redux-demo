package net.attilaszabo.reduxdemo.domain.common

import net.attilaszabo.redux.Action
import net.attilaszabo.redux.Dispatcher
import net.attilaszabo.redux.enhancers.Middleware
import net.attilaszabo.reduxdemo.state.ReduxDemoState

internal class LoggingMiddleware : Middleware<ReduxDemoState> {

    override fun dispatch(state: () -> ReduxDemoState, action: Action, next: Dispatcher, dispatcher: Dispatcher) {
        println("-------------------------")
        println("with state=${state()}")
        println("and action=$action")
        next.dispatch(action)
        println("output state=${state()}")
    }
}
