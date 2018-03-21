package net.attilaszabo.reduxdemo.repositories

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_repositories.activityRepositoriesConstraintLayout
import kotlinx.android.synthetic.main.activity_repositories.activityRepositoriesRepositoriesRecyclerView
import kotlinx.android.synthetic.main.activity_repositories.activityRepositoriesSwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_repositories.activityRepositoriesToolbar
import net.attilaszabo.reduxdemo.R
import net.attilaszabo.reduxdemo.common.base.BaseActivity
import net.attilaszabo.reduxdemo.repositories.RepositoriesViewModel.ReactionListener
import net.attilaszabo.reduxdemo.state.Repository

class RepositoriesActivity : BaseActivity<RepositoriesViewModel>(), ReactionListener {

    // Members

    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: RepositoriesAdapter

    // BaseActivity

    override fun getLayoutResourceId(): Int? = R.layout.activity_repositories

    override fun onCreateViewModel() = RepositoriesViewModel(this)

    override fun onInitialize() {
        setSupportActionBar(activityRepositoriesToolbar)
        mLinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mAdapter = RepositoriesAdapter()
        activityRepositoriesRepositoriesRecyclerView.layoutManager = mLinearLayoutManager
        activityRepositoriesRepositoriesRecyclerView.adapter = mAdapter
        activityRepositoriesRepositoriesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = mLinearLayoutManager.childCount
                val totalItemCount = mLinearLayoutManager.itemCount
                val firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition > 0) {
                    mViewModel.loadRepositories()
                }
            }
        })
        activityRepositoriesSwipeRefreshLayout.setOnRefreshListener { mViewModel.reloadRepositories() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            mViewModel.loadRepositories()
        }
    }

    // ReactionListener

    override fun onNewRepositories(repositories: List<Repository>) {
        mAdapter.setRepositories(repositories)
    }

    override fun setError(message: String?) {
        message?.let {
            val snackbar = Snackbar.make(activityRepositoriesConstraintLayout, message, Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction(R.string.retry, {
                mViewModel.loadRepositories()
            })
            snackbar.show()
        }
    }

    override fun adjustLoadingIndicatorVisibility(isVisible: Boolean) {
        activityRepositoriesSwipeRefreshLayout.isRefreshing = isVisible
    }
}
