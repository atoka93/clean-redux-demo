package net.attilaszabo.reduxdemo.common.base

import net.attilaszabo.redux.Store
import net.attilaszabo.redux.Subscriber
import net.attilaszabo.redux.Subscription
import net.attilaszabo.reduxdemo.state.ReduxDemoState

abstract class BaseViewModel {

    // Members

    protected lateinit var mStore: Store<ReduxDemoState>
    private var mSubscription: Subscription? = null

    // Public Api

    fun onCreate(store: Store<ReduxDemoState>) {
        mStore = store
        val subscriber = onCreateSubscriber()
        mSubscription = mStore.subscribe(subscriber)
        subscriber.onStateChanged(mStore.getState())
    }

    fun onDestroy() {
        mSubscription?.unsubscribe()
    }

    abstract fun onCreateSubscriber(): Subscriber<ReduxDemoState>
}
