package net.attilaszabo.reduxdemo.repositories

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_list_repositories.view.itemListRepositoriesNumberTextView
import kotlinx.android.synthetic.main.item_list_repositories.view.itemListRepositoriesTitleTextView
import net.attilaszabo.reduxdemo.R
import net.attilaszabo.reduxdemo.repositories.RepositoriesAdapter.RepositoriesViewHolder
import net.attilaszabo.reduxdemo.state.Repository

class RepositoriesAdapter(private var mRepositories: MutableList<Repository> = mutableListOf()) :
        ListAdapter<Repository, RepositoriesViewHolder>(DiffCallback()) {

    // RecyclerView.Adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder =
            RepositoriesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_repositories, parent, false))

    override fun getItemCount(): Int = mRepositories.size

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        holder.bind(position, mRepositories[position])
    }

    // Public Api

    fun setRepositories(repositories: List<Repository>) {
        mRepositories.clear()
        mRepositories.addAll(repositories)
        notifyDataSetChanged()
    }

    // RecyclerView.ViewHolder

    class RepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Members

        private val itemListRepositoriesNumberTextView: TextView by lazy { itemView.itemListRepositoriesNumberTextView }
        private val itemListRepositoriesTitleTextView: TextView by lazy { itemView.itemListRepositoriesTitleTextView }

        // Public Api

        fun bind(position: Int, article: Repository) {
            val layoutParams = itemListRepositoriesNumberTextView.layoutParams
            layoutParams.width = 0
            itemListRepositoriesNumberTextView.layoutParams = layoutParams

            itemListRepositoriesNumberTextView.text = (position + 1).toString()
            itemListRepositoriesTitleTextView.text = article.name
        }
    }

    // DiffUtil.ItemCallback

    class DiffCallback : DiffUtil.ItemCallback<Repository>() {

        override fun areItemsTheSame(oldItem: Repository?, newItem: Repository?): Boolean =
                oldItem === newItem

        override fun areContentsTheSame(oldItem: Repository?, newItem: Repository?): Boolean =
                oldItem == newItem
    }
}
