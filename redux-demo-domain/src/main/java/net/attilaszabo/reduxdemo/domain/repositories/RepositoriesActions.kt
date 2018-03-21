package net.attilaszabo.reduxdemo.domain.repositories

import net.attilaszabo.redux.Action
import net.attilaszabo.reduxdemo.state.Repository

sealed class RepositoriesActions : Action {
    object LoadMore : RepositoriesActions()
    object Reload : RepositoriesActions()
    data class Results(val repositories: List<Repository>, val isLastPage: Boolean) : RepositoriesActions()
    data class Error(val throwable: Throwable) : RepositoriesActions()
}
