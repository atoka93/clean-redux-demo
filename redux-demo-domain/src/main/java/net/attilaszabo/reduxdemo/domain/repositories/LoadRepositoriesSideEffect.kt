package net.attilaszabo.reduxdemo.domain.repositories

import net.attilaszabo.redux.Action
import net.attilaszabo.redux.Dispatcher
import net.attilaszabo.redux.enhancers.SideEffect
import net.attilaszabo.reduxdemo.domain.common.DomainResult
import net.attilaszabo.reduxdemo.domain.repositories.RepositoriesActions.Results
import net.attilaszabo.reduxdemo.domain.repositories.data.IRepositoriesRepository
import net.attilaszabo.reduxdemo.state.ReduxDemoState

internal class LoadRepositoriesSideEffect(private val mRepositoriesRepository: IRepositoriesRepository) : SideEffect<ReduxDemoState>() {

    override fun dispatch(state: () -> ReduxDemoState, action: Action, dispatcher: Dispatcher) {
        if (action is RepositoriesActions.LoadMore || action is RepositoriesActions.Reload) {
            val resultHandler = object : DomainResult<Results> {
                override fun success(result: Results) {
                    dispatcher.dispatch(result)
                }

                override fun error(throwable: Throwable?) {
                    throwable?.let { dispatcher.dispatch(RepositoriesActions.Error(it)) }
                }
            }
            mRepositoriesRepository.loadRepositories(state().repositories.loadFrom, resultHandler)
        }
    }
}
