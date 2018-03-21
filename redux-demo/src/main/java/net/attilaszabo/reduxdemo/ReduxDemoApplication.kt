package net.attilaszabo.reduxdemo

import android.app.Application
import android.os.Bundle
import net.attilaszabo.redux.Store
import net.attilaszabo.redux.implementation.android.ReduxActivityLifecycleCallbacks
import net.attilaszabo.redux.implementation.android.ReduxActivityLifecycleCallbacks.StatePersistenceListener
import net.attilaszabo.reduxdemo.data.login.AccountsRepository
import net.attilaszabo.reduxdemo.data.repositories.RepositoriesRepository
import net.attilaszabo.reduxdemo.data.sources.common.StatePreferences
import net.attilaszabo.reduxdemo.data.sources.github.GithubApiController
import net.attilaszabo.reduxdemo.data.sources.login.TokenManager
import net.attilaszabo.reduxdemo.domain.Domain
import net.attilaszabo.reduxdemo.domain.ReduxDemoStateReducer
import net.attilaszabo.reduxdemo.state.ReduxDemoState

class ReduxDemoApplication : Application(), StatePersistenceListener<ReduxDemoState> {

    // Members

    private lateinit var mActivityLifecycleCallbacks: ReduxActivityLifecycleCallbacks<ReduxDemoState>
    private lateinit var mStatePreference: StatePreferences

    // Application

    override fun onCreate() {
        super.onCreate()
        mActivityLifecycleCallbacks = ReduxActivityLifecycleCallbacks(
                this,
                ReduxDemoState(),
                ReduxDemoStateReducer(),
                Domain.getMiddlewareEnhancer(
                        AccountsRepository(TokenManager()),
                        RepositoriesRepository(GithubApiController())
                ))
        mStatePreference = StatePreferences(applicationContext)
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    // StatePersistenceListener

    override fun saveState(outInstanceState: Bundle?, state: ReduxDemoState) {
//        outInstanceState?.putSerializable(APP_STATE, getStore().getState())
        mStatePreference.appState = getStore().getState()
        outInstanceState?.putBoolean(HAS_APP_STATE, true)
    }

    override fun restoreState(savedInstanceState: Bundle?): ReduxDemoState? =
//        savedInstanceState?.getSerializable(APP_STATE) as? ReduxDemoState
            if (savedInstanceState?.getBoolean(HAS_APP_STATE) == true) {
                mStatePreference.appState
            } else {
                null
            }

    // Public Api

    fun getStore(): Store<ReduxDemoState> = mActivityLifecycleCallbacks.getStore()

    companion object {

        // Constants

        private const val APP_STATE = "appState"
        private const val HAS_APP_STATE = "hasAppState"
    }
}
