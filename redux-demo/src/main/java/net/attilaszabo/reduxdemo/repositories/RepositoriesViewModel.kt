package net.attilaszabo.reduxdemo.repositories

import net.attilaszabo.redux.PartialSubscriber
import net.attilaszabo.redux.PartialSubscriber.SubStateChangeListener
import net.attilaszabo.redux.Subscriber
import net.attilaszabo.reduxdemo.common.base.BaseViewModel
import net.attilaszabo.reduxdemo.domain.repositories.RepositoriesActions
import net.attilaszabo.reduxdemo.state.ReduxDemoState
import net.attilaszabo.reduxdemo.state.RepositoriesState
import net.attilaszabo.reduxdemo.state.Repository

class RepositoriesViewModel(private val mReactionListener: ReactionListener) : BaseViewModel(), SubStateChangeListener<ReduxDemoState, RepositoriesState> {

    // BaseViewModel

    override fun onCreateSubscriber(): Subscriber<ReduxDemoState> = PartialSubscriber(this)

    // SubStateChangeListener

    override fun getSubState(state: ReduxDemoState): RepositoriesState = state.repositories

    override fun onSubStateChanged(subState: RepositoriesState) {
        mReactionListener.onNewRepositories(subState.repositories)
        mReactionListener.setError(subState.error?.message)
        mReactionListener.adjustLoadingIndicatorVisibility(subState.isLoading)
    }

    // Public Api

    fun loadRepositories() {
        val repositoriesState = mStore.getState().repositories
        if (!repositoriesState.isLastPage && !repositoriesState.isLoading) {
            mStore.dispatch(RepositoriesActions.LoadMore)
        }
    }

    fun reloadRepositories() {
        mStore.dispatch(RepositoriesActions.Reload)
    }

    // ActivityReactionInterface

    interface ReactionListener {
        fun onNewRepositories(repositories: List<Repository>)
        fun setError(message: String?)
        fun adjustLoadingIndicatorVisibility(isVisible: Boolean)
    }
}
