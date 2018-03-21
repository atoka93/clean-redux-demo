package net.attilaszabo.reduxdemo.domain

import net.attilaszabo.redux.enhancers.Enhancer
import net.attilaszabo.redux.enhancers.Middleware
import net.attilaszabo.reduxdemo.domain.common.LoggingMiddleware
import net.attilaszabo.reduxdemo.domain.login.PerformLoginSideEffect
import net.attilaszabo.reduxdemo.domain.login.data.IAccountsRepository
import net.attilaszabo.reduxdemo.domain.repositories.LoadRepositoriesSideEffect
import net.attilaszabo.reduxdemo.domain.repositories.data.IRepositoriesRepository
import net.attilaszabo.reduxdemo.state.ReduxDemoState

object Domain {
    fun getMiddlewareEnhancer(
            accountsRepository: IAccountsRepository,
            repositoriesRepository: IRepositoriesRepository
    ): Enhancer<ReduxDemoState> = Middleware.applyMiddlewares(
            LoadRepositoriesSideEffect(repositoriesRepository),
            PerformLoginSideEffect(accountsRepository),
            LoggingMiddleware()
    )
}
