package net.attilaszabo.reduxdemo.domain.repositories

import net.attilaszabo.redux.Action
import net.attilaszabo.redux.Reducer
import net.attilaszabo.reduxdemo.state.RepositoriesState
import net.attilaszabo.reduxdemo.state.Repository

internal class RepositoriesReducer : Reducer<RepositoriesState> {

    override fun reduce(state: RepositoriesState, action: Action): RepositoriesState =
            when (action) {
                is RepositoriesActions.LoadMore -> {
                    state.copy(isLoading = true, error = null)
                }
                is RepositoriesActions.Reload -> {
                    RepositoriesState(isLoading = true)
                }
                is RepositoriesActions.Results -> {
                    val repositories = mutableListOf<Repository>()
                    repositories.addAll(state.repositories)
                    repositories.addAll(action.repositories)
                    var loadFrom = state.loadFrom
                    if (repositories.size > 0) {
                        loadFrom = repositories[repositories.size - 1].id
                    }
                    RepositoriesState(loadFrom = loadFrom, isLastPage = action.isLastPage, repositories = repositories)
                }
                is RepositoriesActions.Error -> {
                    state.copy(isLoading = false, error = action.throwable)
                }
                else -> state
            }
}
