package net.attilaszabo.reduxdemo.state

import java.io.Serializable

data class RepositoriesState(
        val isLoading: Boolean = false,
        val isLastPage: Boolean = false,
        val loadFrom: Int = 0,
        val repositories: List<Repository> = listOf(),
        val error: Throwable? = null
) : Serializable
