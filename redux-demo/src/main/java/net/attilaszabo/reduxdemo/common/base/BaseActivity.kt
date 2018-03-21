package net.attilaszabo.reduxdemo.common.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.attilaszabo.reduxdemo.ReduxDemoApplication

abstract class BaseActivity<TViewModel : BaseViewModel> : AppCompatActivity() {

    // Members

    protected lateinit var mViewModel: TViewModel

    // AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayoutResourceId()?.let {
            setContentView(it)
        }
        onInitialize()
        mViewModel = onCreateViewModel().apply {
            onCreate((application as ReduxDemoApplication).getStore())
        }
    }

    override fun onDestroy() {
        if (::mViewModel.isInitialized) {
            mViewModel.onDestroy()
        }
        super.onDestroy()
    }

    // Public Api

    open fun onInitialize() {}

    abstract fun getLayoutResourceId(): Int?

    abstract fun onCreateViewModel(): TViewModel
}
